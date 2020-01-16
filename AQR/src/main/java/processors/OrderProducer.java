package processors;

import com.fasterxml.jackson.jr.ob.JSON;
import model.Order;
import model.Side;
import model.TopOfTheBook;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class OrderProducer {
    private BlockingQueue<Order> queue;

    public OrderProducer(BlockingQueue _queue) {
        this.queue=_queue;
    }

    public void parseOrder(String orderContent) throws IOException {
        if (orderContent.contains("bidQuantity")) {
            TopOfTheBook top = JSON.std.beanFrom(TopOfTheBook.class,orderContent);
            Order bidOrder = new Order(top.getSymbol(), Side.BUY, top.getBidQuantity(), top.getBidPrice());
            Order askOrder = new Order(top.getSymbol(), Side.SELL, top.getAskQuantity(), top.getAskPrice());
            queue.offer(bidOrder);
            queue.offer(askOrder);
        }
        else {
            Order order = JSON.std.beanFrom(Order.class,orderContent);
            queue.offer(order);
        }
    }
}
