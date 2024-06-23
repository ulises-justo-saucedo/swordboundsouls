package com.SwordboundSouls.entity;

public class HollowFighting extends Hollow {
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

}
