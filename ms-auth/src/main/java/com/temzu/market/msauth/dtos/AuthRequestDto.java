package com.temzu.market.msauth.dtos;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthRequestDto {

  @NonNull
  private String login;
  @NonNull
  private String password;
}
