package stores;

public class TinyStore extends Store {

    public TinyStore(float discountFactor, float penaltyFeed) {
        super(discountFactor, penaltyFeed);

        maxItemTypes = 2;
        maxItemsStocked = 3;
        maxItemsOrdered = 2;
        maxItemsReturned = 1;
    }
}
