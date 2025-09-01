package semicolon.africa.dtos.request;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AuctionProductDto {
    private String productName;
    private BigDecimal price;
    private String sellerId;
    private LocalDateTime bidStart;
    private LocalDateTime  bidStop;
}
