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
    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "idUser")
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

}
