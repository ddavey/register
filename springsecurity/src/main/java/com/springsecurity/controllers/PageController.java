package com.springsecurity.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springsecurity.SessionConstants;
import com.springsecurity.user.User;
import com.springsecurity.user.UserService;

@Controller
public class PageController {
	@Autowired private UserService userService;
	
	@RequestMapping("/")
	public String getHomePage(HttpServletRequest request,HttpSession session){
		User user = userService.findByUsername(request.getRemoteUser());
		if(session.getAttribute(SessionConstants.SESSION_ATTR_USER)==null){
			session.setAttribute(SessionConstants.SESSION_ATTR_USER, user);
		}
		return "home";
	}
}
