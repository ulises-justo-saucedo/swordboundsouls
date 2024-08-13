package com.SwordboundSouls.service.impl;

import com.SwordboundSouls.entity.Role;
import com.SwordboundSouls.repository.IRoleRepository;
import com.SwordboundSouls.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    public Role findByName(String name){
        return roleRepository.findByName(name).orElse(null);
    }
}
