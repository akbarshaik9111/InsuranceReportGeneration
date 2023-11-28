package com.app.akbar.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {

	private String planNames;
	private String planStatus;
	private String gender;
	private String planStartDate;
	private String planEndDate;

}
