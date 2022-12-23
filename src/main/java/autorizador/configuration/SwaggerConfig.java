package autorizador.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * <p>Classe responsável por implementar o framework Swagger, responsável por documentar os serviços
 * RESTFul dessa API.</p>
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String LICENSE_TEXT = "Licença";
    private static final String LICENSE_URI = "";
    private static final String NAME_API = "Mini Autorizador RESTful API";
    private static final String DESC_API = "RESTful API do Mini Autorizador";



    @SuppressWarnings("unused")
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(NAME_API)
                .description(DESC_API)
                .version(SWAGGER_API_VERSION)
                .license(LICENSE_TEXT)
                .licenseUrl(LICENSE_URI)
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "para operações get"),
                new AuthorizationScope("write", "para operações insert"),
                new AuthorizationScope("trust", "para operações seguras")};
        return scopes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(
                        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.regex("/*"))
                .build();
    }


}

