package stores;

public class MediumStore extends Store {

    public MediumStore(float discountFactor, float penaltyFeed) {
        super(discountFactor, penaltyFeed);

        maxItemTypes = 3;
        maxItemsStocked = 8;
        maxItemsOrdered = 3;
        maxItemsReturned = 2;
    }
}
