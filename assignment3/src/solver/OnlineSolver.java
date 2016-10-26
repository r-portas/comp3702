package solver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import problem.Store;
import problem.Matrix;
import problem.ProblemSpec;
import problem.Stock;
import problem.ProbabilityGenerator;

public class OnlineSolver implements OrderingAgent {

    private ProblemSpec spec = new ProblemSpec();
    private Store store;
    private List<Matrix> probabilities;
    private ProbabilityGenerator probGenerator;

    private Stock currentStock;

    public OnlineSolver(ProblemSpec spec) throws IOException {
        probGenerator = new ProbabilityGenerator();
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

        List<Stock> possibilities = probGenerator.getPossibleSolutions(currentStock, store, probabilities);
        Stock best = possibilities.get(0);

        itemOrders = best.getItemOrders();
        System.out.println("Ordered: " + itemOrders);
        itemReturns = best.getItemReturns();

        // combine orders and returns to get change for each item type
        List<Integer> order = new ArrayList<Integer>(itemOrders.size());
        for(int i = 0; i < store.getMaxTypes(); i++) {
            order.add(itemOrders.get(i) - itemReturns.get(i));
        }

        return order;
    }

}
