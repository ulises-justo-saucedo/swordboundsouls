package com.SwordboundSouls.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hollow extends LivingBeing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHollow;
    @Column(unique = true)
    private String hollowName;
    private String hollowDescription;

    public Hollow(String aspect, int hp, int atk, int def, int reiatsu, int lvl, String hollowName, String hollowDescription) {
        super(aspect, hp, atk, def, reiatsu, lvl);
        this.hollowName = hollowName;
        this.hollowDescription = hollowDescription;
    }

}
