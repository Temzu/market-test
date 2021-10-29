package com.temzu.market.msauth.services;

import com.temzu.market.msauth.entities.Role;

public interface RoleService {

  Role findByName(String name);
}
