package com.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.demo.model.Tester;

@Component
public class TesterDao {

	
	@Autowired
	HibernateTemplate hiberneteTemplate;
	
	
	//add employee
	@Transactional
	public void addTester(Tester tester)
	{
		hiberneteTemplate.save(tester);
	}
	
	
	//get all employee
	public List<Tester> getAllTester()
	{
		return hiberneteTemplate.loadAll(Tester.class);
	}
	
	//get Tester by id
	@Transactional
	public Tester getTesterById(Long id)
	{
		
		
		Tester tester= hiberneteTemplate.get(Tester.class, id);
		return tester;
	}
	
	
	//update employee
	
	@Transactional
	public void updateTester(Tester tester)
	{
		hiberneteTemplate.update(tester);
	}
	
	
	//delete employee
	@Transactional
	public void deleteTester(Long id)
	{
		hiberneteTemplate.delete(hiberneteTemplate.load(Tester.class, id));
	}
	
}