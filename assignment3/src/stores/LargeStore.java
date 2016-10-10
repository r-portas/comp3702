package stores;

public class LargeStore extends Store {

    public LargeStore(float discountFactor, float penaltyFeed) {
        super(discountFactor, penaltyFeed);

        maxItemTypes = 5;
        maxItemsStocked = 10;
        maxItemsOrdered = 4;
        maxItemsReturned = 2;
    }
}
