package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PspGatewayController {
	
	@Value("${application.version}")
	private String version;
	
	@Autowired
	private LoadBalancerClient client;
	
	
	@RequestMapping(value="/execute-payment/{psp}/{id}", method = RequestMethod.GET)	
	public String executePayment(@PathVariable("psp") String psp, @PathVariable("id") String id){							
		ServiceInstance service = client.choose(psp+"-client");
		return (new RestTemplate()).getForObject(service.getUri()+"/execute-payment/"+id,String.class);
	}
}
