package model;

public class OrderFact {
    private double _price;
    private int _quantity;

    public double get_price() {
        return _price;
    }
    public void set_price(Float _price) {
        this._price = _price;
    }

    public int get_quantity() {
        return _quantity;
    }
    public void set_quantity(int _quantity) {
        this._quantity = _quantity;
    }

    public OrderFact(Order order) {
        _price = order.getPrice();
        _quantity = order.getQuantity();
    }
}
