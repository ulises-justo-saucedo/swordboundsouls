package com.SwordboundSouls.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class CharacterFighting extends LivingBeing {
	@Id
	@Column(name = "characterName")
	private String characterName;
	@Column(name = "classType")
	private String classType;
	@Column(name = "xp")
	private int xp;
	@Column(name = "username")
	private String username; //FK - Connects with 'users' table
	@Transient
	private List<String> physicalSkills;
	@Transient
	private List<String> kidoSkills;
	@Transient
	private List<String> buffs;
	public CharacterFighting(String characterName, String classType, int xp, String username, String aspect, int hp, int atk, int def, int reiatsu, int lvl) {
		super(aspect,hp,atk,def,reiatsu,lvl);
		this.characterName = characterName;
		this.classType = classType;
		this.xp = xp;
		this.username = username;
		this.physicalSkills = new ArrayList<>();
		this.kidoSkills = new ArrayList<>();
		this.buffs = new ArrayList<>();
	}
	public CharacterFighting() {
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
	public void determineCharacterStatsAfterBuffing(String buff) {
		
	}
	public int determineCharacterDamage(String attackAction) {
		int dmg = 0;
		if(thisAttackIsPhysical(attackAction,physicalSkills)) {
			dmg = determinePhysicalDmg(attackAction,atk,reiatsu);
		}else {
			dmg = determineReiatsuDmg(attackAction,reiatsu);
		}
		return dmg;
	}
	public boolean thisAttackIsPhysical(String attackAction,List<String> physicalSkills) {
		boolean isPhysical = false;
		int i = 0;
		while(physicalSkills.size() > i && !isPhysical) {
			if(physicalSkills.get(i).equalsIgnoreCase(attackAction)) {
				isPhysical = true;
			}
			i++;
		}
		return isPhysical;
	}
	public int determinePhysicalDmg(String attackAction,int atk,int reiatsu) {
		int dmg = 0;
		switch(attackAction) {
		case "Zanpaku-tō":
			dmg = physicalDmgFormula(atk,reiatsu,0);
			break;
		}
		return dmg;
	}
	public int physicalDmgFormula(int atk,int reiatsu,int skillModifier) {
		return (int)(atk + ( (atk+skillModifier) * (reiatsu/10) ));
	}
	public int determineReiatsuDmg(String attackAction,int reiatsu) {
		int dmg = 0;
		switch(attackAction) {
		case "Shō":
			dmg = reiatsuDmgFormula(reiatsu,1);
		}
		return dmg;
	}
	public int reiatsuDmgFormula(int reiatsu,int skillModifier) {
		return (int)(reiatsu + ( (reiatsu+skillModifier) * (reiatsu/2) ));
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
