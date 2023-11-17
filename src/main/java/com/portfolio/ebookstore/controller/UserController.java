package com.portfolio.ebookstore.controller;

import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ebookstore")
public class UserController {
    private final UserService userService;

    // LOGGED USER PANEL
    @GetMapping
    @RequestMapping("/user-details")
    public String getUserPanelView(Model model) {

        return "logged/user-details";
    }

    // NEW USER REGISTER
    @GetMapping
    @RequestMapping("/register")
    public String getAddCustomerView(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "main/registration";
    }

    @PostMapping
    @RequestMapping("/register/add")
    public String addCustomer(UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/ebookstore";
    }

}
