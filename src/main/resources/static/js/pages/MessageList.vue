<template>
    <v-container>
        <v-layout align-space-around justify-start column>
            <message-form :messageAttr="message" />
            <message-row v-for="(message,index) in sortedMessages"
                        :key="`message.id-${index}`"
                        :message="message"
                        :editMessage="editMessage" />
        </v-layout>
    </v-container>
</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import messagesApi from 'api/messages'
    import { mapGetters } from 'vuex'

    export default {
        components: {
            MessageRow
        },
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        computed: mapGetters([ 'sortedMessages' ]),
        methods: {
            editMessage(message){
                this.message = message
            }
        }
    }
</script>

<style>

</style>