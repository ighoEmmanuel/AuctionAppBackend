package semicolon.africa.service;

import semicolon.africa.dtos.reposonse.LogInResponse;
import semicolon.africa.dtos.request.LoginDto;

public interface AuthLoginService {
     LogInResponse login (LoginDto loginRequestDto);
}
