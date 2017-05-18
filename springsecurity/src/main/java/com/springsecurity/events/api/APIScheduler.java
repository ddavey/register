package com.springsecurity.events.api;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.springsecurity.LdapSearchRunnable;
import com.springsecurity.events.EventService;
import com.springsecurity.user.UserService;

public class APIScheduler extends ScheduledThreadPoolExecutor{

	public APIScheduler(UserService userService,EventService eventService) {
		super(2);
		scheduleWithFixedDelay(new LdapSearchRunnable(userService), 0, 10, TimeUnit.MINUTES);
		scheduleWithFixedDelay(new BankHolidayRunnable(eventService), 0, 30, TimeUnit.DAYS);
		
	}

}
