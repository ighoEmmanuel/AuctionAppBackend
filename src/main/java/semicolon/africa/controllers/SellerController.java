package semicolon.africa.controllers;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.dtos.reposonse.AuctionResponse;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.AuctionProductDto;
import semicolon.africa.dtos.request.RegisterDto;
import semicolon.africa.service.SellerService;
import semicolon.africa.service.imp.SellerServiceImpl;

@RestController
@RequestMapping("/api")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerServiceImpl sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/register/seller")
    public RegisterResponse register(@RequestBody RegisterDto registerDto) {
        return sellerService.register(registerDto);
    }
//
    @PostMapping("/addProduct")
    public AuctionResponse auctionProduct(@RequestBody AuctionProductDto addProductDto) {
        return sellerService.auctionProduct(addProductDto);
    }

    @GetMapping("/highest/bidder")
    public String highestBidder(@RequestBody String productId) {
        return sellerService.highestBidder(productId);
    }

}
