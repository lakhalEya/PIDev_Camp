package tn.camps.tuncamps.user.DTO;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String TokenType = "Bearer ";
    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
