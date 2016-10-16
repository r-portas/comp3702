package problem;

import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

/**
 * Represents the stock of a store
 */
public class Stock implements Comparable<Stock>{

    /** The inventory for the order */
    private List<Integer> inventory;

    /** The cost of completing an order */
    private double cost;

    /** The total profit from an order */
    private double profit;

    /** Stores a list of prices */
    private List<Double> prices;

    /** The items to order */
    private List<Integer> itemOrders;
    /** The items to return */
    private List<Integer> itemReturns;

    /** Enable verbose logging */
    private boolean verbose = false;

    public Stock(List<Integer> current, List<Double> prices) {
        this.inventory = new ArrayList<Integer>(current);
        this.prices = prices;
        this.cost = 0;
        
        itemOrders = new ArrayList<Integer>();
        itemReturns = new ArrayList<Integer>();
    }

    public List<Integer> getItemOrders() {
        return itemOrders;
    }

    public List<Integer> getItemReturns() {
        return itemReturns;
    }

    public void setItemOrders(List<Integer> orders) {
        this.itemOrders = orders;
    }

    public void setItemReturns(List<Integer> returns) {
        this.itemReturns = returns;
    }

    public double getCost() {
        return cost;
    }

    public double getProfit() {
        return profit;
    }

    public List<Integer> getInventory() {
        return inventory;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int compareTo(Stock other) {
        Double profitObj = new Double(profit);
        return profitObj.compareTo(other.getProfit());
    }

    public void calculateProfit(List<Matrix> probMatrixes) {

        double potentialProfit = 0;
        for (int i = 0; i < inventory.size(); i++) {
            Matrix p = probMatrixes.get(i);
            int items = inventory.get(i);
            double itemPrice = prices.get(i);

            // Get the row associated with the number of items
            List<Double> pRow = new ArrayList<Double>();

            if (items >= 0) {
                pRow = p.getRow(items);
            } else {
                System.out.println(inventory);
                System.out.println("ERROR: item count is negative (" + items + ") [Stock.java:calculateProfit]");
            }
            
            if (verbose) {
                System.out.println("Calculating probability for item " + i);
                System.out.println("Items: " + items + ", Item Price: " + itemPrice);
                System.out.println(pRow);
            }

            for (int itemsBought = 0; itemsBought < pRow.size(); itemsBought++) {
                double probability = pRow.get(itemsBought);


                if (itemsBought <= items) {
                    // Its a profit
                    potentialProfit += itemsBought * itemPrice * probability * 0.75;

                    if (verbose) {
                        System.out.println("(ItemsBought: " + itemsBought + ", Profit: " + (itemsBought * itemPrice * probability * 0.75) + ")");
                    }
                } else {
                    // Its a loss 
                    potentialProfit -= itemsBought * itemPrice * probability * 0.25;

                    if (verbose) {
                        System.out.println("(ItemsBought: " + itemsBought + ", Profit: " + (-itemsBought * itemPrice * probability * 0.25) + ")");
                    }
                }
            }
        }


        // Calculate the profit
        profit = potentialProfit - cost;
        if (verbose) {
            System.out.println("Profit: " + profit);
        }
    }

    /**
     * Gets the total items in the order
     */
    public int getTotalItems() {
        int total = 0;

        for (Integer i : inventory) {
            total += i;   
        }

        return total;
    }

    /**
     * Adds items to the order, returning a new instance of the order
     * An order could be like (1, 0) to add a single instance of item 1
     */
    public Stock add(List<Integer> items) {
        double newCost = 0;
        List<Integer> newInv = new ArrayList<Integer>(inventory);
        List<Integer> newItemOrders = new ArrayList<Integer>();
        List<Integer> newItemReturns = new ArrayList<Integer>();

        for (int i = 0; i < newInv.size(); i++) {
            int newItems = newInv.get(i);
            double price = prices.get(i);

            int updated = newItems + items.get(i);
            if (items.get(i) < 0) {
                // Its a refund
                newItemReturns.add(items.get(i));
                newItemOrders.add(0);

                newCost += -1 * items.get(i) * price * 0.5;
            } else {
                // Its a order
                newItemReturns.add(0);
                newItemOrders.add(items.get(i));
            }

            newInv.set(i, updated);
        }

        Stock newStock = new Stock(newInv, prices);
        newStock.setCost(newCost);
        newStock.setItemOrders(newItemOrders);
        newStock.setItemReturns(newItemReturns);

        return newStock;
    }

    public String toString() {
        String out = "Stock(inv = {";

        out += inventory.get(0);

        for (int i = 1; i < inventory.size(); i++) {
            out += ", ";
            out += inventory.get(i);
        }

        out += "}, profit = ";
        out += profit;
        out += ")";

        return out;
    }
}
