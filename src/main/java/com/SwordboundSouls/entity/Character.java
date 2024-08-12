package com.SwordboundSouls.entity;

import java.util.ArrayList;
import java.util.List;
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
    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "character_phys_skills")
    private List<PhysicalSkill> physicalSkills;
    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "character_kido_skills")
    private List<KidoSkill> kidoSkills;
    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "character_buff_skills")
    private List<BuffSkill> buffs;

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

}
