package semicolon.africa.dtos.request;

import lombok.Data;
import semicolon.africa.data.models.Address;

@Data
public class ProfileDto {
    private String userId;
    private String role;
    private Address address;
}
