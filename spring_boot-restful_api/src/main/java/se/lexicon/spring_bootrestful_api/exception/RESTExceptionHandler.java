package se.lexicon.spring_bootrestful_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalException(Exception ex) {
        System.out.println("Global Exception------------------------");
        System.out.println(ex.getMessage());
        ex.printStackTrace();
        System.out.println("-----------------------------------------");
        return ResponseEntity.status(500).body(new APIError(500, "INTERNAL_SERVER_ERROR"));
    }

}
