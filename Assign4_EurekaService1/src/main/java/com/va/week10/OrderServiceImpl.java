package com.va.week10;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order saveOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        repository.save(order);

        ObjectMapper mapper = new ObjectMapper();
        try {
            File dir = new File("orders-json");
            if (!dir.exists()) dir.mkdirs();
            mapper.writeValue(new File(dir, "order_" + order.getOrderId() + ".json"), order);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            restTemplate.postForObject("http://localhost:8082/market/place", order, String.class);
        } catch (Exception e) {
            System.out.println("Market service error: " + e.getMessage());
        }

        try {
            AcctTransaction tx = new AcctTransaction();
            tx.setTransactionId(order.getOrderId());
            tx.setOrderId(order.getOrderId());
            tx.setTransactionAmount(order.getOrderAmt());
            tx.setTransactionType(order.getOrderType_BuyOrSell());
            tx.setTransactionDate(order.getOrderDate().toString());

            restTemplate.postForObject("http://localhost:8083/transactions/process", tx, String.class);
        } catch (Exception e) {
            System.out.println("Transaction service error: " + e.getMessage());
        }

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrderById(String id) {
        Optional<Order> opt = repository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public void updateOrder(Order order) {
        repository.save(order);
    }

    @Override
    public void deleteOrderById(String id) {
        repository.deleteById(id);
    }
}