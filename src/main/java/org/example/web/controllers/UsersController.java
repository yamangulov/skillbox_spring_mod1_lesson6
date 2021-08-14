package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.app.services.RegisterService;
import org.example.web.dto.Book;
import org.example.web.dto.User;
import org.example.web.dto.UserIdToRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private final Logger logger = Logger.getLogger(UsersController.class);
    private final RegisterService registerService;

    @Autowired
    public UsersController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/control")
    public String users(Model model) {
        logger.info("got users list");
        model.addAttribute("user", new User());
        model.addAttribute("userIdToRemove", new UserIdToRemove());
        model.addAttribute("userList", registerService.getAllUsers());
        return "user_control";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            model.addAttribute("userList", registerService.getAllUsers());
            return "user_control";
        } else {
            registerService.registerUser(user);
            return "redirect:/users/control";
        }
    }

    @PostMapping("/unregister")
    public String unregisterUser(@Valid UserIdToRemove userIdToRemove, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            model.addAttribute("userList", registerService.getAllUsers());
            return "user_control";
        } else {
            registerService.unregisterUserById(userIdToRemove.getId());
            return "redirect:/users/control";
        }
    }
}
