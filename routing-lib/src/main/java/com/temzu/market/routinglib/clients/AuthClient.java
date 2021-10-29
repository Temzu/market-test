package com.temzu.market.routinglib.clients;

import com.temzu.market.routinglib.dtos.AuthRequestDto;
import com.temzu.market.routinglib.dtos.AuthResponseDto;
import com.temzu.market.routinglib.dtos.SignUpRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient
public interface AuthClient {

  @PostMapping("/signup")
  String signUp(@RequestBody SignUpRequestDto signUpRequest);

  @PostMapping("/login")
  AuthResponseDto login(@RequestBody AuthRequestDto request);

}
