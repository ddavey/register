package com.springsecurity.events;


import java.util.List;

import com.springsecurity.user.User;

public interface EventService {
	public Event save(Event event);
	
	public void delete(Long id);
	
	public List<Event> findByDateRangeAndUser(long startDate,long endDate,User user);
	
	public Event getLastBankHoliday();
}
