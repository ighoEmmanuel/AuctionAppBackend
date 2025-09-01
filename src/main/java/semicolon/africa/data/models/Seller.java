package semicolon.africa.data.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "sellers")
public class Seller extends User{
    @DBRef
    private List<Product> products = new ArrayList<>();
}
