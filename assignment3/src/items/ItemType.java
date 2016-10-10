package items;

import java.lang.String;

/**
 * Stores information about a single item type
 */
public class ItemType {

    // The name of the item
    private String name;

    // The price of each item
    private float price;

    // The number of items currently in stock
    private int itemCount;
   
    public String getName() {
        return name;
    }

    public ItemType(String name, float price, int initialAmount) {
        this.price = price;
        this.itemCount = initialAmount;
        this.name = name;
    }

    public int getItemCount() {
        return itemCount;
    }
}
