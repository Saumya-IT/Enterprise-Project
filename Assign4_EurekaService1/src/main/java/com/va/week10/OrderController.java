package com.va.week10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/home")
    public String homepage() {
        return "order-home";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("order", new Order());
        return "order-add";
    }

    @PostMapping("/place")
    public String placeOrder(@ModelAttribute Order order) {
        service.saveOrder(order);
        return "redirect:/orders/list";
    }

    @GetMapping("/list")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", service.getAllOrders());
        return "order-list";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable String id, Model model) {
        Order order = service.getOrderById(id);
        model.addAttribute("order", order);
        return "order-edit";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Order order, RedirectAttributes redirectAttrs) {
        service.updateOrder(order);
        redirectAttrs.addFlashAttribute("message", "✅ Order updated successfully.");
        return "redirect:/orders/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id, RedirectAttributes redirectAttrs) {
        service.deleteOrderById(id);
        redirectAttrs.addFlashAttribute("message", "🗑️ Order deleted successfully.");
        return "redirect:/orders/list";
    }

    @GetMapping("/ui")
    public String ui(Model model) {
        model.addAttribute("orders", service.getAllOrders());
        model.addAttribute("order", new Order());
        return "order-list";
    }
}
