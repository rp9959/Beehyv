package com.beehyv.service;




import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;





@Service
public class BodmasService {
	

	@Autowired
	EvaluationService evaluationservice;
	
	@Autowired
	ValidationService validationservice;
	
	@Autowired
	OperatorsCountService operatorservice;
	

	//This method evalues the expreession if it is valid and returns the result to browser
	//and also saves the Expression details in DB
	@Transactional
	public ResponseEntity<Object> evaluateAndCount(int userid, String expression) {	
		
			char[] tokens = expression.toCharArray();

	        char[] validated = validationservice.validate(tokens);

	        if (validated[0] == 'E') {
	            System.out.println("Review the Expression then Try Again");
	            
	            return new ResponseEntity<>("Paranthesis are not balanced/Invalid expresion. Enter a valid Expression !!",
	                    HttpStatus.BAD_REQUEST);
	        }

	        else {
	        	operatorservice.persist(userid,expression);
	            return ResponseEntity.ok(
	            		
	            		evaluationservice.evaluate(validated));
	            
	        }
	    }
	
	
	public ResponseEntity<Object> getHighestCount(int userid) {	
		
		return operatorservice.fetchHighestUsedOperator(userid);
	}

    
}
	


