package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @GetMapping("/home")
    public String home(HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        return redirect != null ? redirect : "market-home";
    }

    @GetMapping("/add")
    public String addForm(Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        model.addAttribute("marketOrder", new Market());
        SessionUtil.injectUser(model, session);
        return "market-add";
    }

    @PostMapping("/place")
    public String placeOrder(@ModelAttribute Market order, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        marketService.saveMarketOrder(order);
        return "redirect:/market/list";
    }

    @GetMapping("/list")
    public String listOrders(Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        model.addAttribute("marketOrders", marketService.getAllOrders());
        SessionUtil.injectUser(model, session);
        return "market-list";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable String id, Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        Market order = marketService.getOrderById(id);
        model.addAttribute("marketOrder", order);
        return "market-edit";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Market order, HttpSession session, RedirectAttributes redirectAttrs) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        marketService.updateOrder(order);
        redirectAttrs.addFlashAttribute("message", "Market order updated successfully!");
        return "redirect:/market/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttrs) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        marketService.deleteOrderById(id);
        redirectAttrs.addFlashAttribute("message", "Market order deleted successfully!");
        return "redirect:/market/list";
    }

    @GetMapping("/all")
    @ResponseBody
    public Object getAllOrders() {
        return marketService.getAllOrders();
    }

    @GetMapping("/ui")
    public String ui(Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        model.addAttribute("marketOrders", marketService.getAllOrders());
        SessionUtil.injectUser(model, session);
        return "market-list";
    }
}