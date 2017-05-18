package com.springsecurity.events.api;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
//import org.apache.log4j.Logger;

import com.springsecurity.events.Event;
import com.springsecurity.events.Event.Type;

public class BankHolidaysApi {
	//private Logger logger = Logger.getLogger(getClass());
	private final HttpClient httpClient = HttpClientBuilder.create().build();
	HttpGet get = new HttpGet("https://www.gov.uk/bank-holidays.json");
	private static BankHolidaysApi instance;
	
	private BankHolidaysApi(){}
	
	public static BankHolidaysApi getInstance(){
		if(instance==null){
			instance = new BankHolidaysApi();
		}
		return instance;
	}
	
	public List<Event> list(){
		List<Event> events = new ArrayList<Event>();
		HttpResponse response;
		String strResponse = null;
		try{
		response = httpClient.execute(get);
		strResponse = EntityUtils.toString(response.getEntity(),"UTF-8");
		}catch(IOException e){
			//logger.error("Unable to connect to Bank Holiday API: "+e.getMessage());
			return events;
		}
		JsonReader reader = Json.createReader(new StringReader(strResponse));
		JsonObject jsonObject = reader.readObject();
		JsonArray holidaysArray = jsonObject.getJsonObject("england-and-wales").getJsonArray("events");
		JsonObject holidayJson;
		Event event;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(holidaysArray.size());
			for(int index=0;index < holidaysArray.size();index++){
				holidayJson = holidaysArray.getJsonObject(index);
				try {
					System.out.println(sdf.parse(holidayJson.getString("date")) + " "+holidayJson.getString("title")+" "+sdf.parse(holidayJson.getString("date")).getTime());
					event = new Event(sdf.parse(holidayJson.getString("date")).getTime(),null,Type.BANK_HOLIDAY);
					events.add(event);
				} catch (java.text.ParseException e) {
					// not expected
				};
			}
		
		return events;
		
	
	}
}
