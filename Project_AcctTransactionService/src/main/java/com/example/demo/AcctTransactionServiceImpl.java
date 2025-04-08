package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AcctTransactionServiceImpl implements AcctTransactionService {

    @Autowired
    private AcctTransactionRepository repository;

    @Override
    public AcctTransaction saveTransaction(AcctTransaction tx) {
        repository.save(tx);

        ObjectMapper mapper = new ObjectMapper();
        try {
            File dir = new File("transaction-json");
            if (!dir.exists()) dir.mkdirs();
            mapper.writeValue(new File(dir, "tx_" + tx.getTransactionId() + ".json"), tx);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tx;
    }

    @Override
    public List<Portfolio> getPortfolioSummary() {
        List<AcctTransaction> allTx = repository.findAll();

        Map<String, Portfolio> summary = new HashMap<>();
        for (AcctTransaction tx : allTx) {
            String ticker = tx.getTickerSymbol();
            if (!summary.containsKey(ticker)) {
                summary.put(ticker, new Portfolio(ticker));
            }
            Portfolio p = summary.get(ticker);
            if ("BUY".equalsIgnoreCase(tx.getTransactionType())) {
                p.setTotalBuyQuantity(p.getTotalBuyQuantity() + tx.getQuantity());
            } else if ("SELL".equalsIgnoreCase(tx.getTransactionType())) {
                p.setTotalSellQuantity(p.getTotalSellQuantity() + tx.getQuantity());
            }
        }

        return new ArrayList<>(summary.values());
    }

    @Override
    public List<AcctTransaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public AcctTransaction getTransactionById(String id) {
        Optional<AcctTransaction> tx = repository.findById(id);
        return tx.orElse(null);
    }

    @Override
    public void updateTransaction(AcctTransaction tx) {
        repository.save(tx);
    }

    @Override
    public void deleteTransactionById(String id) {
        repository.deleteById(id);
    }
}
