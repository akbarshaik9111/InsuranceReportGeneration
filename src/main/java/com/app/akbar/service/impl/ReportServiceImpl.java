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
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("PLANS-DATA");
		Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("S.ID");
		headerRow.createCell(1).setCellValue("CITIZEN NAME");
		headerRow.createCell(2).setCellValue("GENDER");
		headerRow.createCell(3).setCellValue("PLAN NAME");
		headerRow.createCell(4).setCellValue("PLAN STATUS");
		headerRow.createCell(5).setCellValue("PLAN START DATE");
		headerRow.createCell(6).setCellValue("PLAN END DATE");
		headerRow.createCell(7).setCellValue("BENEFIT AMOUNT");
		
		List<CitizenPlan> records = planRepo.findAll();
		
		int dataRowIndex = 1;
		
		for(CitizenPlan plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanNames());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
			
			if(null != plan.getPlanStartDate()) {
				dataRow.createCell(5).setCellValue(plan.getPlanStartDate()+"");
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			
			if(null != plan.getPlanEndDate()) {
				dataRow.createCell(6).setCellValue(plan.getPlanEndDate());
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			
			if(null != plan.getBenefitAmount()) {
				dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
			} else {
				dataRow.createCell(7).setCellValue("N/A");
			}
			
			
			dataRowIndex++;
		}
		
		/*
		 * To share in project path
		 */
		/*FileOutputStream fos = new FileOutputStream(new File("plans.xml"));
		workbook.write(fos);
		workbook.close();*/
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		return true;
	}

	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		// Creating the Object of Document
		Document document = new Document(PageSize.A4);
		
		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());
		
		// Opening the created document to change it
		document.open();
		
		// Creating font
	    // Setting font style and size
	    Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTiltle.setSize(20);
		
		// Creating paragraph
		Paragraph para = new Paragraph("Citizen plans information", fontTiltle);
		
		// Aligning the paragraph in the document
		para.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(para);
		
		PdfPTable table = new PdfPTable(8);
		table.setSpacingBefore(5);
		table.addCell("ID");
		table.addCell("NAME");
		table.addCell("GENDER");
		table.addCell("PLAN NAME");
		table.addCell("PLAN STATUS");
		table.addCell("START DATE");
		table.addCell("END DATE");
		table.addCell("BENEFIT AMT");
		
		List<CitizenPlan> records = planRepo.findAll();
		for(CitizenPlan plan : records) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanNames());
			table.addCell(plan.getPlanStatus());
			
			if(null != plan.getPlanStartDate()) {
				table.addCell(plan.getPlanStartDate()+"");
			} else {
				table.addCell("N/A");
			}
			
			if(null != plan.getPlanEndDate()) {
				table.addCell(plan.getPlanEndDate()+"");
			} else {
				table.addCell("N/A");
			}
			
			if(null != plan.getBenefitAmount()) {
				table.addCell(String.valueOf(plan.getBenefitAmount()));
			} else {
				table.addCell("N/A");
			}
		}
		document.add(table);
		document.close();
		return true;
	}

}
