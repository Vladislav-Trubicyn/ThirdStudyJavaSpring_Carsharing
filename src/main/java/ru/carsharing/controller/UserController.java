package ru.carsharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.carsharing.model.Role;
import ru.carsharing.model.User;
import ru.carsharing.service.UserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUsersPage(Model model)
    {
        model.addAttribute("users", userService.findAllUsers());
        return "userlist";
    }

    @GetMapping("/user/{id}/edit")
    public String editSelectedUser(@PathVariable("id") User user, Model model)
    {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "useredit";
    }

    @PostMapping("/useredit")
    public String saveEditSelectedUser(@RequestParam Map<String, String> form,
                                       @RequestParam("id") User user,
                                       @RequestParam("username") String username,
                                       @RequestParam(value = "action", required = true, defaultValue = "false") boolean action, Model model)
    {
        user.setAction(action);
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();

        for(String key : form.keySet())
        {
            if(roles.contains(key))
            {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userService.saveUser(user);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "useredit";
    }
}
