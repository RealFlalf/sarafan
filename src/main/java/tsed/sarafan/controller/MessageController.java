package tsed.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import tsed.sarafan.domain.Message;
import tsed.sarafan.domain.Views;
import tsed.sarafan.repo.MessageRepo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageRepo messageRepo;

    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    @JsonView(Views.IdNum.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getOne(
            @PathVariable("id") Message message
    ) {
        return message;
    }

    @PostMapping
    public Message create (
            @RequestBody Message message
    ) {
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message update (
            @PathVariable("id") Message messageFromDB,
            @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, messageFromDB, "id");

        return messageRepo.save(messageFromDB   );
    }

    @DeleteMapping("{id}")
    public void delete (
            @PathVariable("id") Message message
    ) {
        messageRepo.delete(message);
    }
}
