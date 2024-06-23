package com.SwordboundSouls.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
//@Table(name = "hollows")
public class Hollows extends LivingBeing {
	@Id
	@Column(name = "hollowName")
	private String hollowName;
	@Column(name = "hollowDescription")
	private String hollowDescription;
	public Hollows(String aspect, int hp, int atk, int def, int reiatsu, int lvl, String hollowName, String hollowDescription) {
		super(aspect, hp, atk, def, reiatsu, lvl);
		this.hollowName = hollowName;
		this.hollowDescription = hollowDescription;
	}
	public Hollows() {
		super();
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
	//Superclass attributes - Getters
	public String getAspect() {
		return super.getAspect();
	}
	public int getHp() {
		return super.getHp();
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
	public int getLvl() {
		return super.getLvl();
	}
}
