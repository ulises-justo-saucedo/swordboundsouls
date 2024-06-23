package com.SwordboundSouls.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.MappedSuperclass;
@MappedSuperclass
public abstract class LivingBeing {
	@Column(name = "aspect")
	protected String aspect; //URL to an Imgur's PNG
	@Column(name = "hp")
	protected int hp;
	@Column(name = "atk")
	protected int atk;
	@Column(name = "def")
	protected int def;
	@Column(name = "reiatsu")
	protected int reiatsu;
	@Column(name = "lvl")
	protected int lvl;
	/*@ElementCollection
	protected List<String> skills;*/
	public LivingBeing(String aspect, int hp, int atk, int def, int reiatsu, int lvl) {
		super();
		this.aspect = aspect;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.reiatsu = reiatsu;
		this.lvl = lvl;
		//this.skills = new ArrayList<>();
	}
	public LivingBeing() {
		super();
	}
	public String getAspect() {
		return aspect;
	}
	public void setAspect(String aspect) {
		this.aspect = aspect;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getReiatsu() {
		return reiatsu;
	}
	public void setReiatsu(int reiatsu) {
		this.reiatsu = reiatsu;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	/*
	public List<String> getSkills(){
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}*/
}
