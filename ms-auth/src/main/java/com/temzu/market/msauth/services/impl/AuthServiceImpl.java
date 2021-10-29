package com.temzu.market.msauth.services.impl;

import com.temzu.market.corelib.services.TokenService;
import com.temzu.market.msauth.dtos.AuthRequestDto;
import com.temzu.market.msauth.dtos.AuthResponseDto;
import com.temzu.market.msauth.dtos.SignUpRequestDto;
import com.temzu.market.corelib.model.UserInfo;
import com.temzu.market.msauth.entities.User;
import com.temzu.market.msauth.entities.mappers.UserMapper;
import com.temzu.market.msauth.services.AuthService;
import com.temzu.market.msauth.services.UserService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final UserMapper userMapper;
  private final TokenService tokenService;

  @Override
  public void signUp(SignUpRequestDto signUpRequestDto) {
    userService.save(userMapper.toUser(signUpRequestDto));
  }

  @Override
  public AuthResponseDto login(AuthRequestDto request) {
    User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());

    UserInfo userInfo = UserInfo.builder()
        .userId(user.getId())
        .userEmail(user.getLogin())
        .role(user.getRole().getName())
        .build();
    String token = tokenService.generateToken(userInfo);
    return new AuthResponseDto(token);
  }
}
