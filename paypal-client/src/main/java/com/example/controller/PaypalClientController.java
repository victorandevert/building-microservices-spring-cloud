package com.example.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaypalClientController {
	
	@Value("${application.version}")
	private String version;
	
	@RequestMapping(value ="/execute-payment/{id}", method = RequestMethod.GET)
	public String  executePayment(@PathVariable("id") String id) throws UnknownHostException{ 
		return "Hi! I'm Paypal v"+this.version +" ( "+InetAddress.getLocalHost().getHostName()+" ) | payment id: " + id;
	}
}
