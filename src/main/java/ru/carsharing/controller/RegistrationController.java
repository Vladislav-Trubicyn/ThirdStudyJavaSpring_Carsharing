package ru.carsharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.carsharing.model.Role;
import ru.carsharing.model.User;
import ru.carsharing.service.UserService;

import java.util.Collections;

@Controller
public class RegistrationController
{
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public String addNewUser(@ModelAttribute User user, Model model)
    {
        User userFromDb = userService.findByUsername(user.getUsername());

        if(userFromDb == null)
        {
            user.setRoles(Collections.singleton(Role.USER));
            user.setAction(true);

            userService.saveUser(user);
            return "login";
        }
        else
        {
            model.addAttribute("errMessage", "Данный ник уже присутствует в системе");
            return "registration";
        }
    }
}
