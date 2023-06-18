package tn.camps.tuncamps.service;

import tn.camps.tuncamps.persistance.entity.User;

public interface IUserService {
    User CreateUser(User user);
    User getUserById(int userId);
    User updateUser(int userId, User updatedUser);
    void deleteUser(int userId);
}
