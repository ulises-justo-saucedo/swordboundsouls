package com.SwordboundSouls.entity;

import java.util.ArrayList;
import java.util.List;

import com.SwordboundSouls.utils.skills.character.KidoSkills;
import com.SwordboundSouls.utils.skills.character.KidoSkillsAttributes;
import com.SwordboundSouls.utils.skills.character.PhysicalSkills;
import com.SwordboundSouls.utils.skills.character.PhysicalSkillsAttributes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "characters")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Character extends LivingBeing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCharacter;
    @Column(unique = true)
    private String characterName;
    private String classType;
    private int xpLimit;
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
        super(aspect, hp, atk, def, reiatsu, lvl, xp);
        this.characterName = characterName;
        this.classType = classType;
        this.xpLimit = calculateXpLimit();
        this.user = user;
        this.physicalSkills = new ArrayList<>();
        this.kidoSkills = new ArrayList<>();
        this.buffs = new ArrayList<>();
    }

    public void incrementXp(int xp){
        setXp(xp + getXp());
    }

    public int calculateXpLimit(){
        return (int) (5 * Math.pow(getLvl(), 3));
    }

    public void determineCharacterStatsAfterBuffing(String buff) {

    }

    public int determineCharacterDamage(String attackAction) {
        int dmg = determinePhysicalDmg(attackAction);

        if(dmg == -1)
            dmg = determineReiatsuDmg(attackAction);

        return dmg;
    }

    public int determinePhysicalDmg(String attackAction) {
        int dmg = -1;
        int i = 0;

        while(dmg == -1 && i < PhysicalSkills.SKILLS.size()){
            PhysicalSkillsAttributes p = PhysicalSkills.SKILLS.get(i);

            if(p.getName().equalsIgnoreCase(attackAction))
                dmg = p.calculateDamage(atk, reiatsu);

            i++;
        }

        return dmg;
    }

    public int determineReiatsuDmg(String attackAction) {
        int dmg = -1;
        int i = 0;

        while(dmg == -1 && i < KidoSkills.SKILLS.size()){
            KidoSkillsAttributes k = KidoSkills.SKILLS.get(i);

            if(k.getName().equalsIgnoreCase(attackAction))
                dmg = k.calculateDamage(reiatsu);

            i++;
        }

        return dmg;
    }
}
