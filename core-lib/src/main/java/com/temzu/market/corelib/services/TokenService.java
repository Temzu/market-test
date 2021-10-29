package com.temzu.market.corelib.services;

import com.temzu.market.corelib.model.UserInfo;

public interface TokenService {

  String generateToken(UserInfo user);

  UserInfo parseToken(String token);
}
