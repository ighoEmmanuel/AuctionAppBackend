package semicolon.africa.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import semicolon.africa.data.models.*;
import semicolon.africa.data.repositories.UserRepository;
import semicolon.africa.dtos.reposonse.AuctionResponse;
import semicolon.africa.dtos.reposonse.BidResponse;
import semicolon.africa.dtos.reposonse.ProfileResponse;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.AuctionProductDto;
import semicolon.africa.dtos.request.BidDto;
import semicolon.africa.dtos.request.ProfileDto;
import semicolon.africa.dtos.request.RegisterDto;
import semicolon.africa.exceptions.EmailError;
import semicolon.africa.exceptions.PasswordError;
import semicolon.africa.security.JwtUtil;
import semicolon.africa.service.BidService;
import semicolon.africa.service.ProductService;
import semicolon.africa.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProductService productService;
    private final BidService bidService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, ProductService productService, BidService bidService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productService = productService;
        this.bidService = bidService;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public RegisterResponse register(RegisterDto registerDto) {
        if(registerDto == null){
            throw new IllegalArgumentException("All fields are required");
        }
        if (registerDto.getUserName() == null ||registerDto.getUserName().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (registerDto.getEmail() == null || !registerDto.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new EmailError("Valid email is required");
        }

        if (registerDto.getPassword() == null || registerDto.getPassword().length() < 6) {
            throw new PasswordError("Password must be at least 6 characters");
        }

        if(userRepository.existsByUserName(registerDto.getUserName())) throw new IllegalArgumentException("User In Use");


        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new EmailError("Email already in use");
        }

        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        registerDto.setPassword(encodedPassword);
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setUserName(registerDto.getUserName());
        userRepository.save(user);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setToken(jwtUtil.generateToken(user));
        return registerResponse;
    }

    @Override
    public ProfileResponse updateAddress(ProfileDto profileDto) {
        ProfileResponse profileResponse = new ProfileResponse();
        if (profileDto == null) {
            throw new IllegalArgumentException("Profile data cannot be null");
        }

        if (profileDto.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        if (profileDto.getRole() == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        Optional<User> user = userRepository.findById(profileDto.getUserId());
        if (user.isPresent()) {
            User presentUser  = user.get();
            Profile profile = presentUser.getProfile();
            if(profile == null) {
                profile = new Profile();
                profile.setAccountStatus(AccountStatus.UNVERIFIED);
                profile.setAddress(profileDto.getAddress());
                presentUser.setProfile(profile);
                userRepository.save(presentUser);
                profileResponse.setProfile(presentUser.getProfile());
                return profileResponse;
            }else{
                profile.setAddress(profileDto.getAddress());
                presentUser.setProfile(profile);
                userRepository.save(presentUser);
                profileResponse.setProfile(presentUser.getProfile());
                return profileResponse;
            }
        }

        throw new IllegalArgumentException("User not found");
    }


    @Override
    public ProfileResponse updateImage(String id, String url, String role) {
        ProfileResponse profileResponse = new ProfileResponse();
        if(url == null || Objects.equals(url, "")) {
            throw new IllegalArgumentException("Url cannot be null");
        }
        Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                Profile profile = user.get().getProfile();
                profile.setUrl(url);
                userRepository.save(user.get());
                profileResponse.setProfile(user.get().getProfile());
                return profileResponse;
            }

        throw new IllegalArgumentException("User not found");
    }


    @Override
    public AuctionResponse auctionProduct(AuctionProductDto addProductDto) {
        return productService.auctionProduct(addProductDto);
    }

    @Override
    public BidResponse bid(BidDto bidDto){
        return bidService.placeBid(bidDto);
    }


    @Override
    public List<Product> viewProduct() {
        return productService.viewAllProducts();
    }


    @Override
    public String highestBidder(String productId) {
        return bidService.highestBidder(productId);
    }


}
