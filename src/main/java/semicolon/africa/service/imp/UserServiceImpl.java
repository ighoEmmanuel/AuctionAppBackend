package semicolon.africa.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.data.models.*;
import semicolon.africa.data.repositories.BidderRepository;
import semicolon.africa.data.repositories.SellerRepository;
import semicolon.africa.data.repositories.UserRepository;
import semicolon.africa.dtos.reposonse.ProfileResponse;
import semicolon.africa.dtos.request.ProfileDto;
import semicolon.africa.service.BidderService;
import semicolon.africa.service.UserService;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BidderService bidderService;
    private final BidderRepository bidderRepository;
    private UserRepository userRepository;
    private SellerRepository sellerRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BidderService bidderService, BidderRepository bidderRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.bidderService = bidderService;
        this.bidderRepository = bidderRepository;
        this.sellerRepository = sellerRepository;
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
        if("Bidder".equals(profileDto.getRole())) {
            Optional<Bidder> bidder = bidderRepository.findById(profileDto.getUserId());
            if (bidder.isPresent()) {
                Bidder presentBidder  = bidder.get();
                Profile profile = presentBidder.getProfile();
                if(profile == null) {
                    profile = new Profile();
                    profile.setAccountStatus(AccountStatus.UNVERIFIED);
                    profile.setAddress(profileDto.getAddress());
                    presentBidder.setProfile(profile);
                    bidderRepository.save(presentBidder);
                    profileResponse.setProfile(presentBidder.getProfile());
                    return profileResponse;
                }else{
                    profile.setAddress(profileDto.getAddress());
                    presentBidder.setProfile(profile);
                    bidderRepository.save(presentBidder);
                    profileResponse.setProfile(presentBidder.getProfile());
                    return profileResponse;
                }
            }
        }else{
            Optional<Seller> seller = sellerRepository.findById(profileDto.getUserId());
            if (seller.isPresent()) {
                Seller presentSeller = seller.get();
                Profile profile = presentSeller.getProfile();
                if(profile == null) {
                    profile = new Profile();
                    profile.setAccountStatus(AccountStatus.UNVERIFIED);
                    profile.setAddress(profileDto.getAddress());
                    presentSeller.setProfile(profile);
                    sellerRepository.save(presentSeller);
                    profileResponse.setProfile(presentSeller.getProfile());
                    return profileResponse;
                }else{
                    profile.setAddress(profileDto.getAddress());
                    presentSeller.setProfile(profile);
                    sellerRepository.save(presentSeller);
                    profileResponse.setProfile(presentSeller.getProfile());
                    return profileResponse;
                }
            }
        }
        throw new IllegalArgumentException("User not found");
    }


    @Override
    public ProfileResponse updateImage(String id, String url, String role) {
        ProfileResponse profileResponse = new ProfileResponse();
        if(Objects.equals(role, "") || role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if(url == null || Objects.equals(url, "")) {
            throw new IllegalArgumentException("Url cannot be null");
        }
        if("Bidder".equals(role)) {
            Optional<Bidder> bidder = bidderRepository.findById(id);
            if (bidder.isPresent()) {
                Profile profile = bidder.get().getProfile();
                profile.setUrl(url);
                bidderRepository.save(bidder.get());
                profileResponse.setProfile(bidder.get().getProfile());
                return profileResponse;
            }

        }
        if("Seller".equals(role)) {
            Optional<Seller> seller = sellerRepository.findById(id);
            if (seller.isPresent()) {
                Profile profile = seller.get().getProfile();
                profile.setUrl(url);
                sellerRepository.save(seller.get());
                profileResponse.setProfile(seller.get().getProfile());
                return profileResponse;
            }
        }
        throw new IllegalArgumentException("User not found");
    }


}
