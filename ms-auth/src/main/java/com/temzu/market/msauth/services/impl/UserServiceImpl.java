package com.temzu.market.msauth.services.impl;

import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.corelib.exceptions.UserWrongPasswordException;
import com.temzu.market.msauth.entities.Role;
import com.temzu.market.msauth.entities.User;
import com.temzu.market.msauth.enums.UserRoles;
import com.temzu.market.msauth.repositories.UserRepository;
import com.temzu.market.msauth.services.RoleService;
import com.temzu.market.msauth.services.UserService;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleService roleService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User save(@NonNull User user) {
    Role role = roleService.findByName(UserRoles.ROLE_USER.name());
    user.setRole(role);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User findByLogin(@NonNull String login) {
    return userRepository.findByLogin(login)
        .orElseThrow(() -> ResourceNotFoundException.byLogin(login, User.class));
  }

  @Override
  public User findByLoginAndPassword(@NonNull String login, @NonNull String password) {
    return Optional.ofNullable(findByLogin(login))
        .filter(user -> passwordEncoder.matches(password, user.getPassword()))
        .orElseThrow(UserWrongPasswordException::new);
  }
}
