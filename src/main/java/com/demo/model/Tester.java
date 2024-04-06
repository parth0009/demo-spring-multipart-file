package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import com.demo.annotations.LengthConstraint;
import com.demo.annotations.MinLengthLong;
import com.demo.annotations.NullablePattern;
import com.demo.annotations.OptionalPattern;
import com.demo.annotations.RequiredInteger;
import com.demo.controller.TesterValidator;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Component
public class Tester extends TesterValidator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Required
	@NotBlank(message = "required is Required")
	private String required;

	// Required, minLength, type Integer
	//@NotBlank(message="requiredMinLengthInteger is required")
	//@Pattern(regexp = "^(?:(?=\\S)(?:(?!0\\d*$)\\d{5,10}))?$", message = "requiredMinLengthInteger is invalid")
	
	@NotBlank(message="requiredMinLengthInteger is required")
	@Pattern(regexp = "^(?:\\d{9,})?$", message = "reqioredMinLengthInteger is invalid using the pattern tag")
	@LengthConstraint(minLength=9, type = Integer.class, message="requiredMinLength is invalid using LengthConstraint")
	private String requiredMinLengthInteger;

	// minLength, Long (optional)
	//@Pattern(regexp = "^(?!\\s)(?:(?=.{10,})\\d+)?$", message = "minLengthLongOptional is invalid")
	@Pattern(regexp="^(?:\\d{5,})?$", message="minLengthLongOptional is invalid using the pattern tag")
	@LengthConstraint(minLength = 5, type = Long.class, message="minLengthLongOptional is invalid using LengthConstraint")
	private String minLengthLongOptional;

	// Required, minLength
	//@Pattern(regexp = "^.{0}|.{8,}$", message = "requiredMinLength is invalid")
	@NotBlank(message="requiredMinLength is required")
	@Pattern(regexp="^(?:\\d{9,})?$", message="requiredMinLength is invalid using the pattern tag")
	@LengthConstraint(minLength=9, message ="requiredMinLength is invalid using LengthConstraint")
	private String requiredMinLength;

	// Email
	@Email(message = "email is invalid")
	private String email;

	// minLength - need to test
	//@Pattern(regexp = "^(?:\s*.{0,4}\s|\s{5,})$", message = "minLengthVar is invalid")
	@Pattern(regexp="^(?:[a-zA-Z0-9]{6,}|)$", message="minLengthVar is invalid using the pattern tag")
	@LengthConstraint(minLength = 6, message="minLengthVar is invalid using the LengthConstraint")
	private String minLengthVar;

	// minLength, Integer type - need to test
	//@Pattern(regexp = "^(?:(?=\\\\S)(?:(?!0\\\\d*$)\\\\d{5,10}))?$", message = "minLengthInteger is invalid")
	@Pattern(regexp="^(?:\\d{3,})?$", message="minLengthInteger is invalid using the pattern tag")
	@LengthConstraint(minLength=3, type=Integer.class, message="minLengthInteger is invalid using the LengthConstraint")
	private String minLengthInteger;

	// maxLength - Need to test
	//@Pattern(regexp = "^(?!\\s*$)(.|.{1,10})$", message = "maxLengthVar is invalid")
	@Pattern(regexp="^(?:[a-zA-Z0-9]{0,8}|)$", message="maxLengthVar is invalid using the pattern tag")
	@LengthConstraint(maxLength = 8, message="maxLengthVar is invalid using the LengthConstraint")
	private String maxLengthVar;

	// Pattern - just reusing the pattern from maxLengthVar
	@Pattern(regexp="^$|^(?!\\\\s*$)(.|.{1,10})$", message="pattern is invalid")
	@OptionalPattern(regexp="^$|^(?!\\\\s*$)(.|.{1,10})$", message="pattern is invalid using the optionalPattern tag")
	private String pattern;

	// Required, Email
	// With the two tags below, if an invalid email is entered, the required email
	// validation also triggers
	@NotBlank(message = "requiredEmail is required")
	@Email(message = "requiredEmail is invalid")
	private String requiredEmail;

	// Required, Long - Need testing
	//@Pattern(regexp = "^(?:(?=\\S)(?!0\\d*$)(-?\\d{1,19}))?$", message = "requiredLong is invalid")
	@NotBlank(message = "requiredLong is required")
	@Pattern(regexp="^[0-9]*$", message="requiredLong is invalid using the pattern tag")
	@LengthConstraint(type=Long.class, message="requiredLong is invalid using the Length Constraint")
	private String requiredLong;

	// Pattern, maxLength - Needs testing
	// This is going to be tricky. How to have pattern with masking separate from
	// pattern with maxLength separate.
	//@Pattern(regexp = "^(?:[0-9A-Za-z\\-\\s]+|(?!\\s*$).{0,9})$", message = "patternMaxLength is invalid")
	@LengthConstraint(maxLength = 9, message="patternMaxLength is invalid using the LengthConstraint")
	@OptionalPattern(regexp="^[0-9A-Za-z\\\\-\\\\s]+$", message="patternMaxLength is invalid using the Optional Pattern tag")
	@Pattern(regexp="^[0-9A-Za-z\\-\\s]+$", message="patternMaxLength is invalid using the Pattern")
	private String patternMaxLength;

	// Required, Pattern - Needs testing
	// Used a random pattern
	@NotBlank(message = "requiredPattern is required")
	@Pattern(regexp = "^[0-9A-Za-z\\-\\s]+$", message = "requiredPattern is invalid")
	@OptionalPattern(regexp = "^[0-9A-Za-z\\\\-\\\\s]+$", message="requiredPattern is invalid using the optional Pattern tag")
	private String requiredPattern;

	// Integer - needs testing
	//@Pattern(regexp = "^(?:(?=\\S)(?!0\\d*$)\\d+)?$", message = "integerVar is invalid")
	@Pattern(regexp="^[0-9]*$", message="integerVar is invalid using the pattern tag")
	@LengthConstraint(type=Integer.class, message="integerVar is invalid using the LengthConstraint")
	private String integerVar;

	// Long - needs testing
	//@Pattern(regexp = "^(?:(?=\\S)(?!0\\d*$)-?\\d{1,18})?$", message="longVar is invalid")
	@Pattern(regexp="^[0-9]*$", message="longVar is invalid using the pattern tag")
	@LengthConstraint(type=Long.class, message="longVar is invalid using the LengthConstraint")
	private String longVar;

	// Required, minLength, Long
	//@Pattern(regexp = "^(?:(?=\\S)(?:(?!0\\d*$)-?\\d{5,}))?$", message="requireMinLengthLong is invalid")
	@NotBlank(message = "requiredMinLengthLong is required")	
	@Pattern(regexp="^(?:\\d{3,})?$", message="requiredMinLengthLong is invalid using the pattern tag")
	@LengthConstraint(minLength = 5, type = Long.class, message="requiredMinLengthLong is invalid using the Length Constraint")
	private String requiredMinLengthLong;

	
	
	
	
	
	
	
	
	
	// ---- Below will be completed later ----- //

	// Required, String with arg0Value
	// -----private String requiredStringWithArgVal;

	// Required, String[] return type
	// -----private String[] stringArray;

	// Required, MultiPartFile
	// This was changed from FormFile of Struts to MultipartFile of Spring. Lets
	// make sure attachment upload work too.
	// -----private MultipartFile fileSpring;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getRequiredMinLengthInteger() {
		return requiredMinLengthInteger;
	}

	public void setRequiredMinLengthInteger(String requiredMinLengthInteger) {
		this.requiredMinLengthInteger = requiredMinLengthInteger;
	}

	public String getMinLengthLongOptional() {
		return minLengthLongOptional;
	}

	public void setMinLengthLongOptional(String minLengthLongOptional) {
		this.minLengthLongOptional = minLengthLongOptional;
	}

	public String getRequiredMinLength() {
		return requiredMinLength;
	}

	public void setRequiredMinLength(String requiredMinLength) {
		this.requiredMinLength = requiredMinLength;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMinLengthVar() {
		return minLengthVar;
	}

	public void setMinLengthVar(String minLengthVar) {
		this.minLengthVar = minLengthVar;
	}

	public String getMinLengthInteger() {
		return minLengthInteger;
	}

	public void setMinLengthInteger(String minLengthInteger) {
		this.minLengthInteger = minLengthInteger;
	}

	public String getMaxLengthVar() {
		return maxLengthVar;
	}

	public void setMaxLengthVar(String maxLengthVar) {
		this.maxLengthVar = maxLengthVar;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getRequiredEmail() {
		return requiredEmail;
	}

	public void setRequiredEmail(String requiredEmail) {
		this.requiredEmail = requiredEmail;
	}

	public String getRequiredLong() {
		return requiredLong;
	}

	public void setRequiredLong(String requiredLong) {
		this.requiredLong = requiredLong;
	}

	public String getPatternMaxLength() {
		return patternMaxLength;
	}

	public void setPatternMaxLength(String patternMaxLength) {
		this.patternMaxLength = patternMaxLength;
	}

	public String getRequiredPattern() {
		return requiredPattern;
	}

	public void setRequiredPattern(String requiredPattern) {
		this.requiredPattern = requiredPattern;
	}

	public String getIntegerVar() {
		return integerVar;
	}

	public void setIntegerVar(String integerVar) {
		this.integerVar = integerVar;
	}

	public String getLongVar() {
		return longVar;
	}

	public void setLongVar(String longVar) {
		this.longVar = longVar;
	}

	public String getRequiredMinLengthLong() {
		return requiredMinLengthLong;
	}

	public void setRequiredMinLengthLong(String requiredMinLengthLong) {
		this.requiredMinLengthLong = requiredMinLengthLong;
	}

	/*
	 * public String getRequiredStringWithArgVal() { return
	 * requiredStringWithArgVal; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public void setRequiredStringWithArgVal(String requiredStringWithArgVal) {
	 * this.requiredStringWithArgVal = requiredStringWithArgVal; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public String[] getStringArray() { return stringArray; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public void setStringArray(String[] stringArray) { this.stringArray =
	 * stringArray; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public MultipartFile getFileSpring() { return fileSpring; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public void setFileSpring(MultipartFile fileSpring) { this.fileSpring =
	 * fileSpring; }
	 */

	@Override
	public void validate(Object target, Errors errors) {
		Tester tester = (Tester) target;
		// if (StringUtils.containsWhitespace(tester.getDesignation())) {
		// errors.rejectValue("designation","", "Designation is required");
		// errors.add("region", new LegacyActionMessage("input.error.region.required",
		// true));
		// }
	}

}