package com.temzu.market.msauth.services;

import com.temzu.market.msauth.entities.User;

public interface UserService {

  User save(User user);

  User findByLogin(String login);

  User findByLoginAndPassword(String login, String password);
}
