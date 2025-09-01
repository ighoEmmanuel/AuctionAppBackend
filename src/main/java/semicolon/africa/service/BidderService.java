package semicolon.africa.service;

import semicolon.africa.data.models.Product;
import semicolon.africa.dtos.reposonse.BidResponse;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.BidDto;
import semicolon.africa.dtos.request.RegisterDto;

import java.util.List;

public interface BidderService {
    public RegisterResponse register(RegisterDto bidderDto);
    public BidResponse bid(BidDto bidDto);
    List<Product> viewProduct();
}
