package com.beehyv.contoller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beehyv.service.BodmasService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bodmas")
public class ApplicationController {
	
	@Autowired
	BodmasService bodmasservice;


	//END POINT that evaluates and persists the valid expressions
	 @PostMapping("/evaluate/{userid}/**")
	    public ResponseEntity<Object> evaluateAndPersist(HttpServletRequest request) {
		 
		 String fullUrl = request.getRequestURL().toString();
		    String exp = fullUrl.split("/evaluate/")[1];
		    String[] expression = exp.split("/",2);
		    
		   int userid= Integer.parseInt(expression[0]);
		  String str= expression[1];
			return bodmasservice.evaluateAndCount(userid, str);
		    
	 }
	 
	//END POINT that fetches the highest used operator a specific userId
		@GetMapping("/highestcount/{userid}")
	    public ResponseEntity<Object> getHighestCount(@PathVariable int userid) {
			
			return bodmasservice.getHighestCount(userid);    	
	 }
	 
	 
	

}
