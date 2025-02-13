package by.demidov_a_r.onlinestore.handler;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Log4j2
@ControllerAdvice(basePackages = "by.demidov_a_r.onlinestore.controller")
public class ControllerExceptionHandler {


    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        log.error("Failed to return response", ex);
        return "error/error500";
    }
}
