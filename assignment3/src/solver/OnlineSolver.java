package solver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Current items: " + currentStock.getTotalItems());
        currentStock.calculateProfit(probabilities);

        getPossibleSolutions(stockInventory);

        // combine orders and returns to get change for each item type
        List<Integer> order = new ArrayList<Integer>(itemOrders.size());
        for(int i = 0; i < itemOrders.size(); i++) {
            order.add(itemOrders.get(i) - itemReturns.get(i));
        }

        return order;
    }

    private List<Stock> getPossibleSolutions(List<Integer> stockInventory) {
        List<Stock> possibleSols = new ArrayList<Stock>();

        int currentTotal = currentStock.getTotalItems();
        System.out.println("Current Stock: " + currentStock.toString());

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

                                    // Its valid
                                    List<Integer> items = new ArrayList<Integer>();
                                    items.add(a);
                                    items.add(b);

                                    Stock s = currentStock.add(items);
                                    s.calculateProfit(probabilities);
                                    possibleSols.add(s);
                                    System.out.println(s.toString());

                                }
                            }
                        }
                    }
                }
                break;
        }


        // Calculate the possibilities if the store buys items
        return possibleSols;
    }

}
