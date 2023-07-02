package tn.camps.tuncamps.service.forum;

import tn.camps.tuncamps.persistence.entity.forum.React;
import java.util.List;

public interface IReactService {
    React createReact(React react);
    React retrieveReact(int id);
    List<React> retrieveAllReact();
    React updateReact(int id, React react);
    void deleteReact(int id);
}
