package solver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import problem.Store;
import problem.Matrix;
import problem.ProblemSpec;
import problem.Stock;

public class OnlineSolver implements OrderingAgent {

    private ProblemSpec spec = new ProblemSpec();
    private Store store;
    private List<Matrix> probabilities;

    private Stock currentStock;

    public OnlineSolver(ProblemSpec spec) throws IOException {
        this.spec = spec;
        store = spec.getStore();
        probabilities = spec.getProbabilities();
    }

    public void doOfflineComputation() {
        // This is online, so no offline computation
    }

    public List<Integer> generateStockOrder(List<Integer> stockInventory,
            int numWeeksLeft) {

        List<Integer> itemOrders = new ArrayList<Integer>();
        List<Integer> itemReturns = new ArrayList<Integer>();

        // Create a new stock item
        currentStock = new Stock(stockInventory, spec.getPrices());
        currentStock.calculateProfit(probabilities);

        List<Stock> possibilities = getPossibleSolutions(stockInventory);
        Stock best = possibilities.get(0);

        itemOrders = best.getItemOrders();
        itemReturns = best.getItemReturns();

        // combine orders and returns to get change for each item type
        List<Integer> order = new ArrayList<Integer>(itemOrders.size());
        for(int i = 0; i < itemOrders.size(); i++) {
            order.add(itemOrders.get(i) - itemReturns.get(i));
        }

        return order;
    }

    //TODO: Convert this to a tree implementation so it can predict the future
    private List<Stock> getPossibleSolutions(List<Integer> stockInventory) {
        List<Stock> possibleSols = new ArrayList<Stock>();

        int currentTotal = currentStock.getTotalItems();

        // Create Order instances
        switch (store.getMaxTypes()) {

            // Tiny and Small stores
            case 2:
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
                break;
        }

        // Sort the Stock to find the min
        Collections.sort(possibleSols);
        Collections.reverse(possibleSols);

        // Calculate the possibilities if the store buys items
        return possibleSols;
    }

}
