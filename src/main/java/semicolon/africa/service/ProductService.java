package semicolon.africa.service;

import semicolon.africa.data.models.Product;
import semicolon.africa.dtos.reposonse.AuctionResponse;
import semicolon.africa.dtos.request.AuctionProductDto;

import java.util.List;

public interface ProductService {
    AuctionResponse auctionProduct(AuctionProductDto productDto);
    List<Product> viewAllProducts();
}
