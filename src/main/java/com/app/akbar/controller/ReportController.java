package com.app.akbar.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.akbar.entity.CitizenPlan;
import com.app.akbar.request.SearchRequest;
import com.app.akbar.service.IReportService;

@Controller
public class ReportController {
	
	@Autowired
	private IReportService service;
	
	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}
	
	@PostMapping("/search")
	public String getPlanDetails(SearchRequest search, Model model) {
		init(model);
		List<CitizenPlan> plan = service.search(search);
		model.addAttribute("search", search);
		model.addAttribute("plans", plan);
		return "index";
	}
	
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=plans.xls");
		service.exportExcel(response);
	}
	
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=plans.pdf");
		service.exportPdf(response);
	}

	private void init(Model model) {
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatuses());
	}

}
