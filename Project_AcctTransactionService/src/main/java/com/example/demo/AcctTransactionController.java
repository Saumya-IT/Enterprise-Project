package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transactions")
public class AcctTransactionController {

    @Autowired
    private AcctTransactionService transactionService;

    @GetMapping("/home")
    public String home() {
        return "transaction-home";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("transaction", new AcctTransaction());
        return "transaction-add";
    }

    @PostMapping("/process")
    public String processTransaction(@ModelAttribute AcctTransaction tx) {
        transactionService.saveTransaction(tx);
        return "redirect:/transactions/list";
    }

    @GetMapping("/list")
    public String getAllTransactions(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        model.addAttribute("message", message);
        return "transaction-list";
    }

    @GetMapping("/edit/{id}")
    public String editTransaction(@PathVariable String id, Model model) {
        AcctTransaction tx = transactionService.getTransactionById(id);
        model.addAttribute("transaction", tx);
        return "transaction-edit";
    }

    @PostMapping("/update")
    public String updateTransaction(@ModelAttribute AcctTransaction tx, RedirectAttributes redirectAttrs) {
        transactionService.updateTransaction(tx);
        redirectAttrs.addFlashAttribute("message", "Transaction updated successfully!");
        return "redirect:/transactions/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable String id, RedirectAttributes redirectAttrs) {
        transactionService.deleteTransactionById(id);
        redirectAttrs.addFlashAttribute("message", "Transaction deleted successfully!");
        return "redirect:/transactions/list";
    }

    @GetMapping("/portfolio")
    public String viewPortfolio(Model model) {
        model.addAttribute("portfolio", transactionService.getPortfolioSummary());
        return "portfolio";
    }

    @GetMapping("/all")
    @ResponseBody
    public Object getAllTransactionsJSON() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/ui")
    public String ui(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "transaction-list";
    }
}
