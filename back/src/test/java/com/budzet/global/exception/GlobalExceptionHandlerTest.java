package com.budzet.global.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.budzet.controller.HelloController;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebMvcTest(controllers = {HelloController.class, GlobalExceptionHandlerTest.ExceptionTestController.class})
@Import(GlobalExceptionHandler.class)
@ContextConfiguration(classes = GlobalExceptionHandlerTest.TestApplication.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("정상 응답을 한다.")
    void successResponseUsesCommonFormat() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resultCode").value(201))
                .andExpect(jsonPath("$.message").value("데이터를 생성하였습니다."))
                .andExpect(jsonPath("$.data").value("Hello, Budzet!"));
    }

    @Test
    @DisplayName("BusinessException 발생 시, 기본 메시지와 함께 에러 응답을 한다.")
    void businessExceptionDefaultMessage() throws Exception {
        mockMvc.perform(get("/test/business-exception-default-message"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.resultCode").value(404))
                .andExpect(jsonPath("$.message").value("요청한 리소스를 찾을 수 없습니다."))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    @DisplayName("BusinessException 발생 시, 커스텀 메시지와 함께 에러 응답을 한다.")
    void businessExceptionCustomMessage() throws Exception {
        mockMvc.perform(get("/test/business-exception-custom-message"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.resultCode").value(404))
                .andExpect(jsonPath("$.message").value("테스트 리소스를 찾을 수 없습니다."))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    @DisplayName("@Valid 에러 발생 시, 구체적인 필드 에러를 포함한다.")
    void validationExceptionIncludesFieldErrorsInData() throws Exception {
        mockMvc.perform(post("/test/validation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resultCode").value(400))
                .andExpect(jsonPath("$.message").value("요청 형식이 올바르지 않습니다."))
                .andExpect(jsonPath("$.data[0].field").value("name"))
                .andExpect(jsonPath("$.data[0].message").value("이름은 필수입니다."));
    }

    // 테스트용 컨트롤러
    @RestController
    static class ExceptionTestController {

        // BusinessException 기본 메시지로 발생시키기
        @GetMapping("/test/business-exception-default-message")
        void throwBusinessException() {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }

        // BusinessException 커스텀 메시지로 발생시키기
        @GetMapping("/test/business-exception-custom-message")
        void throwBusinessExceptionWithCustomMessage() {
            throw new BusinessException(ErrorCode.NOT_FOUND, "테스트 리소스를 찾을 수 없습니다.");
        }

        // @Valid 에러 발생시키기
        @PostMapping("/test/validation")
        void validate(@Valid @RequestBody ValidationRequest request) {
        }

        record ValidationRequest(
                @NotBlank(message = "이름은 필수입니다.")
                String name
        ) {
        }
    }

    @SpringBootConfiguration
    @EnableAutoConfiguration
    @Import({
            HelloController.class,
            ExceptionTestController.class,
            GlobalExceptionHandler.class
    })
    static class TestApplication {
    }
}
