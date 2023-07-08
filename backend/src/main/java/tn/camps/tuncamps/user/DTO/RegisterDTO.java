package tn.camps.tuncamps.user.DTO;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class RegisterDTO {
    private String userName;
    @javax.validation.constraints.Email
    private String Email;
    private String password;
}
