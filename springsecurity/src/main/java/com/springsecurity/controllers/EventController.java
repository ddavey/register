package com.springsecurity.controllers;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springsecurity.SessionConstants;
import com.springsecurity.events.Event;
import com.springsecurity.events.Event.Type;
import com.springsecurity.events.EventService;
import com.springsecurity.user.User;

@Controller
public class EventController {
	@Autowired private EventService eventService;
	
	@RequestMapping(value="/events/list",method=RequestMethod.POST)
	public @ResponseBody List<Event> doAddWfhEvent(@RequestParam long start,@RequestParam long end,HttpSession session){
		User user = (User) session.getAttribute(SessionConstants.SESSION_ATTR_USER);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		List<Event> events = eventService.findByDateRangeAndUser(calendar.getTimeInMillis(), end, user);
		return events;
	}
	
	@RequestMapping(value="/events/wfh/add",method=RequestMethod.POST)
	public @ResponseBody Event doAddWfhEvent(@RequestParam long date,HttpSession session){
		User user = (User) session.getAttribute(SessionConstants.SESSION_ATTR_USER);
		Event event = new Event(date,user,Type.WFH);
		return eventService.save(event);
	}
	
	@RequestMapping(value="/events/wfh/delete",method=RequestMethod.POST)
	public @ResponseBody boolean doDeleteWfhEvent(@RequestParam Long id,HttpSession session){
		eventService.delete(id);
		return true;
	}
}
