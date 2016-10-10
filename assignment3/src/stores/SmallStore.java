package stores;

public class SmallStore extends Store {

    public SmallStore(float discountFactor, float penaltyFeed) {
        super(discountFactor, penaltyFeed);

        maxItemTypes = 2;
        maxItemsStocked = 8;
        maxItemsOrdered = 3;
        maxItemsReturned = 2;
    }
}
