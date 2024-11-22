package ogya.workshop.performance_appraisal.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
        registry.addRedirectViewController("/swagger-ui", "/swagger-ui.html");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("Workshop Performance Appraisal")
                        .description("Workshop Performance Appraisal")
                        .version("1.0.0")
                        .termsOfService("Terms of service")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

    @Bean
    GroupedOpenApi allApis() {
        return GroupedOpenApi.builder()
                .group("All")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    GroupedOpenApi allUserApis() {
        return GroupedOpenApi.builder()
                .group("User")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    GroupedOpenApi allRoleApis() {
        return GroupedOpenApi.builder()
                .group("Role")
                .pathsToMatch("/role/**")
                .build();
    }

    @Bean
    GroupedOpenApi allUserRoleApis() {
        return GroupedOpenApi.builder()
                .group("UserRole")
                .pathsToMatch("/user-role/**")
                .build();
    }

    @Bean
    GroupedOpenApi allEmpSuggestApis() {
        return GroupedOpenApi.builder()
                .group("EmpSuggest")
                .pathsToMatch("/emp-suggest/**")
                .build();
    }

    @Bean
    GroupedOpenApi allTechSkillApis() {
        return GroupedOpenApi.builder()
                .group("TechSkill")
                .pathsToMatch("/tech-skill/**")
                .build();
    }

    @Bean
    GroupedOpenApi allEmpTechSkillApis() {
        return GroupedOpenApi.builder()
                .group("EmpTechSkill")
                .pathsToMatch("/emp-tech-skill/**")
                .build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS on all endpoints
                .allowedOrigins("http://localhost:4200") // Allow this origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
