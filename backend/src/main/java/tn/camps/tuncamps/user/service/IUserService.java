package tn.camps.tuncamps.user.service;

import tn.camps.tuncamps.user.DTO.AuthResponseDTO;
import tn.camps.tuncamps.user.DTO.LoginDTO;
import tn.camps.tuncamps.user.DTO.RegisterDTO;
import tn.camps.tuncamps.user.repository.UserEntity;

public interface IUserService {
    UserEntity CreateUser(RegisterDTO registerDTO);
    UserEntity getUserById(int userId);
    UserEntity updateUser(int userId, UserEntity updatedUserEntity);
    void deleteUser(int userId);
    AuthResponseDTO loginUser(LoginDTO loginDTO);
    UserEntity getUserByUserName(String userName);

}
