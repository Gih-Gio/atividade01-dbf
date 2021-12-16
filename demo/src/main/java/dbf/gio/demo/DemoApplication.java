package dbf.gio.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
public@SpringBootApplication class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		log.info("--->Minha Primeira Aplicação<------");
	}
	@Configuration
	@EnableSwagger2
	public class SwaggerConfig {
		@Bean
		public Docket docket() {
			return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(new ApiInfoBuilder()
							.title("DemoDs3 2021 API")
							.description("Uma API REST para o Trabalho PP ")
							.version("0.0.1-SNAPSHOT")
							.license("MIT")
							.licenseUrl("https://opensource.org/licenses/MIT")
							.build())
					.select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
					.build();
		}
	}

}



