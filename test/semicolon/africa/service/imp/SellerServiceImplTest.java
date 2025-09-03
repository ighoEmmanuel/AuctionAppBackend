//package semicolon.africa.service.imp;
//
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import semicolon.africa.data.models.Seller;
//import semicolon.africa.data.repositories.BidderRepository;
//import semicolon.africa.data.repositories.ProductRepository;
//import semicolon.africa.data.repositories.SellerRepository;
//import semicolon.africa.data.repositories.UserRepository;
//import semicolon.africa.dtos.reposonse.AuctionResponse;
//import semicolon.africa.dtos.request.AuctionProductDto;
//import semicolon.africa.service.BidService;
//import semicolon.africa.service.ProductService;
//import semicolon.africa.service.SellerService;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//class SellerServiceImplTest {
//
//    @Autowired
//    private BidderRepository bidderRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    private BidderService bidderService;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private BidService bidService;
//
//    @Autowired
//    private SellerRepository sellerRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ProductService productService;
//
//    private SellerService sellerService;
//
//    @BeforeEach
//    void setUp() {
//        sellerService = new SellerServiceImpl(
//                sellerRepository,
//                bidderRepository,
//                passwordEncoder,
//                productService,
//                bidderService,
//                bidService,
//                userRepository
//        );
//    }
//
//    @AfterEach
//    void tearDown() {
//        sellerRepository.deleteAll();
//        productRepository.deleteAll();
//    }
//
//    @Test
//    public void AuctionAProductAndCheckIfTheResponseIsNotNullTest() {
//        Seller seller = new Seller();
//        seller.setUserName("Jonathan");
//        seller.setEmail("jonathan@gmai.com");
//        seller.setPassword("password");
//
//        Seller savedSeller = sellerRepository.save(seller);
//
//        AuctionProductDto auctionProductDto = new AuctionProductDto();
//        auctionProductDto.setSellerId(savedSeller.getId());
//        auctionProductDto.setProductName("Maths");
//        auctionProductDto.setPrice(BigDecimal.valueOf(1000));
//        auctionProductDto.setBidStart(LocalDateTime.now().plusSeconds(50));
//        auctionProductDto.setBidStop(LocalDateTime.now().plusHours(2));
//
//        AuctionResponse auctionResponse = sellerService.auctionProduct(auctionProductDto);
//        assertNotNull(auctionResponse);
//    }
//
//    @Test
//    public void TryTOAuctionAProductWithTheBidStartingTimeAfterTheNormalTimeAndAssertItThrowsErrorTest() {
//        Seller seller = new Seller();
//        seller.setUserName("Jonathan");
//        seller.setEmail("jonathan@gmai.com");
//        seller.setPassword("password");
//
//        Seller savedSeller = sellerRepository.save(seller);
//
//        AuctionProductDto auctionProductDto = new AuctionProductDto();
//        auctionProductDto.setSellerId(savedSeller.getId());
//        auctionProductDto.setProductName("Maths");
//        auctionProductDto.setPrice(BigDecimal.valueOf(1000));
//        auctionProductDto.setBidStart(LocalDateTime.now().minusDays(1));
//        auctionProductDto.setBidStop(LocalDateTime.now().plusHours(2));
//
//       assertThrows(IllegalArgumentException.class,() -> {
//           sellerService.auctionProduct(auctionProductDto);
//       },"Start time must be in the future");
//
//    }
//
////    @Test
////    public void
//}
