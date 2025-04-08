package com.example.demo;

import jakarta.servlet.http.HttpSession;
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
    public String home(HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        return redirect != null ? redirect : "transaction-home";
    }

    @GetMapping("/add")
    public String addForm(Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        model.addAttribute("transaction", new AcctTransaction());
        SessionUtil.injectUser(model, session);
        return "transaction-add";
    }

    @PostMapping("/process")
    public String processTransaction(@ModelAttribute AcctTransaction tx, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        transactionService.saveTransaction(tx);
        return "redirect:/transactions/list";
    }

    @GetMapping("/list")
    public String getAllTransactions(Model model, HttpSession session, @ModelAttribute("message") String message) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        model.addAttribute("transactions", transactionService.getAllTransactions());
        model.addAttribute("message", message);
        SessionUtil.injectUser(model, session);
        return "transaction-list";
    }

    @GetMapping("/edit/{id}")
    public String editTransaction(@PathVariable String id, Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        AcctTransaction tx = transactionService.getTransactionById(id);
        model.addAttribute("transaction", tx);
        return "transaction-edit";
    }

    @PostMapping("/update")
    public String updateTransaction(@ModelAttribute AcctTransaction tx, HttpSession session, RedirectAttributes redirectAttrs) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        transactionService.updateTransaction(tx);
        redirectAttrs.addFlashAttribute("message", "Transaction updated successfully!");
        return "redirect:/transactions/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttrs) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        transactionService.deleteTransactionById(id);
        redirectAttrs.addFlashAttribute("message", "Transaction deleted successfully!");
        return "redirect:/transactions/list";
    }

    @GetMapping("/portfolio")
    public String viewPortfolio(Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        model.addAttribute("portfolio", transactionService.getPortfolioSummary());
        return "portfolio";
    }

    @GetMapping("/all")
    @ResponseBody
    public Object getAllTransactionsJSON() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/ui")
    public String ui(Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        model.addAttribute("transactions", transactionService.getAllTransactions());
        SessionUtil.injectUser(model, session);
        return "transaction-list";
    }
}