package semicolon.africa.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semicolon.africa.dtos.reposonse.LogInResponse;
import semicolon.africa.dtos.request.LoginDto;
import semicolon.africa.service.AuthLoginService;
import semicolon.africa.service.imp.AuthLoginServiceImp;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthLoginService authLoginService;

    public AuthenticationController(AuthLoginServiceImp authLoginServiceImpl){
        this.authLoginService = authLoginServiceImpl;
    };
//
    @PostMapping("/login")
    public LogInResponse login(@Valid @RequestBody LoginDto loginDto) {
        return authLoginService.login(loginDto);
    }


}
