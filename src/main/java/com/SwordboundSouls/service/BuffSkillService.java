package com.SwordboundSouls.service;

import com.SwordboundSouls.repository.IBuffSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuffSkillService {
    @Autowired
    private IBuffSkillRepository buffSkillRepository;

}
