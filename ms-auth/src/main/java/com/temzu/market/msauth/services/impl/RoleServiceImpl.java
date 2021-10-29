package com.temzu.market.msauth.services.impl;

import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.msauth.entities.Role;
import com.temzu.market.msauth.repositories.RoleRepository;
import com.temzu.market.msauth.services.RoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public Role findByName(@NonNull String name) {
    return roleRepository.findByName(name)
        .orElseThrow(() -> ResourceNotFoundException.byName(name, Role.class));
  }
}
