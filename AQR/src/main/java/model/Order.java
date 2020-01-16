package model;

public class Order {
    private String symbol;
    private int quantity;
    private Side side;
    private double price;

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Side getSide() {
        return side;
    }
    public void setSide(Side side) {
        this.side = side;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Order() {}

    public Order(String _symbol, Side _side, int _quantity, double _price) {
        symbol = _symbol;
        side = _side;
        quantity = _quantity;
        price = _price;
    }
}
