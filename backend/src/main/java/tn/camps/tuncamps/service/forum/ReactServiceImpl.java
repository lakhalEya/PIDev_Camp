package tn.camps.tuncamps.service.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.persistence.entity.forum.React;
import tn.camps.tuncamps.persistence.repository.forum.ReactRepository;
import java.util.List;

@Service
public class ReactServiceImpl implements IReactService{

    @Autowired
    ReactRepository reactRepository;

    @Override
    public React createReact(React react) {
        return reactRepository.save(react);
    }

    @Override
    public React retrieveReact(int id) {
        return reactRepository.findById(id).get();
    }

    @Override
    public List<React> retrieveAllReact() {
        List<React> listreact =  (List<React>) reactRepository.findAll();
        return listreact;
    }

    @Override
    public React updateReact(int id, React react) {
        React existingReact = reactRepository.findById(id).orElse(null);
        if (existingReact != null) {
            existingReact.setTitle(react.getTitle());
            existingReact.setDescription(react.getDescription());
            return reactRepository.save(existingReact);
        }
        return null;
    }

    @Override
    public void deleteReact(int id) {
        reactRepository.deleteById(id);
    }
}
