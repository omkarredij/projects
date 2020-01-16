package model;

public class TopOfTheBook {
    String symbol;
    int bidQuantity;
    double bidPrice;
    int askQuantity;
    double askPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(int bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public int getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(int askQuantity) {
        this.askQuantity = askQuantity;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }
}
