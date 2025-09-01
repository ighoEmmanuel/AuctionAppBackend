package semicolon.africa.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "bides")
public class Bid {
    @Id
    private String id;
    private String userId;
    private BigDecimal price;
    private String productId;
}
