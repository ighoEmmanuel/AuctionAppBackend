//package semicolon.africa.service.imp;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import semicolon.africa.data.models.Seller;
//import semicolon.africa.data.repositories.BidderRepository;
//import semicolon.africa.data.repositories.ProductRepository;
//import semicolon.africa.data.repositories.SellerRepository;
//import semicolon.africa.dtos.reposonse.AuctionResponse;
//import semicolon.africa.dtos.request.AuctionProductDto;
//import semicolon.africa.service.BidderService;
//import semicolon.africa.service.ProductService;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//
//@SpringBootTest
//class ProductServiceImplTest {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private SellerRepository sellerRepository;
//
//    @Autowired
//    private ProductService productService;
//
//
//    @BeforeEach
//    void setUp(){
//       productService = new ProductServiceImpl(productRepository,sellerRepository);
//    }
//
//    @AfterEach
//    void tearDown(){
//        productRepository.deleteAll();
//        sellerRepository.deleteAll();
//    }
//    @Test
//    void contextLoads() {
//        assertTrue(true); // Just to see if Spring context loads at all
//    }
//
//
//    @Test
//    void testAProductCanBeAuctionedWithValidTime() {
//        Seller seller = new Seller();
//        seller.setUserName("Jonathan");
//        seller.setEmail("jonathan@gmai.com");
//        seller.setPassword("password");
//        Seller savedSeller = sellerRepository.save(seller);
//
//
//        AuctionProductDto auctionProductDto = new AuctionProductDto();
//        auctionProductDto.setSellerId(savedSeller.getId());
//        auctionProductDto.setProductName("Maths");
//        auctionProductDto.setPrice(BigDecimal.valueOf(1000));
//        auctionProductDto.setBidStart(LocalDateTime.now().plusSeconds(50));
//        auctionProductDto.setBidStop(LocalDateTime.now().plusHours(2));
//        AuctionResponse auctionResponse = productService.auctionProduct(auctionProductDto);
//        assertNotNull(auctionResponse);
//    }
//}