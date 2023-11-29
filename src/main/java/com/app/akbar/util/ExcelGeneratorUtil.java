package com.app.akbar.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.app.akbar.entity.CitizenPlan;

@Component
public class ExcelGeneratorUtil {
		
	public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception {
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
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
	}

}
