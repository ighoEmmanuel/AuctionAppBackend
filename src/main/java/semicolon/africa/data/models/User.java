package semicolon.africa.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data

public  class User {
    @Id
    private String id;
    private String userName;
    private String email;
    private String password;
    private AccountType accountType;
    private Profile profile;
    @DBRef
    private List<Product> products = new ArrayList<>();
    @DBRef
    private Set<Bid> bids;
}
