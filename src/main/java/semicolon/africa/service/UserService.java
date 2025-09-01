package semicolon.africa.service;

import semicolon.africa.dtos.reposonse.ProfileResponse;
import semicolon.africa.dtos.request.ProfileDto;

public interface UserService {
    ProfileResponse updateAddress(ProfileDto profileDto);
    ProfileResponse updateImage(String id,String url,String role);
}
