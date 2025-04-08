package com.example.demo;

import java.util.List;

public interface MarketService {
    Market saveMarketOrder(Market order);
    List<Market> getAllOrders();
    Market getOrderById(String id);
    void updateOrder(Market order);
    void deleteOrderById(String id);
}