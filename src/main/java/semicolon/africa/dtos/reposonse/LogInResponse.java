package semicolon.africa.dtos.reposonse;


import lombok.Data;
import semicolon.africa.data.models.Profile;
import semicolon.africa.data.models.User;

@Data
public class LogInResponse {
    public String token;
}
