package mk.finki.ukim.mk.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidBalloonException extends RuntimeException {
    public InvalidBalloonException(){
        super(String.format("Name or description has value null!!!"));
    }
}
