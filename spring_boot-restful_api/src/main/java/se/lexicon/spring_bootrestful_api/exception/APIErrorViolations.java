package se.lexicon.spring_bootrestful_api.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class APIErrorViolations extends APIError {

    private List<Violation> Violation;

    public APIErrorViolations(Integer statusCode, String statusText, String message, List<Violation> violation) {
        super(statusCode, statusText, message);
        Violation = violation;
    }
}
