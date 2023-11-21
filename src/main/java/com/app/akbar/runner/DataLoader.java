package com.app.akbar.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.akbar.entity.CitizenPlan;
import com.app.akbar.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private CitizenPlanRepository repo;

	public void run(ApplicationArguments args) throws Exception {

		// CASH PLAN
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("STEVE");
		c1.setGender("MALE");
		c1.setPlanNames("CASH");
		c1.setPlanStatus("APPROVED");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmount(2000.00);

		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("Lilly");
		c2.setGender("FE-MALE");
		c2.setPlanNames("CASH");
		c2.setPlanStatus("DENIED");
		c1.setDenielReason("RENTAL INCOME");

		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("ROBERT");
		c3.setGender("MALE");
		c3.setPlanNames("CASH");
		c3.setPlanStatus("TERMINATED");
		c3.setPlanStartDate(LocalDate.now().minusMonths(2));
		c3.setPlanEndDate(LocalDate.now().plusMonths(4));
		c3.setTerminationDate(LocalDate.now());
		c3.setTerminationReason("GOVT JOB");

		// FOOD PLAN
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("NEBULA");
		c4.setGender("FE-MALE");
		c4.setPlanNames("FOOD");
		c4.setPlanStatus("APPROVED");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenefitAmount(4000.00);

		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizenName("LOKI");
		c5.setGender("MALE");
		c5.setPlanNames("FOOD");
		c5.setPlanStatus("DENIED");
		c5.setDenielReason("HOSTEL INCOME");

		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizenName("SCARLETT JHONSON");
		c6.setGender("FE-MALE");
		c6.setPlanNames("FOOD");
		c6.setPlanStatus("TERMINATED");
		c6.setPlanStartDate(LocalDate.now().minusMonths(2));
		c6.setPlanEndDate(LocalDate.now().plusMonths(4));
		c6.setTerminationDate(LocalDate.now());
		c6.setTerminationReason("PRIVATE JOB");

		// MEDICAL PLAN
		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizenName("THOR");
		c7.setGender("MALE");
		c7.setPlanNames("MEDICAL");
		c7.setPlanStatus("APPROVED");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmount(6000.00);

		CitizenPlan c8 = new CitizenPlan();
		c8.setCitizenName("TEYONAH pARRIS");
		c8.setGender("FE-MALE");
		c8.setPlanNames("MEDICAL");
		c8.setPlanStatus("DENIED");
		c8.setDenielReason("PROPERTY INCOME");

		CitizenPlan c9 = new CitizenPlan();
		c9.setCitizenName("HEMSWORTH");
		c9.setGender("MALE");
		c9.setPlanNames("MEDICAL");
		c9.setPlanStatus("TERMINATED");
		c9.setPlanStartDate(LocalDate.now().minusMonths(2));
		c9.setPlanEndDate(LocalDate.now().plusMonths(4));
		c9.setTerminationDate(LocalDate.now());
		c9.setTerminationReason("GOVT JOB");

		// EMPLOYMENT PLAN
		CitizenPlan c10 = new CitizenPlan();
		c10.setCitizenName("GEMMA CHAN");
		c10.setGender("FE-MALE");
		c10.setPlanNames("EMPLOYMENT");
		c10.setPlanStatus("APPROVED");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setBenefitAmount(8000.00);

		CitizenPlan c11 = new CitizenPlan();
		c11.setCitizenName("HOLLAND");
		c11.setGender("MALE");
		c11.setPlanNames("EMPLOYMENT");
		c11.setPlanStatus("DENIED");
		c11.setDenielReason("RENTAL INCOME");

		CitizenPlan c12 = new CitizenPlan();
		c12.setCitizenName("BRIE LARSON");
		c12.setGender("FE-MALE");
		c12.setPlanNames("EMPLOYMENT");
		c12.setPlanStatus("TERMINATED");
		c12.setPlanStartDate(LocalDate.now().minusMonths(3));
		c12.setPlanEndDate(LocalDate.now().plusMonths(3));
		c12.setTerminationDate(LocalDate.now());
		c12.setTerminationReason("PRIVATE JOB");
		
		List<CitizenPlan> list = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12);
		repo.saveAll(list);
	}
}
