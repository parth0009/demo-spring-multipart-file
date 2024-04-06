package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.dao.TesterDao;
import com.demo.model.Tester;
@Component
@Service
public class TesterServices {

	
	
	@Autowired
	TesterDao testerDao;
	
	
	
	//add tester
	public void addTester(Tester tester)
	{
		testerDao.addTester(tester);
	}
	
	//get all testers
	public List<Tester> getAllTester()
	{
		return testerDao.getAllTester();
	}
	
	
	//get tester by id
	
	public Tester getById(Long id)
	{
		return testerDao.getTesterById(id);
	}
	
	
	// update tester
	
	public void updateTester(Tester tester)
	{
		testerDao.updateTester(tester);
	}
	
	
	//delete tester 
	
	public void deleteTester(Long id)
	{
		testerDao.deleteTester(id);
	}
	
}