package hu.schonhertz.training.print.validator;

import org.springframework.stereotype.Component;

/**
 * Created by ikant on 2016. 07. 23..
 */
@Component
public class NotEmptyValidator implements Validator {

    public void validate(String[] args) {
        if (args.length == 0 || args[0].length() == 0) {
            throw new IllegalArgumentException("Empty");
        }
    }
}
