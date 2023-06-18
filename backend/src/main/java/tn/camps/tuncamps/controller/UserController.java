package tn.camps.tuncamps.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistance.entity.User;
import tn.camps.tuncamps.service.IUserService;

@RestController
@AllArgsConstructor
public class UserController {
    IUserService userService;

    @PostMapping("/add")
    public User CreateUser(@RequestBody User user) {
        return userService.CreateUser(user);
    }
@GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") int userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") int userId, @RequestBody User updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }
@DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") int userId) {
        userService.deleteUser(userId);
    }
}
