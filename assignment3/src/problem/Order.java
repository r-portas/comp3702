package problem;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a single order
 */
public class Order {

    /** The inventory for the order */
    private List<Integer> inventory;

    /** The starting inventory */
    private List<Integer> startingInventory;

    /** The cost of completing an order */
    private double cost;

    public Order(List<Integer> current) {
        this.inventory = current;
        this.startingInventory = current;
        this.cost = 0;
    }

    public double getCost() {
        return cost;
    }

    /**
     * Adds items to the order
     * An order could be like (1, 0) to add a single instance of item 1
     */
    public void add(List<Integer> items, List<Double> prices) {
        for (int i = 0; i < inventory.size(); i++) {
            int newItems = inventory.get(i);
            int price = prices.get(i);

            int updated = newItems + items.get(i);

            if (newItems < 0) {
                cost += -1 * newItems * price * 0.5;
            }

            inventory.set(i, updated);
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
}
