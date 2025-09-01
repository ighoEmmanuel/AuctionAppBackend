package semicolon.africa.dtos.reposonse;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class AuctionResponse {
    String productId;
    String productName;
    BigDecimal price;
}
