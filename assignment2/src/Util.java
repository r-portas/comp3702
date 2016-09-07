package util;

import java.lang.*;

/**
 * Contains utility functions
 * @author Roy Portas
 */
public class Util {
    public static double round(double number) {
        String s = String.format("%.2f", number);
        double res = Double.parseDouble(s);
        return res;
    }
}
