package com.springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springsecurity.utilities.Encryption;

@Controller
public class EncryptionController {
	@RequestMapping(value="/encrypt",method=RequestMethod.GET)
	public String getEncryptionPage(){
		return "encrypt";
	}
	
	@RequestMapping(value="/encrypt",method=RequestMethod.POST)
	public @ResponseBody String encryptValue(@RequestParam String toEncrypt){
		
		return Encryption.encrypt(toEncrypt);
	}
}
