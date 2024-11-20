package ogya.workshop.performance_appraisal;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PerformanceAppraisalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerformanceAppraisalApplication.class, args);
	}

}
