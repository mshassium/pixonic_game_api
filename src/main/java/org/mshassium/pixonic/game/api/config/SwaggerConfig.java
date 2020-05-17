package org.mshassium.pixonic.game.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("org.mshassium.pixonic.game.api.controller.game"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo());

    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("Pixonic Game Back End REST API")
                .description("This API allows to perform all operations related to Pixonic Game Back End")
                .version("1.0")
                .build();
    }
}
