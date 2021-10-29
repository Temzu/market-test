package com.temzu.market.msauth.entities.mappers;

import com.temzu.market.msauth.entities.User;
import com.temzu.market.routinglib.dtos.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final ModelMapper mapper;

  public User toUser(SignUpRequestDto signUpRequestDto) {
    return mapper.map(signUpRequestDto, User.class);
  }
}
