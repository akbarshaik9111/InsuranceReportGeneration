package com.app.akbar.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.app.akbar.entity.CitizenPlan;
import com.app.akbar.request.SearchRequest;

public interface IReportService {

	public List<String> getPlanNames();

	public List<String> getPlanStatuses();

	public List<CitizenPlan> search(SearchRequest request);

	public boolean exportExcel(HttpServletResponse response)throws Exception;

	public boolean exportPdf(HttpServletResponse response)throws Exception;
}
