package semicolon.africa.data.models;


import lombok.Data;

import java.awt.*;


@Data
public class Profile {
    private String email;
    private Address address;
    private AccountStatus accountStatus;
    private String url;
}
