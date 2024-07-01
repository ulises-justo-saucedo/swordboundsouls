package com.SwordboundSouls.service;

import com.SwordboundSouls.entity.Role;
import com.SwordboundSouls.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name){
        return roleRepository.findByName(name).orElse(null);
    }
}
