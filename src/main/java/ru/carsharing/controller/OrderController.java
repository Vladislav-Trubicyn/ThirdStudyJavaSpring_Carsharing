package ru.carsharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.carsharing.model.Car;
import ru.carsharing.model.Order;
import ru.carsharing.model.Role;
import ru.carsharing.model.User;
import ru.carsharing.service.CarService;
import ru.carsharing.service.OrderService;

import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @Autowired
    private CarService carService;

    @GetMapping()
    public String showOrdersPage(@AuthenticationPrincipal User user, Model model)
    {
        if(user.getRoles().contains(Role.ADMIN))
        {
            Iterable<Order> orders = orderService.findAllOrders();
            model.addAttribute("orders", orders);
            model.addAttribute("roleAdmin", true);
        }
        else if(user.getRoles().contains(Role.USER))
        {
            Iterable<Order> orders = orderService.findByUserId(user.getId());
            model.addAttribute("orders", orders);
            model.addAttribute("roleAdmin", false);
        }

        return "orderlist";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editOrderAdmin(@PathVariable("id") Order order, Model model)
    {
        model.addAttribute("order", order);
        return "editorder";
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveEditOrderAdmin(@RequestParam("id") Order order,
                                     @RequestParam("commentAdmin") String commentAdmin,
                                     @RequestParam(value = "status", required = true, defaultValue = "false") boolean status)
    {
        order.setCommentAdmin(commentAdmin);
        order.setStatus(status);

        if(order.isStatus() == true)
        {
            Optional<Car> car = carService.findById(order.getCarId());
            car.get().setAccess(false);
            carService.saveCar(car.get());
        }

        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @PostMapping("/add")
    public String addOrderUser(@ModelAttribute Order order)
    {
        order.setStatus(false);
        orderService.saveOrder(order);
        return "redirect:/orders";
    }


}
