package com.app.akbar.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.buf.Ascii;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.app.akbar.entity.CitizenPlan;
import com.app.akbar.repo.CitizenPlanRepository;
import com.app.akbar.request.SearchRequest;
import com.app.akbar.service.IReportService;
import com.app.akbar.util.EmailUtils;
import com.app.akbar.util.ExcelGeneratorUtil;
import com.app.akbar.util.PdfGeneratorUtil;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ReportServiceImpl implements IReportService {
	
	@Autowired
	private CitizenPlanRepository planRepo;
	
	@Autowired
	private ExcelGeneratorUtil excelGenerator;
	
	@Autowired
	private PdfGeneratorUtil pdfGenerator;
	
	@Autowired
	private EmailUtils emailSender;

	public List<String> getPlanNames() {
		return planRepo.getPlanNames();
	}

	public List<String> getPlanStatuses() {
		return planRepo.getPlanStatus();
	}

	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan plan = new CitizenPlan();
		
		if(null != request.getPlanNames() && "" != request.getPlanNames()) {
			plan.setPlanNames(request.getPlanNames());
		}
		
		if(null != request.getPlanStatus() && "" != request.getPlanStatus()) {
			plan.setPlanStatus(request.getPlanStatus());
		}
		
		if(null != request.getGender() && "" != request.getGender()) {
			plan.setGender(request.getGender());
		}
		
		if(null != request.getPlanStartDate() && !"".equals(request.getPlanStartDate())) {
			String date = request.getPlanStartDate(); 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(date, formatter);
			plan.setPlanStartDate(localDate);
		}
		
		if(null != request.getPlanEndDate() && !"".equals(request.getPlanEndDate())) {
			String date = request.getPlanEndDate(); 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(date, formatter);
			plan.setPlanEndDate(localDate);
		}
		
		List<CitizenPlan> plans = planRepo.findAll(Example.of(plan));
		return plans;
	}

	public boolean exportExcel(HttpServletResponse response) throws Exception {
		File file = new File("Plans.xls");
		List<CitizenPlan> plan = planRepo.findAll();
		
		excelGenerator.generate(response, plan, file);
		String subject = "Test Mail";
		String body = "<h1>This is Test Mail</h1>";
		String to = "akbarshaik911@gmail.com";
		
		emailSender.sendEmail(subject, body, to, file);
		file.delete();
		return true;
	}

	public boolean exportPdf(HttpServletResponse response) throws Exception {
		File file = new File("Plans.pdf");
		List<CitizenPlan> plan = planRepo.findAll();
		
		pdfGenerator.generate(response, plan, file);
		String subject = "Test Mail";
		String body = "<h1>This is Test Mail</h1>";
		String to = "akbarshaik911@gmail.com";
		
		emailSender.sendEmail(subject, body, to, file);
		file.delete();
		return true;
	}

}
