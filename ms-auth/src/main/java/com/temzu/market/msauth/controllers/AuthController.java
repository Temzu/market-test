package com.temzu.market.msauth.controllers;

import com.temzu.market.msauth.dtos.AuthRequestDto;
import com.temzu.market.msauth.dtos.AuthResponseDto;
import com.temzu.market.msauth.dtos.SignUpRequestDto;
import com.temzu.market.msauth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public String signUp(@RequestBody SignUpRequestDto signUpRequest) {
    authService.signUp(signUpRequest);
    return "OK";
  }

  @PostMapping("/login")
  public AuthResponseDto login(@RequestBody AuthRequestDto request) {
    System.out.println("login");
    return authService.login(request);
  }
}
