package semicolon.africa.service.imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import semicolon.africa.data.models.Product;
import semicolon.africa.data.models.Seller;
import semicolon.africa.data.repositories.ProductRepository;
import semicolon.africa.data.repositories.SellerRepository;
import semicolon.africa.dtos.reposonse.AuctionResponse;
import semicolon.africa.dtos.request.AuctionProductDto;
import semicolon.africa.service.ProductService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private  final SellerRepository sellerRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;

    }

    @Override
    public AuctionResponse auctionProduct(AuctionProductDto productDto) {
        String sellerId = productDto.getSellerId();
        Optional<Seller> seller = sellerRepository.findById(sellerId);

        if (seller.isEmpty()) {
            throw new IllegalArgumentException("Seller with ID " + sellerId + " does not exist.");
        }

        LocalDateTime startTime = productDto.getBidStart();
        LocalDateTime endTime = productDto.getBidStop();
        validateAuctionTimes(startTime, endTime);

        BigDecimal price = productDto.getPrice();
        String productName = productDto.getProductName();

        Product product = new Product();
        product.setPrice(price);
        product.setName(productName);
        product.setBidStartTime(startTime);
        product.setBidStopTime(endTime);

        seller.get().getProducts().add(product);
        productRepository.save(product);
        sellerRepository.save(seller.get());

        AuctionResponse auctionResponse = new AuctionResponse();
        auctionResponse.setProductId(product.getId());
        auctionResponse.setProductName(product.getName());
        auctionResponse.setPrice(product.getPrice());

        return auctionResponse;
    }


//    @Scheduled(fixedRate = 86400000)
//    public void removeExpiredProduct() {
//        List<Product> products = productRepository.findAll();
//        List<Product> productsToDelete = new ArrayList<>();
//
//        LocalDateTime now = LocalDateTime.now();
//
//        for (Product product : products) {
//            if (product.getBidStopTime() != null) {
//                LocalDateTime bidStopTimeWithMargin = product.getBidStopTime().plusDays(1);
//                if (now.isAfter(bidStopTimeWithMargin)) {
//                    productsToDelete.add(product);
//                }
//            }
//        }
//
//        productRepository.deleteAll(productsToDelete);
//    }



    public void validateAuctionTimes(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime now = LocalDateTime.now();

        if (startTime.isBefore(now)) {
            throw new IllegalArgumentException("Start time must be in the future");
        }

        if (endTime.isBefore(now)) {
            throw new IllegalArgumentException("End time must be in the future");
        }

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
    }

    @Override
    public List<Product> viewAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        List<Product> activeProducts = new ArrayList<>();
        for(Product product : allProducts) {
            if((product.getBidStartTime().isAfter(now)) || product.getBidStopTime().isAfter(now)){
                activeProducts.add(product);
            }
        }
        return activeProducts;
    }


}
