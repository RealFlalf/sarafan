<template>
    <v-app class="main-app">
        <v-app-bar app>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-btn text 
                    v-if="profile"
                    :disabled="$route.path === '/'"
                    @click="showMessages">
                Messages
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn text 
                    v-if="profile"
                    :disabled="$route.path === '/profile'"
                    @click="showProfile">
                {{profile.name}}
            </v-btn>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>


<script>
    import { addHandler } from 'util/ws'
    import { mapState, mapMutations } from 'vuex'
    
    export default {
        computed: mapState(['profile']),
        methods: {
            ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
            showMessages() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('/profile')
            }
        },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE'){
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error(`Unknow event type: ${data.eventType}`)
                    }
                } else {
                    console.error(`Unknow object type: ${data.objectType}`)
                }
            })
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }
    }
</script>

<style>

</style>