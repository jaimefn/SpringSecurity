package br.com.nextmove.condonext.config.swagger;

import br.com.nextmove.condonext.domain.userlogin.UserLogin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
public class SwaggerConfigurations {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.nextmove.condonext"))
                .paths(PathSelectors.ant("/**"))
                .build()
               .directModelSubstitute(Pageable.class,SwaggerPageable.class)
                .ignoredParameterTypes(UserLogin.class)
                .globalOperationParameters(Arrays.asList(
                        new ParameterBuilder()
                                .name("Authorization")
                                .description("token jwt")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()));
    }
}
