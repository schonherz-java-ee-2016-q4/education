package hu.schonhertz.training.print.validator;

import org.springframework.stereotype.Component;

/**
 * Created by ikant on 2016. 07. 23..
 */
@Component
public class ContentValidator implements Validator {
    private final String requiredChars = "aáeéiíoóöőuúüű";

    public void validate(String[] args) {
        if (!args[0].matches(".*[" + requiredChars + "].*")) {
            throw new IllegalArgumentException("Not containing any of: " + requiredChars);
        }
    }
}
