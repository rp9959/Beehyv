package com.beehyv.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.beehyv.model.OperatorCountBean;
import com.beehyv.repository.OperatorsCountRepository;


@Service
public class OperatorsCountService {


	
	@Autowired
	OperatorsCountRepository operatorrepo;
	
	
	@Transactional   //This method saves the Expression and UserId to the DB
	public void persist(int userid, String expression) {
		
		HashMap<Character, Integer> countmap= new HashMap<Character, Integer>();
		
		char[] operatorsList=  expression.toCharArray(); 
		OperatorCountBean oldoperatorbean = new OperatorCountBean();
		
		 oldoperatorbean= operatorrepo.findByUserid(userid);
			 
			 if(oldoperatorbean!= null)
				 countmap =oldoperatorbean.getCountmap() ;
				
			 			 
		for(char c: operatorsList)
		{	
				 if(countmap.containsKey(c))
			       countmap.put(c, countmap.get(c)+1);
						
				else	
				{
	     			if(c=='*'||c=='+'||c=='-'||c=='/')
					  countmap.put(c, 1);	
				}
				 							
		}

		OperatorCountBean operatorbean = new OperatorCountBean();
		operatorbean.setUserid(userid);
		operatorbean.setCountmap(countmap);
		operatorrepo.save(operatorbean);
				
		
	}
	
	
	
	//Method to Fetch the most Used Operator for a given UserId
	public ResponseEntity<Object> fetchHighestUsedOperator(int userid) {
		
		HashMap<Character, Integer> countmap= new HashMap<Character, Integer>();
		OperatorCountBean oldoperatorbean = new OperatorCountBean();
		 oldoperatorbean= operatorrepo.findByUserid(userid);
		 
		 if(oldoperatorbean!= null)
		 {
			 countmap =oldoperatorbean.getCountmap() ;
	
			
			 Optional<java.util.Map.Entry<Character, Integer>> maxEntry =   countmap.entrySet()
		        .stream()
		        .max(Comparator.comparing(Map.Entry::getValue));
			  
		    String str=  "The most used operator for the User: "+ userid+ " is "+ maxEntry.get().getKey() + " Operator ------>"+ maxEntry.get().getValue()+" times !!";
		    System.out.println(str);
		    return ResponseEntity.ok(str);
      				
        }
		    		
		 	 
		 
		 else
			 return new ResponseEntity<>("This User hasn't made any evaluations yet. Get an expression evaluated first !!",
	                 HttpStatus.BAD_REQUEST);
			 
		  
		
	}
	
	

}
