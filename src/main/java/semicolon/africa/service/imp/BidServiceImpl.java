package semicolon.africa.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.data.models.Bid;
import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.models.Product;
import semicolon.africa.data.repositories.BidRepository;
import semicolon.africa.data.repositories.BidderRepository;
import semicolon.africa.data.repositories.ProductRepository;
import semicolon.africa.data.repositories.UserRepository;
import semicolon.africa.dtos.reposonse.BidResponse;
import semicolon.africa.dtos.request.BidDto;
import semicolon.africa.service.BidService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class  BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final BidderRepository bidderRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, ProductRepository productRepository, UserRepository userRepository, BidderRepository bidderRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.bidderRepository = bidderRepository;
    }

    @Override
    public BidResponse placeBid(BidDto bidDto) {
        Optional<Product> originalProduct = productRepository.findById(bidDto.getProductId());
        if (originalProduct.isEmpty()) {
            throw new IllegalArgumentException("Product not found with ID: " + bidDto.getProductId());
        }
        Product sellersProduct = originalProduct.get();
        BigDecimal sellerPrice = sellersProduct.getPrice();
        BigDecimal buyerPrice = bidDto.getPrice();

        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(sellersProduct.getBidStopTime())){
            throw new IllegalArgumentException("Bid has Expired");
        }
        if (buyerPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Bid price must be greater than zero");
        }

        if (buyerPrice.compareTo(sellerPrice) <= 0) {
            throw new IllegalArgumentException("Bid price must be higher than current price: " + sellerPrice);
        }
        sellersProduct.setPrice(buyerPrice);
        productRepository.save(sellersProduct);
        Bid bid = new Bid();
        bid.setUserId(bidDto.getUserId());
        bid.setPrice(buyerPrice);
        bid.setProductId(sellersProduct.getId());
        bidRepository.save(bid);
        BidResponse bidResponse = new BidResponse();
        bidResponse.setBidId(bid.getId());
        return bidResponse;
    }



    public Product findProductById(String productId) {
        Product product = productRepository.findById(productId).get();
        return  product;
    };


    @Override
    public String highestBidder(String productId) {
        List<Bid> bids = bidRepository.findAll();
        for (Bid bid : bids) {
            Product product = findProductById(productId);
            if(Objects.equals(bid.getProductId(), productId)){
                if(Objects.equals(bid.getPrice(), product.getPrice())){
                    Optional<Bidder> bidder = bidderRepository.findById(bid.getUserId());
                    return bidder.get().getUserName();
                }
            }
        }
        throw new IllegalArgumentException("This user dont have any bid on this product");
    }

//    @Override
//    public List<Bidder> biddersForAProduct(String productId, LocalDateTime time) {
//        bidRepository.findAllById(productId,time);
//    }

    ;



    




//    @Scheduled(fixedRate = 86400000)
//    public void removeExpiredBids() {
//        List<Bid> bidDocuments = bidRepository.findAll();
//        for (Bid bid : bidDocuments) {
//            Optional<Product> product = productRepository.findById(bid.getProductId());
//            if (product.isEmpty()) bidRepository.delete(bid);
//        }
//    }


}