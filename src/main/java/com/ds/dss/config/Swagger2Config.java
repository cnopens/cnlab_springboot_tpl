package com.ds.dss.config;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spi.service.contexts.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config
{
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.ds.iaas.rsmgr.controller")).paths(PathSelectors.any()).build().securitySchemes((List)this.securitySchemes()).securityContexts((List)this.securityContexts());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("SwaggerUI\u6f14\u793a").description("iaas-rsmgr").contact("cnopens").version("1.0").build();
    }
    
    private List<ApiKey> securitySchemes() {
        final List<ApiKey> result = new ArrayList<ApiKey>();
        final ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);
        return result;
    }
    
    private List<SecurityContext> securityContexts() {
        final List<SecurityContext> result = new ArrayList<SecurityContext>();
        result.add(this.getContextByPath("/user/.*"));
        return result;
    }
    
    private SecurityContext getContextByPath(final String pathRegex) {
        return SecurityContext.builder().securityReferences((List)this.defaultAuth()).forPaths(PathSelectors.regex(pathRegex)).build();
    }
    
    private List<SecurityReference> defaultAuth() {
        final List<SecurityReference> result = new ArrayList<SecurityReference>();
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = { authorizationScope };
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }
}
