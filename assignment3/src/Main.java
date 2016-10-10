/**
 * COMP3702 - Assignment 3
 * @author Roy Portas
 */

import stores.*;
import items.*;

public class Main {

    private static void test() {
        float discountFactor = 0.95f;
        float penaltyFee = 100f; 
        TinyStore tiny = new TinyStore(discountFactor, penaltyFee); 

        try {
            ItemType item1 = new ItemType("1", 50f, 2);
            tiny.addItemType(item1);

            ItemType item2 = new ItemType("2", 70f, 1);
            tiny.addItemType(item2);

        } catch (TooManyItemTypesException e) {
            System.out.println(e);
        }

        System.out.println(tiny.toString());
    }

    public static void main(String[] args) {
        /*
        if (args.length == 2) {
            // Start the program
        } else {
            System.out.println("Usage: ./a3-comp3702-4356084.jar input-file output-file");
        }
        */
        test();
    }
}
