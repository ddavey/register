package com.springsecurity.events;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.springsecurity.user.User;

@Entity
@Table(name="Events",uniqueConstraints={@UniqueConstraint(columnNames = { "date", "USERID"})})
public class Event {
	public static enum Type {WFH,BANK_HOLIDAY,HOLIDAY};
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private long date;
	
	private Type type;

	@ManyToOne
	@JoinColumn(name="USERID",nullable=true)
	private User user;
	
	public Event(){}
	
	public Event(long date,User user,Type type){
		this.date = date;
		this.user = user;
		this.type = type;
	}
	
	public Event(long id,long date,User user,Type type){
		this.id = id;
		this.date = date;
		this.user = user;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
