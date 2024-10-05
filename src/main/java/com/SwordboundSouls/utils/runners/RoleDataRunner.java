package com.SwordboundSouls.utils.runners;

import com.SwordboundSouls.entity.Role;
import com.SwordboundSouls.helpers.Roles;
import com.SwordboundSouls.repository.IRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class RoleDataRunner implements CommandLineRunner {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.count() == 0){
            roleRepository.saveAll(
                    List.of(
                            new Role(Roles.USER),
                            new Role(Roles.ADMIN)
                    )
            );
        }
    }
}
