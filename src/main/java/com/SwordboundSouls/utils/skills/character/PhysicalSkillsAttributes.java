package com.SwordboundSouls.utils.skills.character;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class PhysicalSkillsAttributes {
    private String name;
    private int modifier;
    public int calculateDamage(int characterAtk, int characterReiatsu) {
        return (int) (characterAtk + ((characterAtk + modifier) * (characterReiatsu / 10)));
    }
}
