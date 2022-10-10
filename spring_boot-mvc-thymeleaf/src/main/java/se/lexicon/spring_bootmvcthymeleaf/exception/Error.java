package se.lexicon.spring_bootmvcthymeleaf.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Error {
    private String code;
    private String message;
    private LocalDateTime timestamp;

    public Error() {
        this.timestamp = LocalDateTime.now();
    }

    public Error(String code, String message) {
        this();
        this.code = code;
        this.message = message;
    }
}
