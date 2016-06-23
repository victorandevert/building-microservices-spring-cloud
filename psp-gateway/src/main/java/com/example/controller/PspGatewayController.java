package com.example.controller;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
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
	private DiscoveryClient client;
	
	@RequestMapping(value="/execute-payment/{psp}/{id}", method = RequestMethod.GET)	
	public String executePayment(@PathVariable("psp") String psp, @PathVariable("id") String id){		
		String response = null;				
		
		List<ServiceInstance> services = client.getInstances(psp+"-client");
	    if (services != null && services.size() > 0) {
	    	URI uri = services.get(0).getUri();
	    	if (uri != null) {
	    		response = (new RestTemplate()).getForObject(uri+"/execute-payment/"+id,String.class);	
			}else{
				response = HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
			}
	    }		
		return response;
	}
}
