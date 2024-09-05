package com.cbcc.bizboot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Bizboot API 文档", version = "1.0.0")
)
public class OpenApiConfig {

    static {
        SpringDocUtils.getConfig()
                .replaceWithSchema(LocalDate.class, new StringSchema()
                        .example(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .replaceWithSchema(LocalDateTime.class, new StringSchema()
                        .example(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
    }

    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("系统管理")
                .pathsToMatch("/api/**")
                .build();
    }
}
