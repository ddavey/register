package com.springsecurity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springsecurity.user.User;
import com.springsecurity.user.UserService;
import com.springsecurity.user.UserServiceImpl;

public class LdapSearchRunnable implements Runnable{
	private UserService userService;
	
	
	public LdapSearchRunnable(UserService userService){
		this.userService=userService;
	}
	
	public void run() {
		while(true){
			updateMembersOfLdapGroup();
			try {
				Thread.sleep((60000*60));
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	private void updateMembersOfLdapGroup(){
		List<User> people = new LdapSearch().listPeople();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mmaa");
		for(User person : people){
			userService.save(person);
		}
	}
}
