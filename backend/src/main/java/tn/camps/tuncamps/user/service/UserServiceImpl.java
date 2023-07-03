package tn.camps.tuncamps.user.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.camps.tuncamps.commons.security.JWTGenerator;
import tn.camps.tuncamps.user.DTO.AuthResponseDTO;
import tn.camps.tuncamps.user.DTO.LoginDTO;
import tn.camps.tuncamps.user.DTO.RegisterDTO;
import tn.camps.tuncamps.persistence.entity.user.User;
import tn.camps.tuncamps.persistence.repository.user.UserRepository;
import tn.camps.tuncamps.user.repository.UserRole;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @Override
    public User CreateUser(RegisterDTO registerDTO) {
        User user = new User();
        if (userRepository.existsByEmail(registerDTO.getEmail()) || userRepository.existsByUsername(
                registerDTO.getUserName())) {
            throw new RuntimeException("userName and Email must be unique");
        }
        user.setUsername(registerDTO.getUserName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(UserRole.GUEST);
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Registering new User failed");
        }
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUser(int userId, User updatedUserEntity) {
        User userEntity = userRepository.findById(userId).orElse(null);
        ;

        assert userEntity != null;
        userEntity.setUsername(updatedUserEntity.getUsername());
        userEntity.setEmail(updatedUserEntity.getEmail());

        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public AuthResponseDTO loginUser(LoginDTO loginDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getUserName(),
                loginDTO.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponseDTO(token);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
}
