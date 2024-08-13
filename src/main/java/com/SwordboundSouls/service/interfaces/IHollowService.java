package com.SwordboundSouls.service.interfaces;

import com.SwordboundSouls.entity.Hollow;

import java.util.List;

public interface IHollowService {
    public Hollow getHollow(String hollowName);
    public Hollow getHollow(int idHollow);
    public List<Hollow> getAllHollows();
}
