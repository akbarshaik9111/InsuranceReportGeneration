package com.app.akbar.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CITIZEN_PLAN_INFO")
public class CitizenPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CITIZEN_ID")
	private Integer citizenId;
	
	@Column(name="CITIZEN_NAME")
	private String citizenName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="PLAN_NAMES")
	private String planNames;
	
	@Column(name="CITIZEN_STATUS")
	private String planStatus;
	
	@Column(name="PLAN_START_DATE")
	private LocalDate planStartDate;
	
	@Column(name="PLAN_END_DATE")
	private LocalDate planEndDate;
	
	@Column(name="BENEFIT_AMOUNT")
	private Double benefitAmount;
	
	@Column(name="DENIEL_REASON")
	private String denielReason;
	
	@Column(name="TERMINATION_DATE")
	private LocalDate terminationDate;
	
	@Column(name="TERMINATION_REASON")
	private String terminationReason;

}
