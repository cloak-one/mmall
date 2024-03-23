package org.example.mmall.config;


import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfig {

    private static final String TOKEN_HEADER = "Authorization";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(TOKEN_HEADER,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        // 这里配置 bearer 后，你的请求里会自动在 token 前加上 Bearer
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
//                /*.addParameters(TOKEN_HEADER,
//                                new Parameter()
//                                        .in("header")
//                                        .schema(new StringSchema())
//                                        .name(tokenHeader))*/
                )
                .info(new Info()
                        .title("测试 title")
                        .description("SpringBoot3 集成 Swagger3")
                        .version("v1"))
                .externalDocs(new ExternalDocumentation()
                        .description("项目API文档")
                        .url("https://springdoc.org/")
                )
                //在这里添加上Swagger要使用的安全策略
                // addList()中写上对应的key值
                .addSecurityItem(new SecurityRequirement().addList(TOKEN_HEADER));

    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("mall")
                .packagesToScan("org.example.mmall.controller")
                .build();
    }
}
