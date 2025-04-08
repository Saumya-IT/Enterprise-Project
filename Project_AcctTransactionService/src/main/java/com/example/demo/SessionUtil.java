package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public class SessionUtil {

    public static boolean isUserLoggedIn(HttpSession session) {
        return session != null && session.getAttribute("loggedInUser") != null;
    }

    public static String redirectIfNotLoggedIn(HttpSession session) {
        return isUserLoggedIn(session) ? null : "redirect:/user/login";
    }

    public static void injectUser(Model model, HttpSession session) {
        if (isUserLoggedIn(session)) {
            model.addAttribute("user", session.getAttribute("loggedInUser"));
        }
    }
}
