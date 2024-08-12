package com.SwordboundSouls.service;

import com.SwordboundSouls.entity.Role;
import com.SwordboundSouls.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private IRoleRepository roleRepository;

    public Role findByName(String name){
        return roleRepository.findByName(name).orElse(null);
    }
}
