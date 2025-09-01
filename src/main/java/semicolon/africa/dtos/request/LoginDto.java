package semicolon.africa.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
final public class LoginDto {
    @NotBlank(message = "Email is required")
    @Email(message = "invalid email type")
    private String email;

    @NotBlank(message = "Password is required")
//    ?
    private String password;
}