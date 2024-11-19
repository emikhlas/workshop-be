package ogya.workshop.performance_appraisal;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SecuritySchemes({
		@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"),
		@SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
})
@SpringBootApplication
public class PerformanceAppraisalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerformanceAppraisalApplication.class, args);
	}

}
