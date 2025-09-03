package semicolon.africa.controllers;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.data.models.Product;
import semicolon.africa.dtos.reposonse.AuctionResponse;
import semicolon.africa.dtos.reposonse.BidResponse;
import semicolon.africa.dtos.reposonse.ProfileResponse;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.AuctionProductDto;
import semicolon.africa.dtos.request.BidDto;
import semicolon.africa.dtos.request.ProfileDto;
import semicolon.africa.dtos.request.RegisterDto;
import semicolon.africa.service.UserService;
import semicolon.africa.service.imp.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }


    @PostMapping("/register/user")
    public RegisterResponse registerBidder(@RequestBody RegisterDto request) {
        return userService.register(request);
    }

    @PostMapping("/update/address")
    public ProfileResponse updateAddress(@Valid @RequestBody ProfileDto profileDto) {
        return userService.updateAddress(profileDto);
    }


    @PostMapping("/bid")
    public BidResponse bid(@RequestBody BidDto bidDto){
        return userService.bid(bidDto);
    }

    @GetMapping("/viewAllProducts")
    public List<Product> viewAllProducts(){
        return userService.viewProduct();
    }


    @PostMapping("/addProduct")
    public AuctionResponse auctionProduct(@RequestBody AuctionProductDto addProductDto) {
        return userService.auctionProduct(addProductDto);
    }


    @GetMapping("/highest/bidder")
    public String highestBidder(@RequestBody String productId) {
        return userService.highestBidder(productId);
    }
//    @PostMapping


}
