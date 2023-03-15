package ru.krizhanovsky.WeChat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImControllers {

    @GetMapping("/im")
    public String im(Model model, @RequestParam(value = "sel", required = false) String sel) {
        if (sel != null && !sel.isEmpty()) {
            System.out.println(sel.substring(1));
            if (sel.charAt(0) == 'c') {
                sel = sel.substring(1);
                System.out.println(sel);
            }
        }
        return "im";
    }

}