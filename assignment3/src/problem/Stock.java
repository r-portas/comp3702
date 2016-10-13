package problem;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the stock of a store
 */
public class Stock {

    /** The inventory for the order */
    private List<Integer> inventory;

    /** The cost of completing an order */
    private double cost;

    /** Stores a list of prices */
    private List<Double> prices;

    public Stock(List<Integer> current, List<Double> prices) {
        this.inventory = new ArrayList<Integer>(current);
        this.prices = prices;
        this.cost = 0;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Integer> getInventory() {
        return inventory;
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

        for (int i = 0; i < newInv.size(); i++) {
            int newItems = newInv.get(i);
            double price = prices.get(i);

            int updated = newItems + items.get(i);
            if (items.get(i) < 0) {
                // Refund cost
                newCost += -1 * items.get(i) * price * 0.5;
            }

            newInv.set(i, updated);
        }

        Stock newStock = new Stock(newInv, prices);
        newStock.setCost(newCost);

        return newStock;
    }

    public String toString() {
        String out = "Order(";

        out += inventory.get(0);

        for (int i = 1; i < inventory.size(); i++) {
            out += ", ";
            out += inventory.get(i);
        }

        out += ") Cost: ";
        out += cost;

        return out;
    }
}
