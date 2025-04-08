package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/fees")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @GetMapping("/home")
    public String home() {
        return "fee-home";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("fee", new Fee());
        return "fee-add";
    }

    @PostMapping("/buy")
    public String buyFee(@ModelAttribute Fee fee, RedirectAttributes redirectAttrs) {
        feeService.saveBuyFee(fee);
        redirectAttrs.addFlashAttribute("message", "Buy fee recorded successfully!");
        return "redirect:/fees/list";
    }

    @PostMapping("/sell")
    public String sellFee(@ModelAttribute Fee fee, RedirectAttributes redirectAttrs) {
        feeService.saveSellFee(fee);
        redirectAttrs.addFlashAttribute("message", "Sell fee recorded successfully!");
        return "redirect:/fees/list";
    }

    @GetMapping("/list")
    public String getAllFees(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("fees", feeService.getAllFees());
        model.addAttribute("message", message);
        return "fee-list";
    }

    @GetMapping("/edit/{id}")
    public String editFee(@PathVariable String id, Model model) {
        Fee fee = feeService.getFeeById(id);
        model.addAttribute("fee", fee);
        return "fee-edit";
    }

    @PostMapping("/update")
    public String updateFee(@ModelAttribute Fee fee, RedirectAttributes redirectAttrs) {
        feeService.updateFee(fee);
        redirectAttrs.addFlashAttribute("message", "Fee updated successfully!");
        return "redirect:/fees/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteFee(@PathVariable String id, RedirectAttributes redirectAttrs) {
        feeService.deleteFeeById(id);
        redirectAttrs.addFlashAttribute("message", "Fee deleted successfully!");
        return "redirect:/fees/list";
    }

    @GetMapping("/all")
    @ResponseBody
    public Object getAllFeesJSON() {
        return feeService.getAllFees();
    }

    @GetMapping("/ui")
    public String ui(Model model) {
        model.addAttribute("fees", feeService.getAllFees());
        return "fee-list";
    }
}
