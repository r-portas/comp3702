package problem;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ProbabilityGenerator {
    /**
     * Generates all possible solutions for ordering
     *
     * @param currentStock      The current stock
     * @param store             The store
     */
    public List<Stock> getPossibleSolutions(Stock currentStock, Store store, List<Matrix> probabilities) {
        List<Stock> possibleSols = new ArrayList<Stock>();

        // Create Order instances
        switch (store.getMaxTypes()) {

            // Tiny and Small stores
            case 2:
                possibleSols = twoMaxTypes(currentStock, store, probabilities);
                break;

            // Medium stores
            case 3:
                possibleSols = threeMaxTypes(currentStock, store, probabilities);
                break;

        }

        // Sort the Stock to find the min
        Collections.sort(possibleSols);
        Collections.reverse(possibleSols);

        // Calculate the possibilities if the store buys items
        return possibleSols;
    }

    private List<Stock> twoMaxTypes(Stock currentStock, Store store, List<Matrix> probabilities) {
        List<Stock> possibleSols = new ArrayList<Stock>();
        int currentTotal = currentStock.getTotalItems();

        // Iterate through every possibility
        for (int a = -store.getMaxReturns(); a < store.getMaxPurchase(); a++) {
            for (int b = -store.getMaxReturns(); b < store.getMaxPurchase(); b++) {
                // Check if its valid
                if ((a + b) <= store.getMaxPurchase()) {
                    if (-(a + b) <= store.getMaxReturns()) {
                        int sum = a + b;
                        if ((sum + currentTotal) <= store.getCapacity()) {

                            int currentA = currentStock.getInventory().get(0);
                            int currentB = currentStock.getInventory().get(1);

                            // Check that the stock count is positive
                            if ((a + currentA) >= 0 && (b + currentB) >= 0) {

                                // Its valid
                                List<Integer> items = new ArrayList<Integer>();
                                items.add(a);
                                items.add(b);

                                Stock s = currentStock.add(items);
                                s.calculateProfit(probabilities);
                                possibleSols.add(s);
                            }

                        }
                    }
                }
            }
        }

        return possibleSols;
    }

    private List<Stock> threeMaxTypes(Stock currentStock, Store store, List<Matrix> probabilities) {
        List<Stock> possibleSols = new ArrayList<Stock>();
        int currentTotal = currentStock.getTotalItems();

        // Iterate through every possibility
        for (int a = -store.getMaxReturns(); a < store.getMaxPurchase(); a++) {
            for (int b = -store.getMaxReturns(); b < store.getMaxPurchase(); b++) {
                for (int c = -store.getMaxReturns(); c < store.getMaxPurchase(); c++) {
                    // Check if its valid
                    if ((a + b + c) <= store.getMaxPurchase()) {
                        if (-(a + b + c) <= store.getMaxReturns()) {
                            int sum = a + b + c;
                            if ((sum + currentTotal) <= store.getCapacity()) {

                                int currentA = currentStock.getInventory().get(0);
                                int currentB = currentStock.getInventory().get(1);
                                int currentC = currentStock.getInventory().get(2);

                                // Check that the stock count is positive
                                if ((a + currentA) >= 0 && (b + currentB) >= 0 && (c + currentC) >= 0) {

                                    // Its valid
                                    List<Integer> items = new ArrayList<Integer>();
                                    items.add(a);
                                    items.add(b);
                                    items.add(c);

                                    Stock s = currentStock.add(items);
                                    s.calculateProfit(probabilities);
                                    possibleSols.add(s);
                                }

                            }
                        }
                    }
                }
            }
        }

        return possibleSols;
    }

    private List<Stock> fiveMaxTypes(Stock currentStock, Store store, List<Matrix> probabilities) {
        List<Stock> possibleSols = new ArrayList<Stock>();
        int currentTotal = currentStock.getTotalItems();

        // Iterate through every possibility
        for (int a = -store.getMaxReturns(); a < store.getMaxPurchase(); a++) {
            for (int b = -store.getMaxReturns(); b < store.getMaxPurchase(); b++) {
                for (int c = -store.getMaxReturns(); c < store.getMaxPurchase(); c++) {
                    for (int d = -store.getMaxReturns(); d < store.getMaxPurchase(); d++) {
                        for (int e = -store.getMaxReturns(); e < store.getMaxPurchase(); e++) {
                            // Check if its valid
                            if ((a + b + c + d + e) <= store.getMaxPurchase()) {
                                if (-(a + b + c + d + e) <= store.getMaxReturns()) {
                                    int sum = a + b + c + d + e;
                                    if ((sum + currentTotal) <= store.getCapacity()) {

                                        int currentA = currentStock.getInventory().get(0);
                                        int currentB = currentStock.getInventory().get(1);
                                        int currentC = currentStock.getInventory().get(2);
                                        int currentD = currentStock.getInventory().get(3);
                                        int currentE = currentStock.getInventory().get(4);

                                        // Check that the stock count is positive
                                        if ((a + currentA) >= 0 && (b + currentB) >= 0 && 
                                                (c + currentC) >= 0 && (d + currentD) >= 0 && (e + currentE) >= 0) {

                                            // Its valid
                                            List<Integer> items = new ArrayList<Integer>();
                                            items.add(a);
                                            items.add(b);
                                            items.add(c);
                                            items.add(d);
                                            items.add(e);

                                            Stock s = currentStock.add(items);
                                            s.calculateProfit(probabilities);
                                            possibleSols.add(s);
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return possibleSols;
    }
}
