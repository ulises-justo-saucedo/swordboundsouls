package com.SwordboundSouls.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class LivingBeing {
    protected String aspect; //URL to an Imgur's PNG
    protected int hp;
    protected int atk;
    protected int def;
    protected int reiatsu;
    protected int lvl;
    /*@ElementCollection
    protected List<String> skills;*/

}
