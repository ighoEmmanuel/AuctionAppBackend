package semicolon.africa.dtos.request;

import lombok.Data;

@Data
public class RegisterDto {
    private String userName;
    private String email;
    private String password;
}
