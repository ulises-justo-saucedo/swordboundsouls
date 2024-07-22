package com.SwordboundSouls.utils.skills.character;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class KidoSkillsAttributes {
    private String name;
    private int modifier;
    public int calculateDamage(int characterReiatsu) {
        return (int) (characterReiatsu + ((characterReiatsu + modifier) * (characterReiatsu / 5)));
    }
}
