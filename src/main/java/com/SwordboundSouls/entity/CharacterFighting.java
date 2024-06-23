package com.SwordboundSouls.entity;

import java.util.List;

public class CharacterFighting extends Character {
    public void reduceHp(int dmg) {
        setHp(getHp() - dmg);
    }

    public boolean isDead() {
        boolean isDead = false;
        if (getHp() <= 0) {
            isDead = true;
        }
        return isDead;
    }

    public void determineCharacterStatsAfterBuffing(String buff) {

    }

    public int determineCharacterDamage(String attackAction) {
        int dmg = 0;
        if (thisAttackIsPhysical(attackAction, super.getPhysicalSkills())) {
            dmg = determinePhysicalDmg(attackAction, atk, reiatsu);
        } else {
            dmg = determineReiatsuDmg(attackAction, reiatsu);
        }
        return dmg;
    }

    public boolean thisAttackIsPhysical(String attackAction, List<String> physicalSkills) {
        boolean isPhysical = false;
        int i = 0;
        while (physicalSkills.size() > i && !isPhysical) {
            if (physicalSkills.get(i).equalsIgnoreCase(attackAction)) {
                isPhysical = true;
            }
            i++;
        }
        return isPhysical;
    }

    public int determinePhysicalDmg(String attackAction, int atk, int reiatsu) {
        int dmg = 0;
        switch (attackAction) {
            case "Zanpaku-tō":
                dmg = physicalDmgFormula(atk, reiatsu, 0);
                break;
        }
        return dmg;
    }

    public int physicalDmgFormula(int atk, int reiatsu, int skillModifier) {
        return (int) (atk + ((atk + skillModifier) * (reiatsu / 10)));
    }

    public int determineReiatsuDmg(String attackAction, int reiatsu) {
        int dmg = 0;
        switch (attackAction) {
            case "Shō":
                dmg = reiatsuDmgFormula(reiatsu, 1);
        }
        return dmg;
    }

    public int reiatsuDmgFormula(int reiatsu, int skillModifier) {
        return (int) (reiatsu + ((reiatsu + skillModifier) * (reiatsu / 2)));
    }

}
