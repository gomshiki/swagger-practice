package com.example.global;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "게시판 API 명세서",
                description = "게시판 API 명세서",
                version = "v1.0.0"
        )
)
@Configuration
public class SwaggerConfig {
    private static final String SECURITY_SCHEME_NAME = "Authorization";

    @Bean
    public GroupedOpenApi boardApi() {
        String[] paths = {"/api/v1/boards/**"};
        return GroupedOpenApi.builder()
                .group("보안 인증이 필요한 API")
                .pathsToMatch(paths)
                .pathsToExclude("/api/v1/users/**")
                .addOperationCustomizer((operation, handlerMethod) ->
                        operation.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME)))
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/api/v1/users/**"};
        return GroupedOpenApi.builder()
                .group("유저 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public OpenAPI boardApiWithSecurity() {
        // Security Scheme 정의
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(SECURITY_SCHEME_NAME);

        // OpenAPI 정의에 Security Scheme 추가
        return new OpenAPI().components(
                new Components().addSecuritySchemes(SECURITY_SCHEME_NAME, securityScheme));
    }
}
