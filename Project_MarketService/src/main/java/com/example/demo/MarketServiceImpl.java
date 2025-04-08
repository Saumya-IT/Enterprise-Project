package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository repository;

    @Override
    public Market saveMarketOrder(Market order) {
        order.setConfirmationStatus("PLACED");
        order.setTypeOfExchange("NYSE");
        order.setLast((order.getBid() + order.getAsk()) / 2);
        repository.save(order);

        ObjectMapper mapper = new ObjectMapper();
        try {
            File dir = new File("market-json");
            if (!dir.exists()) dir.mkdirs();
            mapper.writeValue(new File(dir, "market_" + order.getOrderId() + ".json"), order);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Market> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Market getOrderById(String id) {
        Optional<Market> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public void updateOrder(Market order) {
        repository.save(order);
    }

    @Override
    public void deleteOrderById(String id) {
        repository.deleteById(id);
    }
}