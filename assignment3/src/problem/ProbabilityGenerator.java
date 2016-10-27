package problem;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ProbabilityGenerator {

    /** Enables debugging **/
    private boolean DEBUGGING = false;

    private void log(String s) {
        if (DEBUGGING) {
            System.out.println("[ProbGen]: " + s);
        }
    }

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

                // Large stores
            case 5:
                possibleSols = fiveMaxTypes(currentStock, store, probabilities);
                break;

                // Mega stores
            case 7:
                possibleSols = sevenMaxTypes(currentStock, store, probabilities);

        }

        // Sort the Stock to find the min
        Collections.sort(possibleSols);
        Collections.reverse(possibleSols);

        log("Best " + possibleSols.get(0));

        // Calculate the possibilities if the store buys items
        return possibleSols;
    }

    /**
     * Returns a list containing only the positive elements of the original list
     */
    private List<Integer> getPositiveNumbers(List<Integer> numbers) {
        List<Integer> positives = new ArrayList<Integer>();

        for (int num : numbers) {
            if (num >= 0) {
                positives.add(num);
            }
        }

        return positives;
    }

    /**
     * Returns a list containing only the negative elements of the original list
     */
    private List<Integer> getNegativeNumbers(List<Integer> numbers) {
        List<Integer> negatives = new ArrayList<Integer>();

        for (int num : numbers) {
            if (num < 0) {
                negatives.add(num);
            }
        }

        return negatives;
    }

    /**
     * Returns the sum of a list
     */
    private int getSum(List<Integer> numbers) {
        int sum = 0;

        for (int num : numbers) {
            sum += num;
        }

        return sum;
    }

    /**
     * Checks if a configuration is valid
     * @param orders A list containing the order for the new stock
     * @param currentInventory The current inventory of the store
     * @param maxStock The max stock of the store
     */
    private boolean checkValid(List<Integer> orders, List<Integer> currentInventory, int maxStock) {
        int sum = 0;

        for (int i = 0; i < orders.size(); i++) {
            int items = orders.get(i) + currentInventory.get(i);
            sum += items;

            // If the items are less than 0, then its not valid
            if (items < 0) {
                return false;
            }
        }

        if (sum > maxStock) {
            return false;
        }

        return true;
    }

    private List<Stock> twoMaxTypes(Stock currentStock, Store store, List<Matrix> probabilities) {
        List<Stock> possibleSols = new ArrayList<Stock>();
        int currentTotal = currentStock.getTotalItems();

        // Iterate through every possibility
        for (int a = -store.getMaxReturns(); a < store.getMaxPurchase(); a++) {
            for (int b = -store.getMaxReturns(); b < store.getMaxPurchase(); b++) {
                // Check if its valid

                List<Integer> numbers = new ArrayList<Integer>();
                numbers.add(a);
                numbers.add(b);

                List<Integer> positives = getPositiveNumbers(numbers);
                List<Integer> negatives = getNegativeNumbers(numbers);

                int sumPositives = getSum(positives);
                int sumNegatives = getSum(negatives);

                if (sumPositives <= store.getMaxPurchase()) {
                    if (sumNegatives * -1 <= store.getMaxReturns()) {
                        int sum = getSum(numbers);
                        if ((sum + currentTotal) <= store.getCapacity()) {

                            // Check that the stock count is positive
                            if (checkValid(numbers, currentStock.getInventory(), store.getCapacity())) {
                                //if ((a + currentA) >= 0 && (b + currentB) >= 0) {

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
                        List<Integer> numbers = new ArrayList<Integer>();
                        numbers.add(a);
                        numbers.add(b);
                        numbers.add(c);

                        List<Integer> positives = getPositiveNumbers(numbers);
                        List<Integer> negatives = getNegativeNumbers(numbers);

                        int sumPositives = getSum(positives);
                        int sumNegatives = getSum(negatives);

                        // Check if its valid
                        if (sumPositives <= store.getMaxPurchase()) {
                            if (-sumNegatives <= store.getMaxReturns()) {
                                int sum = getSum(numbers);
                                if ((sum + currentTotal) <= store.getCapacity()) {

                                    // Check that the stock count is greater than 0
                                    if (checkValid(numbers, currentStock.getInventory(), store.getCapacity())) {

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
                                List<Integer> numbers = new ArrayList<Integer>();
                                numbers.add(a);
                                numbers.add(b);
                                numbers.add(c);
                                numbers.add(d);
                                numbers.add(e);

                                List<Integer> positives = getPositiveNumbers(numbers);
                                List<Integer> negatives = getNegativeNumbers(numbers);

                                int sumPositives = getSum(positives);
                                int sumNegatives = getSum(negatives);

                                // Check if its valid
                                if (sumPositives <= store.getMaxPurchase()) {
                                    if (-sumNegatives <= store.getMaxReturns()) {
                                        int sum = getSum(numbers);
                                        if ((sum + currentTotal) <= store.getCapacity()) {

                                            // Check that the stock count is greater than 0
                                            if (checkValid(numbers, currentStock.getInventory(), store.getCapacity())) {

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

    private List<Stock> sevenMaxTypes(Stock currentStock, Store store, List<Matrix> probabilities) {
        List<Stock> possibleSols = new ArrayList<Stock>();
        int currentTotal = currentStock.getTotalItems();

        // Iterate through every possibility
        for (int a = -store.getMaxReturns(); a < store.getMaxPurchase(); a++) {
            for (int b = -store.getMaxReturns(); b < store.getMaxPurchase(); b++) {
                for (int c = -store.getMaxReturns(); c < store.getMaxPurchase(); c++) {
                    for (int d = -store.getMaxReturns(); d < store.getMaxPurchase(); d++) {
                        for (int e = -store.getMaxReturns(); e < store.getMaxPurchase(); e++) {
                            for (int f = -store.getMaxReturns(); f < store.getMaxPurchase(); f++) {
                                for (int g = -store.getMaxReturns(); g < store.getMaxPurchase(); g++) {
                                    List<Integer> numbers = new ArrayList<Integer>();
                                    numbers.add(a);
                                    numbers.add(b);
                                    numbers.add(c);
                                    numbers.add(d);
                                    numbers.add(e);
                                    numbers.add(f);
                                    numbers.add(g);

                                    List<Integer> positives = getPositiveNumbers(numbers);
                                    List<Integer> negatives = getNegativeNumbers(numbers);

                                    int sumPositives = getSum(positives);
                                    int sumNegatives = getSum(negatives);

                                    // Check if its valid
                                    if (sumPositives <= store.getMaxPurchase()) {
                                        if (-sumNegatives <= store.getMaxReturns()) {
                                            int sum = getSum(numbers);
                                            if ((sum + currentTotal) <= store.getCapacity()) {

                                                // Check that the stock count is greater than 0
                                                if (checkValid(numbers, currentStock.getInventory(), store.getCapacity())) {

                                                    // Its valid
                                                    List<Integer> items = new ArrayList<Integer>();
                                                    items.add(a);
                                                    items.add(b);
                                                    items.add(c);
                                                    items.add(d);
                                                    items.add(e);
                                                    items.add(f);
                                                    items.add(g);

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
            }
        }

        return possibleSols;
    }
}
