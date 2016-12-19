package hu.schonhertz.training.print.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by ikant on 2016. 07. 23..
 */
@Component
public class LengthValidator implements Validator {
    private final int minLength;

    public LengthValidator(@Value("${min.length}") int minLength) {
        this.minLength = minLength;
    }

    public void validate(String[] args) {
        if (args.length == 0 || args[0].length() < minLength) {
            throw new IllegalArgumentException("Too short");
        }
    }
}
