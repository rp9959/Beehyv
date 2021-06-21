package com.beehyv.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.beehyv.model.OperatorCountBean;




@Repository
public interface OperatorsCountRepository extends JpaRepository<OperatorCountBean, Integer>{
	OperatorCountBean findByUserid(int userid) ;
	

}
