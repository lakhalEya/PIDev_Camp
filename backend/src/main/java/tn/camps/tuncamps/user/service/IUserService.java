package tn.camps.tuncamps.user.service;

import tn.camps.tuncamps.user.DTO.AuthResponseDTO;
import tn.camps.tuncamps.user.DTO.LoginDTO;
import tn.camps.tuncamps.user.DTO.RegisterDTO;
import tn.camps.tuncamps.persistence.entity.user.User;

public interface IUserService {
    User CreateUser(RegisterDTO registerDTO);
    User getUserById(int userId);
    User updateUser(int userId, User updatedUserEntity);
    void deleteUser(int userId);
    AuthResponseDTO loginUser(LoginDTO loginDTO);
    User getUserByUserName(String userName);

}
