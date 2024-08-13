package com.SwordboundSouls.service.interfaces;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.entity.Hollow;

public interface IFightService {
    public void setUpEntities(Character character, Hollow hollow);

    public void cleanEntities();

    public void performTurn(String buff, String attack);

    public void buffCharacter(String buff);

    public void reduceCharacterHp();

    public void reduceHollowHp(String attack);

    public boolean characterWon();

    public boolean hollowWon();
}
