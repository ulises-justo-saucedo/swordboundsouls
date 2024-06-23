package com.SwordboundSouls.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
//@Table(name = "users")
public class Users {
	@Id
	@Column(name = "username")
	private String username;
	@Column(name = "pass")
	private String pass;
	@Column(name = "isadmin")
	private boolean isAdmin;
	public Users(String username, String pass, boolean isAdmin) {
		super();
		this.username = username;
		this.pass = pass;
		this.isAdmin = isAdmin;
	}
	public Users() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
