package com.SwordboundSouls.utils.runners;

import com.SwordboundSouls.entity.KidoSkill;
import com.SwordboundSouls.entity.PhysicalSkill;
import com.SwordboundSouls.repository.IKidoSkillRepository;
import com.SwordboundSouls.repository.IPhysicalSkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SkillDataRunner implements CommandLineRunner {
    @Autowired
    private IPhysicalSkillRepository physicalSkillRepository;

    @Autowired
    private IKidoSkillRepository kidoSkillRepository;

    @Override
    public void run(String... args) throws Exception {
        if(physicalSkillRepository.count() == 0)
            physicalSkillRepository.save(new PhysicalSkill(1, "Zanpaku-tō", 1));

        if(kidoSkillRepository.count() == 0)
            kidoSkillRepository.save(new KidoSkill(1, "Shō", 2));
    }
}
