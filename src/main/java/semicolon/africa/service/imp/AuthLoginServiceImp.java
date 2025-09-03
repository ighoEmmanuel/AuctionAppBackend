package semicolon.africa.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import semicolon.africa.data.models.User;
import semicolon.africa.data.repositories.UserRepository;
import semicolon.africa.dtos.reposonse.LogInResponse;
import semicolon.africa.dtos.request.LoginDto;
import semicolon.africa.exceptions.EmailError;
import semicolon.africa.exceptions.PasswordError;
import semicolon.africa.security.JwtUtil;
import semicolon.africa.service.AuthLoginService;

import java.util.Optional;
@Service
public class AuthLoginServiceImp implements AuthLoginService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthLoginServiceImp(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

        this.jwtUtil = jwtUtil;
    }

    @Override
    public LogInResponse login(LoginDto loginDto) {
        LogInResponse logInResponse = new LogInResponse();
        String email = loginDto.getEmail();
        String rawPassword = loginDto.getPassword();

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User owner = user.get();
            if (passwordEncoder.matches(rawPassword, owner.getPassword())) {
                LogInResponse loginResponse = new LogInResponse();
                loginResponse.setToken(jwtUtil.generateToken(owner));
                return loginResponse;
            }
            throw new PasswordError("Invalid password or email");
        }

        throw new EmailError("Email Not found");
    }

}
