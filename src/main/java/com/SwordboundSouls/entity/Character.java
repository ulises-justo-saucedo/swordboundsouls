package com.SwordboundSouls.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "characters")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Character extends LivingBeing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCharacter;
    @Column(unique = true)
    private String characterName;
    private String classType;
    private int xp;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;
    @ElementCollection
    private List<String> physicalSkills;
    @ElementCollection
    private List<String> kidoSkills;
    @ElementCollection
    private List<String> buffs;

    public Character(String characterName, String classType, int xp, User user, String aspect, int hp, int atk, int def, int reiatsu, int lvl) {
        super(aspect, hp, atk, def, reiatsu, lvl);
        this.characterName = characterName;
        this.classType = classType;
        this.xp = xp;
        this.user = user;
        this.physicalSkills = new ArrayList<>();
        this.kidoSkills = new ArrayList<>();
        this.buffs = new ArrayList<>();
    }

    public void determineCharacterStatsAfterBuffing(String buff) {

    }

    public int determineCharacterDamage(String attackAction) {
        int dmg = 0;
        if (thisAttackIsPhysical(attackAction, getPhysicalSkills())) {
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
