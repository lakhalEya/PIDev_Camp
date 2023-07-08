package tn.camps.tuncamps.user.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import tn.camps.tuncamps.user.DTO.AuthResponseDTO;
import tn.camps.tuncamps.user.DTO.LoginDTO;
import tn.camps.tuncamps.user.DTO.RegisterDTO;
import tn.camps.tuncamps.user.repository.UserEntity;
import tn.camps.tuncamps.user.service.IUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> RegisterNewUser(@RequestBody RegisterDTO registerDTO) throws Exception {
        try {
            UserEntity user = userService.CreateUser(registerDTO);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            System.out.println("actually made it here");
            return new ResponseEntity<>(userService.loginUser(loginDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseStatusException(HttpStatus.FORBIDDEN, "Login Failed, Please check "
                    + "Username and Password").getStatus());
        }
    }

    @GetMapping("/get/{id}")
    public UserEntity getUserById(@PathVariable("id") int userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/update/{id}")
    public UserEntity updateUser(@PathVariable("id") int userId, @RequestBody UserEntity updatedUserEntity) {
        return userService.updateUser(userId, updatedUserEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") int userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/getByName")
    public UserEntity getUserByUserName(@RequestParam String userName) {
        System.out.println(userName);
        return userService.getUserByUserName(userName);
    }
}