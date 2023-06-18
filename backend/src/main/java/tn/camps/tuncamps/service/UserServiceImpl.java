package tn.camps.tuncamps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistance.entity.User;
import tn.camps.tuncamps.persistance.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements  IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User CreateUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User getUserById(int userId) {
      return   userRepository.findById(userId).orElse(null);

    }

    @Override
    public User updateUser(int userId, User updatedUser) {
        User user = userRepository.findById(userId).orElse(null);
               // .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé."));


        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());


        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
