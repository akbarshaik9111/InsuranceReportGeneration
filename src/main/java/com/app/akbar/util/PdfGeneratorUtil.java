package com.app.akbar.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.app.akbar.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfGeneratorUtil {
	
	public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception {
		// Creating the Object of Document
				Document document = new Document(PageSize.A4);
				
				// Getting instance of PdfWriter
				PdfWriter.getInstance(document, response.getOutputStream());
				PdfWriter.getInstance(document, new FileOutputStream(file));
				
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
	}

}
