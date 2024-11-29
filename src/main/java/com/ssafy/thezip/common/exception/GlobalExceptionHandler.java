package com.ssafy.thezip.common.exception;

import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 필드 유효성 검사 실패 처리기 (이메일 등)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage())
//        );
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
//
//    // Enum 변환 오류 처리기
//    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
//    public ResponseEntity<Map<String, String>> handleEnumTypeMismatchException(Exception ex) {
//        Map<String, String> error = new HashMap<>();
//
//        Throwable cause = ex.getCause();
//        if (cause instanceof IllegalArgumentException) {
//            String message = cause.getMessage();
//            String enumClassName = message.substring(message.indexOf("'") + 1, message.lastIndexOf("'"));
//            try {
//                Class<?> enumClass = Class.forName(enumClassName);
//                if (enumClass.isEnum()) {
//                    String allowedValues = Stream.of(enumClass.getEnumConstants())
//                            .map(Object::toString)
//                            .collect(Collectors.joining(", "));
//                    error.put("message", "올바르지 않은 값입니다. 허용되는 값은 [" + allowedValues + "] 입니다.");
//                }
//            } catch (ClassNotFoundException e) {
//                error.put("message", "올바르지 않은 값입니다.");
//            }
//        } else {
//            error.put("message", "올바르지 않은 값입니다.");
//        }
//
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<?> handleException(Exception e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // 예외 로그 출력
        if (ex instanceof PersistenceException) {
            logger.error("MyBatis PersistenceException 발생: {}", ex.getMessage(), ex);
        } else if (ex instanceof DataAccessException) {
            logger.error("데이터 접근 오류 발생: {}", ex.getMessage(), ex);
        } else {
            logger.error("예외 발생: {}", ex.getMessage(), ex);
        }

        // 클라이언트에 오류 메시지 응답
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다. 관리자에게 문의하세요.");
    }
}