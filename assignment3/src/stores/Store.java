package stores;

import java.util.HashMap;
import java.util.Set;
import java.lang.String;
import items.*;

public class Store {
    // The number of types of items the shop can sell
    protected int maxItemTypes = 0;

    // Stores the count of item types in the store
    protected int itemTypeCount = 0;

    // Stores the count of items in the store
    protected int itemCount = 0;

    // The max number of items that can be stocked
    protected int maxItemsStocked = 0;
    
    // The max number of items that can be ordered per week
    protected int maxItemsOrdered = 0;

    // The max number of items that can be returned per week
    protected int maxItemsReturned = 0;

    protected float discountFactor = 0;
    protected float penaltyFee = 0;

    // Stores all the item types indexed by the item name
    protected HashMap<String, ItemType> itemTypes;

    public void addItemType(ItemType type) throws TooManyItemTypesException {

        itemTypes.put(type.getName(), type);
        itemTypeCount = itemTypes.size();
        
        updateItemCount();

        if (itemTypeCount > maxItemTypes) {
            throw new TooManyItemTypesException("Attempted to add item when itemTypeCount is " + itemTypeCount + ", max count is " + maxItemTypes);
        }

    }

    public String toString() {
        String output = "Store(ItemTypeCount: " + itemTypeCount + ", ItemCount: " + itemCount + ")\n";

        Set<String> itemNames = itemTypes.keySet();

        for (String itemName: itemNames) {
            output += itemName + ": " + itemTypes.get(itemName).getItemCount() + "\n";
        }

        return output;

    }

    protected void updateItemCount() {
        Set<String> itemNames = itemTypes.keySet();
        
        itemCount = 0;

        for (String itemName: itemNames) {
            itemCount += itemTypes.get(itemName).getItemCount();
        }
    }

    /**
     * Create an instance of a store
     */
    public Store(float discountFactor, float penaltyFeed) {
        itemTypes = new HashMap<String, ItemType>();
        itemTypeCount = 0;

        this.discountFactor = discountFactor;
        this.penaltyFee = penaltyFee;
    }
    
}
