package semicolon.africa.dtos.reposonse;


import lombok.Data;
import org.springframework.http.HttpStatus;
import semicolon.africa.data.models.Profile;

@Data
public class RegisterResponse {
    private String token;
}
