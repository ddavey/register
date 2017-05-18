package com.springsecurity.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsecurity.user.User;

@Service
public class EventServiceImpl implements EventService{
	@Autowired private EventRepositiory eventRepo;
	
	@Transactional
	public Event save(Event event){
		return eventRepo.save(event);
	}
	
	@Transactional
	public void delete(Long id){
		eventRepo.delete(id);
	}
	
	public List<Event> findByDateRangeAndUser(long startDate,long endDate,User user){
		return eventRepo.findByUserOrUserIsNullAndDateBetween(user, startDate, endDate);
	}
	
	public Event getLastBankHoliday(){
		Pageable pageable = new PageRequest(0,1,Sort.Direction.DESC,"date");
		
		List<Event> events = eventRepo.findByUserIsNull(pageable).getContent();
		if(events.size()==0){
			return null;
		}else{
			return events.get(0);
		}
	}
}
