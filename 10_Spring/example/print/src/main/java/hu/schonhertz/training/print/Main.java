package hu.schonhertz.training.print;

import hu.schonhertz.training.print.printer.Printer;
import hu.schonhertz.training.print.printer.ReversePrinter;
import hu.schonhertz.training.print.validator.ContentValidator;
import hu.schonhertz.training.print.validator.LengthValidator;
import hu.schonhertz.training.print.validator.NotEmptyValidator;
import hu.schonhertz.training.print.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by ikant on 2016. 07. 23..
 */
@Component
public class Main {
    private static final String CONFIG_LOCATION = "printer.xml";

    private Printer printer;

    /**
     * Field injection when used with annotation config.
     */
    @Autowired
    @Qualifier("contentValidator")
    private Validator contentValidator;
    @Autowired
    @Qualifier("notEmptyValidator")
    private Validator notEmptyValidator;
    private Validator lengthValidator;

    /**
     * Constructor injection with annotation config.
     *
     * @param printer printer instance from the container
     */
    public Main(@Autowired Printer printer) {
        this.printer = printer;
    }

    /**
     * Entry point.
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        Main main = applicationContext.getBean(Main.class);
        main.process(args);
    }

    private void process(String[] args) {
        notEmptyValidator.validate(args);
        lengthValidator.validate(args);
        contentValidator.validate(args);
        printer.print(args[0]);
    }

    @Autowired
    @Qualifier("lengthValidator")
    public void setLengthValidator(Validator lengthValidator) {
        this.lengthValidator = lengthValidator;
    }

    public void setContentValidator(Validator contentValidator) {
        this.contentValidator = contentValidator;
    }

    public void setNotEmptyValidator(Validator notEmptyValidator) {
        this.notEmptyValidator = notEmptyValidator;
    }
}
