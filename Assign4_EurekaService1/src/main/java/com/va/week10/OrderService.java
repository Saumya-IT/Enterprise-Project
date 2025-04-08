package com.va.week10;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(String id);
    void updateOrder(Order order);
    void deleteOrderById(String id);
}