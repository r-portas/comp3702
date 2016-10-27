package solver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import problem.Store;
import problem.Stock;
import problem.ProbabilityGenerator;
import problem.Matrix;
import problem.ProblemSpec;
import problem.MonteCarlo;

public class OfflineSolver implements OrderingAgent {

    private ProblemSpec spec = new ProblemSpec();
    private Store store;
    private List<Matrix> probabilities;
    private MonteCarlo monteCarlo;
    private ProbabilityGenerator pg;

    public OfflineSolver(ProblemSpec spec) throws IOException {
        this.spec = spec;
        store = spec.getStore();
        probabilities = spec.getProbabilities();
        pg = new ProbabilityGenerator();
    }

    public void doOfflineComputation() {


    }

    public List<Integer> generateStockOrder(List<Integer> stockInventory,
            int numWeeksLeft) {

        List<Integer> itemOrders = new ArrayList<Integer>();
        List<Integer> itemReturns = new ArrayList<Integer>();

        Stock currentStock = new Stock(stockInventory, spec.getPrices());

        currentStock.calculateProfit(probabilities);

        // Do the monte carlo search tree
        monteCarlo = new MonteCarlo(
                currentStock,
                store,
                pg,
                probabilities,
                numWeeksLeft
            );

        Stock best;

        if (numWeeksLeft == 0) {
            // Run the basic method
            List<Stock> possibilities = pg.getPossibleSolutions(currentStock, store, probabilities);
            best = possibilities.get(0);

        } else {
            // Do a monte carlo search
            best = monteCarlo.run();
        }

        itemOrders = best.getItemOrders();
        itemReturns = best.getItemReturns();

        if (itemOrders.size() == 0) {
            
            List<Stock> possibilities = pg.getPossibleSolutions(currentStock, store, probabilities);
            best = possibilities.get(0);

            itemOrders = best.getItemOrders();
            itemReturns = best.getItemReturns();
        }


        // combine orders and returns to get change for each item type
        List<Integer> order = new ArrayList<Integer>(itemOrders.size());
        for(int i = 0; i < itemOrders.size(); i++) {
            order.add(itemOrders.get(i) - itemReturns.get(i));
        }

        System.out.println("Order size: " + order.size());

        return order;
    }

}
