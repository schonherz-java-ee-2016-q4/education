package hu.schonhertz.training.print.printer;

import org.springframework.stereotype.Component;

/**
 * Created by ikant on 2016. 07. 23..
 */
@Component
public class ReversePrinter implements Printer {


    public void print(String input) {
        System.out.print(new StringBuilder(input).reverse().toString());
    }
}
