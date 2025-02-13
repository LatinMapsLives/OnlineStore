package by.demidov_a_r.onlinestore.handler;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "by.demidov_a_r.onlinestore.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return "error/error500";
    }
}
