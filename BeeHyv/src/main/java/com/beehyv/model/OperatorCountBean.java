package com.beehyv.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;


//Model Object used in Table creation of DB
@Transactional
@Entity
@Table(name="OperatorsCount")
public class OperatorCountBean {
	
	 @Id	 
	 @Column(name="UserId")
	 private int userid;
	 
	private HashMap<Character,Integer> countmap;


	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

		public HashMap<Character, Integer> getCountmap() {
		return countmap;
	}
	public void setCountmap(HashMap<Character, Integer> countmap) {
		this.countmap = countmap;
	}
	
	@Override
	public String toString() {
		return "OperatorCountBean [ userid=" + userid + ", countmap=" + countmap + "]";
	}
	
	
	

}
