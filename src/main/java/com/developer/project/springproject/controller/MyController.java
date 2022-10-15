package com.developer.project.springproject.controller;

import com.developer.project.springproject.entity.Customer;
import com.developer.project.springproject.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class MyController {

    private final CustomerService customerService;

    public MyController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/")
    public String showRegistrationPage(Model model) {
        model.addAttribute("registeredCustomer", new Customer());

        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginCustomer");
        return "login";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("loginCustomer");
        return "home";
    }

    @PostMapping("/register_customer")
    public String registerNewCustomer(@ModelAttribute("registeredCustomer") @Valid Customer customer,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registeredCustomer", customer);
            return "redirect:/";
        }

        if (customerService.checkPresence(customer.getEmail(), customer.getPassword())) {
            return "redirect:/";
        }
        customerService
                .registerCustomer(
                        customer.getEmail(),
                        customer.getName(),
                        customer.getSurname(),
                        customer.getPassword());

        return "redirect:/login";


    }

    @PostMapping("/login_customer")
    public String loginCustomer(@ModelAttribute Customer customer, Model model) {
            Customer loginned =
                    customerService.loginCustomer(customer.getEmail(), customer.getPassword());
            if (loginned != null) {
                model.addAttribute("userName", loginned.getName());
                model.addAttribute("userSurname", loginned.getSurname());

                return "/home";
            } else
                return "redirect:/";
    }

}
