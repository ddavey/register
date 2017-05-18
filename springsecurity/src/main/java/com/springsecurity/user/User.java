package com.springsecurity.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User{
	@Id
	private String uuid;
	private String username;
	private String fn;
	private String sn;
	
	public User(String uuid,String username,String fn,String sn){
		this.uuid = uuid;
		this.username = username;
		this.fn = fn;
		this.sn = sn;
	}
	
	public User(){}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public String getFn() {
		return fn;
	}

	public String getSn() {
		return sn;
	}

	@Override
	public String toString() {
		return "Person [uuid=" + uuid + ", username=" + username + ", fn=" + fn + ", sn=" + sn + "]";
	}
	
}
