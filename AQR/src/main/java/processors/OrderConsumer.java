package processors;

import model.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderConsumer {
    private LinkedBlockingQueue<Order> orderQueue;
    private Map<String, SymbolOrderRankings> orderRankings = new HashMap();

    public OrderConsumer(LinkedBlockingQueue queue) {
        orderQueue=queue;
    }

    public void processOrders() {
        int count = 0;
        while (!orderQueue.isEmpty()) {
            count++;
            Order order = orderQueue.poll();
            if (orderRankings.containsKey(order.getSymbol())) {
                SymbolOrderRankings symbolOrderRankings = orderRankings.get(order.getSymbol());
                if (order.getSide() == Side.BUY)
                    symbolOrderRankings.addBid(new OrderFact(order));
                else
                    symbolOrderRankings.addAsk(new OrderFact(order));
            }
            else
            {
                SymbolOrderRankings symbolOrderRankings = new SymbolOrderRankings(order.getSymbol());
                if (order.getSide() == Side.BUY)
                    symbolOrderRankings.addBid(new OrderFact(order));
                else
                    symbolOrderRankings.addAsk(new OrderFact(order));
                orderRankings.put(order.getSymbol(), symbolOrderRankings);
            }
            if (count % 15 == 0)
                System.out.println(count + " orders parsed. Order rankings count: " + orderRankings.size());
        }
        System.out.println("Done processing " + count + " orders");
    }

    public Map<String, Set<Level>> report(int topN) {
        var toReport = new HashMap<String, Set<Level>>();
        orderRankings.forEach((k,v) -> {
            System.out.println("Processing symbol " + k);
            int limit = Integer.min(Integer.min(v.getAsks().size(), v.getBids().size()), topN);
            int count = 0;
            var askIterator = v.getAsks().iterator();
            var bidIterator = v.getBids().iterator();
            Set set = new LinkedHashSet();
            while (count < limit) {
                var ask = askIterator.next();
                var bid = bidIterator.next();
                set.add(new Level(ask.get_price(), ask.get_quantity(), bid.get_price(), bid.get_quantity()));
                count++;
            }
            toReport.put(k, set);
        });
        return toReport;
    }
}
