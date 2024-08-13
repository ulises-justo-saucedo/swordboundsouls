package com.SwordboundSouls.service.impl;

import com.SwordboundSouls.repository.IBuffSkillRepository;
import com.SwordboundSouls.service.interfaces.IBuffSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuffSkillService implements IBuffSkillService {
    @Autowired
    private IBuffSkillRepository buffSkillRepository;

}
