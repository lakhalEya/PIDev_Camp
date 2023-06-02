package tn.camps.tuncamps.service.forum;

import tn.camps.tuncamps.persistence.entity.forum.Message;
import java.util.List;

public interface IMessageService {
    Message createMessage(Message message);
    Message retrieveMessage(int id);
    List<Message> retrieveAllMessage();
    Message updateMessage(int id, Message message);
    void deleteMessage(int id);
}
