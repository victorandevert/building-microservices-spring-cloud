package com.example.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("paypal-client")
public interface PaypalClient {
	
	@RequestMapping(value="/execute-payment/{id}", method = RequestMethod.GET)	
	public String executePayment(@PathVariable("id") String id);

}
