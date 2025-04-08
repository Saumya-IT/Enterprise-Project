package com.va.week10;

import jakarta.servlet.http.HttpSession;
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
    public String homepage(HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        return redirect != null ? redirect : "order-home";
    }

    @GetMapping("/add")
    public String addForm(Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        model.addAttribute("order", new Order());
        SessionUtil.injectUser(model, session);
        return "order-add";
    }

    @PostMapping("/place")
    public String placeOrder(@ModelAttribute Order order, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        service.saveOrder(order);
        return "redirect:/orders/list";
    }

    @GetMapping("/list")
    public String getAllOrders(Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        model.addAttribute("orders", service.getAllOrders());
        SessionUtil.injectUser(model, session);
        return "order-list";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable String id, Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        Order order = service.getOrderById(id);
        model.addAttribute("order", order);
        return "order-edit";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Order order, HttpSession session, RedirectAttributes redirectAttrs) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        service.updateOrder(order);
        redirectAttrs.addFlashAttribute("message", "✅ Order updated successfully.");
        return "redirect:/orders/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttrs) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        service.deleteOrderById(id);
        redirectAttrs.addFlashAttribute("message", "🗑️ Order deleted successfully.");
        return "redirect:/orders/list";
    }
    
    @GetMapping("/ui")
    public String ui(Model model, HttpSession session) {
        String redirect = SessionUtil.redirectIfNotLoggedIn(session);
        if (redirect != null) return redirect;

        model.addAttribute("orders", service.getAllOrders());
        model.addAttribute("order", new Order());
        SessionUtil.injectUser(model, session);
        return "order-list";
    }
}
