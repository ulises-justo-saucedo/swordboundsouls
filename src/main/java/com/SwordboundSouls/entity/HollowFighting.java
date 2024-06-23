package com.SwordboundSouls.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class HollowFighting extends LivingBeing {
	@Id
	@Column(name = "hollowName")
	private String hollowName;
	@Column(name = "hollowDescription")
	private String hollowDescription;
	public HollowFighting(String aspect, int hp, int atk, int def, int reiatsu, int lvl, String hollowName, String hollowDescription) {
		super(aspect, hp, atk, def, reiatsu, lvl);
		this.hollowName = hollowName;
		this.hollowDescription = hollowDescription;
	}
	public HollowFighting() {
		super();
	}
	
	public void reduceHp(int dmg) {
		setHp(getHp() - dmg);
	}
	public boolean isDead() {
		boolean isDead = false;
		if(getHp() <= 0) {
			isDead = true;
		}
		return isDead;
	}
	
	public String getHollowName() {
		return hollowName;
	}
	public void setHollowName(String hollowName) {
		this.hollowName = hollowName;
	}
	public String getHollowDescription() {
		return hollowDescription;
	}
	public void setHollowDescription(String hollowDescription) {
		this.hollowDescription = hollowDescription;
	}
	//Superclass attributes - Getters and Setters
	public String getAspect() {
		return super.getAspect();
	}
	
	public int getHp() {
		return super.getHp();
	}
	public void setHp(int hp) {
		super.setHp(hp);
	}
	public int getAtk() {
		return super.getAtk();
	}
	public int getDef() {
		return super.getDef();
	}
	public int getReiatsu() {
		return super.getReiatsu();
	}
	public void setReiatsu(int reiatsu) {
		super.setReiatsu(reiatsu);
	}
	public int getLvl() {
		return super.getLvl();
	}
}
