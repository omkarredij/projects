package model;

public class Level {
    public int getBidQuantity() {
        return bidQuantity;
    }
    public double getBidPrice() {
        return bidPrice;
    }
    public int getAskQuantity() {
        return askQuantity;
    }
    public double getAskPrice() {
        return askPrice;
    }

    int bidQuantity;
    double bidPrice;
    int askQuantity;
    double askPrice;

    public Level(double _askPrice, int _askQuantity, double _bidPrice, int _bidQuantity) {
        bidQuantity = _bidQuantity;
        bidPrice = _bidPrice;
        askQuantity = _askQuantity;
        askPrice = _askPrice;
    }

    @Override
    public String toString(){
        return "Ask Price: " + askPrice + " Ask Quantity: " + askQuantity +
        " | " + "Bid Price: " + bidPrice + " Bid Quantity: " + bidQuantity;
    }

    @Override
    public boolean equals(Object obj) {
        Level level = (Level)obj;
        return this.askPrice == level.askPrice && this.askQuantity== level.askQuantity
                && this.bidPrice == level.bidPrice && this.bidQuantity== level.bidQuantity;
    }
}
