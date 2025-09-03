package semicolon.africa.service;

import semicolon.africa.data.models.Product;
import semicolon.africa.dtos.reposonse.AuctionResponse;
import semicolon.africa.dtos.reposonse.BidResponse;
import semicolon.africa.dtos.reposonse.ProfileResponse;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.AuctionProductDto;
import semicolon.africa.dtos.request.BidDto;
import semicolon.africa.dtos.request.ProfileDto;
import semicolon.africa.dtos.request.RegisterDto;

import java.util.List;

public interface UserService {
    RegisterResponse register(RegisterDto registerDto);
    ProfileResponse updateAddress(ProfileDto profileDto);
    ProfileResponse updateImage(String id,String url,String role);
    AuctionResponse auctionProduct(AuctionProductDto addProductDto);

    BidResponse bid(BidDto bidDto);

    String highestBidder(String productionId);

    List<Product> viewProduct();
}
