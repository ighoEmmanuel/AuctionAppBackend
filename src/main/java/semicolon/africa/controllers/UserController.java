package semicolon.africa.controllers;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semicolon.africa.dtos.reposonse.ProfileResponse;
import semicolon.africa.dtos.request.ProfileDto;
import semicolon.africa.service.UserService;
import semicolon.africa.service.imp.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @PostMapping("/update/address")
    public ProfileResponse updateAddress(@Valid @RequestBody ProfileDto profileDto) {
        return userService.updateAddress(profileDto);
    }

//    @PostMapping


}
