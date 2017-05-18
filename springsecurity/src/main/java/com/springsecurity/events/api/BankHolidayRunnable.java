package com.springsecurity.events.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.log4j.Logger;

import com.springsecurity.events.Event;
import com.springsecurity.events.EventService;

public class BankHolidayRunnable implements Runnable{
	private Logger logger = LoggerFactory.getLogger(getClass());
	private EventService eventService;
	
	BankHolidayRunnable(EventService eventService){
		this.eventService = eventService;
	}
	
	@Override
	public void run() {
		Event event = eventService.getLastBankHoliday();
        List<Event> bankHolidays = BankHolidaysApi.getInstance().list();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        boolean bankHolidaysAdded = false;
        for(Event bankHoliday : bankHolidays){
        	if(event==null || event.getDate() < bankHoliday.getDate()){
        		eventService.save(bankHoliday);
        		calendar.setTimeInMillis(bankHoliday.getDate());
        		logger.info("Added bank holiday for date: "+sdf.format(calendar.getTime()));
        		bankHolidaysAdded = true;
        	}
        }
        calendar.setTimeInMillis(event.getDate());
        if(!bankHolidaysAdded){
        	logger.info("No new bank holiday(s) found after: "+sdf.format(calendar.getTime()));
        }
	}

}
