package com.springsecurity.events;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.springsecurity.user.User;

@Embeddable
public class EventId implements Serializable{
	private long date;
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;
	
	public EventId(){}
	
	public EventId(long date,User user){
		this.date = date;
		this.user = user;
	}

	public long getDate() {
		return date;
	}

	void setDate(long date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	void setUser(User user) {
		this.user = user;
	}
	
	
}
