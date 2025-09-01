package semicolon.africa.controllers;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.data.models.Product;
import semicolon.africa.dtos.reposonse.BidResponse;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.BidDto;
import semicolon.africa.dtos.request.RegisterDto;
import semicolon.africa.service.imp.BidderServiceImpl;
import semicolon.africa.service.BidderService;


import java.util.List;

@RestController
@RequestMapping("/api")
public class BidderControllers {

    private final BidderService bidderService;


    public BidderControllers(BidderServiceImpl bidderService) {
        this.bidderService = bidderService;

    }
//
    @PostMapping("/register/bidder")
    public RegisterResponse registerBidder(@RequestBody RegisterDto request) {
        return bidderService.register(request);
    }

    @PostMapping("/bid")
    public BidResponse bid(@RequestBody BidDto bidDto){
        return bidderService.bid(bidDto);
    }
//
    @GetMapping("/viewAllProducts")
    public List<Product> viewAllProducts(){
        return bidderService.viewProduct();
    }

//
//    @PostMapping("uploadImage")
//    public void uploadImage(@RequestParam String userId,@RequestParam("file") MultipartFile file){
//        profileService.upLoadImage(userId,file);
//    }
}
