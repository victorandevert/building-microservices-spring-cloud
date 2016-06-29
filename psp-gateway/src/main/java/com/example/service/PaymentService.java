package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.client.AdyenClient;
import com.example.client.KlarnaClient;
import com.example.client.PaypalClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PaymentService {
	
	@Autowired
	private AdyenClient adyenClient;
	
	@Autowired
	private KlarnaClient klarnaClient;
	
	@Autowired
	private PaypalClient paypalClient;

	@HystrixCommand(fallbackMethod="getFallbackPayment")
	public String executeAdyenPayment(final String id) {
		return adyenClient.executePayment(id);
	}
	
	@HystrixCommand(fallbackMethod="getFallbackPayment")
	public String executeKlarnaPayment(final String id) {
		return klarnaClient.executePayment(id);
	}
	
	@HystrixCommand(fallbackMethod="getFallbackPayment")
	public String executePaypalPayment(final String id) {
		return paypalClient.executePayment(id);
	}
		
	public String getFallbackPayment(final String id){
		return "Do other things like register the request in a queue to process the payment later. ID: " + id;
	}
	
}
