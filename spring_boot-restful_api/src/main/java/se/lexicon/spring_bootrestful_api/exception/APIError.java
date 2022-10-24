package se.lexicon.spring_bootrestful_api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class APIError {

    private Integer statusCode; //404
    private String statusText; //NOT_FOUND
    private String message; //Requested page does not exist.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp; //1960-01-01 12:00:00

    public APIError() {
        this.timestamp = LocalDateTime.now();
    }

    public APIError(Integer statusCode, String statusText) {
        this();
        this.statusCode = statusCode;
        this.statusText = statusText;
    }
}
