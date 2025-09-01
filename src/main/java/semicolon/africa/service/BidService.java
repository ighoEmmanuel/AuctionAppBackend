package semicolon.africa.service;

import semicolon.africa.data.models.Bid;
import semicolon.africa.dtos.reposonse.BidResponse;
import semicolon.africa.dtos.request.BidDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BidService {
    public BidResponse placeBid(BidDto bidDto);
    public String highestBidder(String productId);
//    List<Bid> biddersForAProduct(String productId, LocalDateTime time);
}
