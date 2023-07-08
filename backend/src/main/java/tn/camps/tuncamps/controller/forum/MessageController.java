package tn.camps.tuncamps.controller.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.forum.Message;
import tn.camps.tuncamps.service.forum.IMessageService;

import java.util.List;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins ="http://localhost:4200")
public class MessageController {

        @Autowired
        private IMessageService iMessageService;

        @PostMapping("/add")
        @ResponseBody
        public Message createMessage(@RequestBody Message message) {
            return iMessageService.createMessage(message);
        }

        @GetMapping("/show/{id}")
        public Message getMessageById(@PathVariable("id") int id) {
            return iMessageService.retrieveMessage(id);
        }


        @GetMapping("/show")
        public List<Message> getAllMessages() {
            return iMessageService.retrieveAllMessage();
        }

        @PutMapping("update/{id}")
        public Message updateMessage(@PathVariable("id") int id, @RequestBody Message message) {
            return iMessageService.updateMessage(id, message);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteMessage(@PathVariable("id") int id) {
            iMessageService.deleteMessage(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

}
