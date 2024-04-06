package com.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.demo.controller.EmployeeValidator;

import lombok.Data;
import lombok.ToString;

@ToString
@Component
public class Employee extends EmployeeValidator{
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotEmpty(message="Name is required")
	private String name;
	
	@NotBlank(message="Department is required")
	@Size(message = "Department is invalid", min = 10, max = Integer.MAX_VALUE,  groups = Default.class)
	private String department;
	
	@NotEmpty(message="Address is required")
	private String address;
	
	private Double salary;
	
	//@NotBlank(message="Designation is required")
	@Nullable
	@Size(message = "Designation Invalid", min = 0, groups = Default.class)
	private String designation;
	
	@NotEmpty(message="Array is empty")
	private String[] testArray = {" ", "  "};
	
	
	// This here is the issue. 
	
	//private Map<Integer, MultipartFile> fileMap = new HashMap<Integer, MultipartFile>();
    //private Map<Integer, String> nameMap = new HashMap<Integer, String>();

    
    //public void setFile(int index, MultipartFile file) {
    //    fileMap.put(new Integer(index), file); 
    //}          
    //public MultipartFile[] getFiles() {
    //    return (MultipartFile[]) fileMap.values().toArray(new MultipartFile[fileMap.size()]);
    //}     
    //public void setDocumentType(int index, String name) { 
    //    nameMap.put(new Integer(index), name); 
    //}          
    //public String[] getDocumentTypes() { 
    //    return (String[]) nameMap.values().toArray(new String[0]); 
    //}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String[] getTestArray() {
		return testArray;
	}
	public void setTestArray(String[] testArray) {
		this.testArray = testArray;
	}
	
	@Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        if (StringUtils.containsWhitespace(employee.getDesignation())) {
            errors.rejectValue("designation","", "Designation is required");
            //errors.add("region", new LegacyActionMessage("input.error.region.required", true));
        }
    }
	
	
	

}