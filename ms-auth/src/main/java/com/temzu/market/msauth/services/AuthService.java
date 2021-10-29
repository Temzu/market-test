package com.temzu.market.msauth.services;

import com.temzu.market.msauth.dtos.AuthRequestDto;
import com.temzu.market.msauth.dtos.AuthResponseDto;
import com.temzu.market.msauth.dtos.SignUpRequestDto;

public interface AuthService {

  void signUp(SignUpRequestDto signUpRequestDto);

  AuthResponseDto login(AuthRequestDto authRequestDto);
}
