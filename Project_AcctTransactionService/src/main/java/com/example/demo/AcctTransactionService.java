package com.example.demo;

import java.util.List;

public interface AcctTransactionService {
    AcctTransaction saveTransaction(AcctTransaction tx);
    List<AcctTransaction> getAllTransactions();
    AcctTransaction getTransactionById(String id);
    void updateTransaction(AcctTransaction tx);
    void deleteTransactionById(String id);
    List<Portfolio> getPortfolioSummary();
}