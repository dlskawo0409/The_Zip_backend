package com.ssafy.thezip.common.configuration;




import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class SwaggerConfig {
//
//    private final Logger log = LoggerFactory.getLogger(getClass());

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("AccessAuth", new SecurityScheme()
//                        .name("access")
//                        .type(SecurityScheme.Type.APIKEY)
//                        .in(SecurityScheme.In.HEADER)
//                        .bearerFormat("JWT")))
//                .addSecurityItem(new SecurityRequirement().addList("AccessAuth"))
//                .info(new Info().title("API Documentation").version("1.0"));
//    }
@Bean
public OpenAPI customOpenAPI() {
    Schema<?> loginRequestSchema = new Schema<>()
            .type("object")
            .addProperty("username", new Schema<>().type("string").description("사용자 이메일").example("dlskawo04091@naver.com"))
            .addProperty("password", new Schema<>().type("string").description("비밀번호").example("dlskawo49!"));

    OpenAPI openAPI = new OpenAPI().components(new Components().addSecuritySchemes("AccessAuth", new SecurityScheme()
                    .name("access")
                    .type(SecurityScheme.Type.APIKEY)
                    .in(SecurityScheme.In.HEADER)
                    .bearerFormat("JWT")))
            .addSecurityItem(new SecurityRequirement().addList("AccessAuth"))
            .info(new Info().title("API Documentation").version("1.0"));

    // /login 경로 추가
    Paths paths = new Paths();
    paths.addPathItem("/login", new PathItem().post(
            new Operation()
                    .summary("로그인 API")
                    .description("Spring Security 기본 로그인 경로")
                    .requestBody(new RequestBody()
                            .description("로그인 요청 데이터")
                            .content(new Content().addMediaType("application/json", new MediaType().schema(loginRequestSchema))))
                    .responses(new ApiResponses()
                            .addApiResponse("200", new ApiResponse().description("로그인 성공"))
                            .addApiResponse("401", new ApiResponse().description("인증 실패"))
                    )
    ));
    openAPI.paths(paths);

    return openAPI;
}
    @Bean
    public GroupedOpenApi memberApi() {
        return GroupedOpenApi.builder()
                .group("Member") // Swagger UI 그룹 이름
                .pathsToMatch("/members/**","/login") // /member 경로와 관련된 API만 포함
                .packagesToScan("com.ssafy.thezip.member") // 특정 패키지 스캔
                .build();
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("All")
                .pathsToMatch("/**","/login")
                .packagesToScan("com.ssafy.thezip")
                .build();
    }

    @Bean
    public GroupedOpenApi interestAreaApi() {
        return GroupedOpenApi.builder()
                .group("InterestArea")
                .pathsToMatch("/interest-area/**","/login")
                .packagesToScan("com.ssafy.thezip.interest_area")
                .build();
    }

    @Bean
    public GroupedOpenApi boardApi() {
        return GroupedOpenApi.builder()
                .group("Board")
                .pathsToMatch("/boards/**","/login")
                .packagesToScan("com.ssafy.thezip.board", "com.ssafy.thezip.board_comment")
                .build();
    }

    @Bean
    public GroupedOpenApi charterApi() {
        return GroupedOpenApi.builder()
                .group("Charter")
                .pathsToMatch("/charters/**","/interest-charter/**", "/dongcodes/**","/interest-area/**", "/login")
                .packagesToScan("com.ssafy.thezip.charter", "com.ssafy.thezip.interest_charter","com.ssafy.thezip.dongcode","com.ssafy.thezip.interest_area")
                .build();
    }

    @Bean
    public GroupedOpenApi dongCodeApi() {
        return GroupedOpenApi.builder()
                .group("DongCode")
                .pathsToMatch("/dongcodes/**")
                .packagesToScan("com.ssafy.thezip.dongcode")
                .build();
    }

    @Bean
    public GroupedOpenApi dormitoryApi() {
        return GroupedOpenApi.builder()
                .group("Dormitory")
                .pathsToMatch("/dormitories/**")
                .packagesToScan("com.ssafy.thezip.dormitory")
                .build();
    }

    @Bean
    public GroupedOpenApi houseInfoApi() {
        return GroupedOpenApi.builder()
                .group("HouseInfo")
                .pathsToMatch("/houseinfos/**", "/interest-house/**","/login")
                .packagesToScan("com.ssafy.thezip.houseinfo", "com.ssafy.thezip.interest_house")
                .build();
    }


    private Info apiInfo() {
        return new Info()
                .title("Spring Boot REST API Specifications")
                .description("Specification")
                .version("1.0.0");
    }
}