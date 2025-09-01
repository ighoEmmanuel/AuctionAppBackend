package semicolon.africa.data.models;



import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "bidder")
public class Bidder extends User{
    @DBRef
    private Set<Bid> bids;
}
