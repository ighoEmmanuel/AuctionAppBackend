package semicolon.africa.dtos.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BidDto {
    private String userId;
    private String productId;
    private BigDecimal price;
}
