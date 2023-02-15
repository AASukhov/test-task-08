package example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthResponseDTO {

    @JsonProperty("auth-token")
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }
}
