package model;

import java.util.*;

public class SymbolOrderRankings {
    String symbol;

    public SortedSet<OrderFact> getBids() {
        return bids;
    }

    public SortedSet<OrderFact> getAsks() {
        return asks;
    }

    private SortedSet<OrderFact> bids = new TreeSet<>((o1, o2) -> Double.compare(o2.get_price(), o1.get_price()));
    private SortedSet<OrderFact> asks = new TreeSet<>(Comparator.comparingDouble(OrderFact::get_price));

    public SymbolOrderRankings(String _symbol) {
        symbol = _symbol;
    }

    public void addBid(OrderFact orderFact) {
        bids.add(orderFact);
    }

    public void addAsk(OrderFact orderFact) {
        asks.add(orderFact);
    }

//    public Set<OrderFact> getTopNBids(int n) {
//        int limit = Integer.min(Integer.min(asks.size(), bids.size()), n);
//        return bids.stream()
//                //.sorted(((o1, o2) -> Double.compare(o2.get_price(),o1.get_price())))
//                .limit(limit)
//                .collect(Collectors.toSet());
//    }
//
//    public Set<OrderFact> getTopNAsks(int n) {
//        int limit = Integer.min(Integer.min(asks.size(), bids.size()), n);
//        return asks.stream()
//                //.sorted((Comparator.comparingDouble(OrderFact::get_price)))
//                .limit(limit)
//                .collect(Collectors.toSet());
//    }
}
