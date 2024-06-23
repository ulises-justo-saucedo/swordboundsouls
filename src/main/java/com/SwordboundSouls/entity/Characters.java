package com.SwordboundSouls.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
//@Table(name = "characters")
public class Characters extends LivingBeing {
	@Id
	@Column(name = "characterName")
	private String characterName;
	@Column(name = "classType")
	private String classType;
	@Column(name = "xp")
	private int xp;
	@Column(name = "username")
	private String username; //FK - Connects with 'users' table
	@ElementCollection
	private List<String> physicalSkills;
	@ElementCollection
	private List<String> kidoSkills;
	@ElementCollection
	private List<String> buffs;
	public Characters(String characterName, String classType, int xp, String username, String aspect, int hp, int atk, int def, int reiatsu, int lvl) {
		super(aspect,hp,atk,def,reiatsu,lvl);
		this.characterName = characterName;
		this.classType = classType;
		this.xp = xp;
		this.username = username;
		this.physicalSkills = new ArrayList<>();
		this.kidoSkills = new ArrayList<>();
		this.buffs = new ArrayList<>();
	}
	public Characters() {
		super();
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public int getXp() {
		return xp;
	}
	public void setXp(int xp) {
		this.xp = xp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getPhysicalSkills() {
		return physicalSkills;
	}
	public void setPhysicalSkills(List<String> physicalSkills) {
		this.physicalSkills = physicalSkills;
	}
	public List<String> getKidoSkills() {
		return kidoSkills;
	}
	public void setKidoSkills(List<String> kidoSkills) {
		this.kidoSkills = kidoSkills;
	}
	public List<String> getBuffs(){
		return buffs;
	}
	public void setBuffs(List<String> buffs) {
		this.buffs = buffs;
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
	/*
	public List<String> getSkills(){
		return super.getSkills();
	}*/
}
