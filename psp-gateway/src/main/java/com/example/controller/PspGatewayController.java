package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.client.AdyenClient;
import com.example.client.KlarnaClient;
import com.example.client.PaypalClient;

@RestController
public class PspGatewayController {
	
	@Value("${application.version}")
	private String version;
	
	@Autowired
	private AdyenClient adyenClient;
	
	@Autowired
	private KlarnaClient klarnaClient;
	
	@Autowired
	private PaypalClient paypalClient;
	
	@RequestMapping(value="/execute-payment/{psp}/{id}", method = RequestMethod.GET)	
	public String executePayment(@PathVariable("psp") String psp, @PathVariable("id") String id){
		String message = null;
		switch (psp) {
			case "adyen":
				message = this.adyenClient.executePayment(id);
				break;
			case "klarna":
				message = this.klarnaClient.executePayment(id);
				break;
			case "paypal":
				message = this.paypalClient.executePayment(id);
				break;
		
			default:
				message = HttpStatus.NOT_FOUND.getReasonPhrase();
				break;
			}		
		return message;
	}	
}
