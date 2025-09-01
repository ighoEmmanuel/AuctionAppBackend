package semicolon.africa.service;

import semicolon.africa.dtos.reposonse.AuctionResponse;
import semicolon.africa.dtos.reposonse.BidResponse;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.AuctionProductDto;
import semicolon.africa.dtos.request.BidDto;
import semicolon.africa.dtos.request.RegisterDto;

public interface SellerService {
    RegisterResponse register(RegisterDto registerDto);
    AuctionResponse auctionProduct(AuctionProductDto addProductDto);
    String highestBidder(String productionId);

}
