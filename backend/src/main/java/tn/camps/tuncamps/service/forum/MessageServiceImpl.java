package tn.camps.tuncamps.service.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.forum.Message;
import tn.camps.tuncamps.persistence.repository.forum.MessageRepository;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService{

    @Autowired
    MessageRepository messageRepository;
    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message retrieveMessage(int id) {
        return messageRepository.findById(id).get();
    }

    @Override
    public List<Message> retrieveAllMessage() {
        List<Message> messages=  (List<Message>) messageRepository.findAll();
        return messages;
    }

    @Override
    public Message updateMessage(int id, Message message) {
        if(messageRepository.existsById(message.getIdMessage())) {
            Message m = messageRepository.findById(message.getIdMessage()).get();
            m.setContent(message.getContent());
            m.setReact(message.getReact());
            messageRepository.save(message);
        }
        return message;
    }

    @Override
    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }
}
