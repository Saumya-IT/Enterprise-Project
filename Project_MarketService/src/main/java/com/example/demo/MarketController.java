package com.example.demo;

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
    public String home() {
        return "market-home";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("marketOrder", new Market());
        return "market-add";
    }

    @PostMapping("/place")
    public String placeOrder(@ModelAttribute Market order) {
        marketService.saveMarketOrder(order);
        return "redirect:/market/list";
    }

    @GetMapping("/list")
    public String listOrders(Model model,
                             @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("marketOrders", marketService.getAllOrders());
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "market-list";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable String id, Model model) {
        Market order = marketService.getOrderById(id);
        model.addAttribute("marketOrder", order);
        return "market-edit";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Market order, RedirectAttributes redirectAttrs) {
        marketService.updateOrder(order);
        redirectAttrs.addFlashAttribute("message", "Market order updated successfully!");
        return "redirect:/market/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id, RedirectAttributes redirectAttrs) {
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
    public String ui(Model model) {
        model.addAttribute("marketOrders", marketService.getAllOrders());
        model.addAttribute("marketOrder", new Market());
        return "market-list";
    }
}
