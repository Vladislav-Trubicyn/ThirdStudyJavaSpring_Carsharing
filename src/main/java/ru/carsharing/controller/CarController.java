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

@Controller
@RequestMapping("/cars")
public class CarController
{
    @Autowired
    private CarService carService;

    @GetMapping()
    public String showCarsPage(@AuthenticationPrincipal User user, Model model)
    {
        if(user.getRoles().contains(Role.ADMIN))
        {
            Iterable<Car> cars = carService.findAllCars();
            model.addAttribute("cars", cars);
            model.addAttribute("roleAdmin", true);
        }
        else if(user.getRoles().contains(Role.USER))
        {
            Iterable<Car> cars = carService.findAllByAccess(true);
            model.addAttribute("cars", cars);
            model.addAttribute("roleAdmin", false);
        }

        return "carlist";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAddCarPageAdmin(@ModelAttribute Car car)
    {
        return "addcar";
    }

    @PostMapping("/addcar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addCarAdmin(@ModelAttribute Car car)
    {
        car.setAccess(true);
        carService.saveCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editSelectedCar(@PathVariable("id") Car car, Model model)
    {
        model.addAttribute("car", car);
        return "editcar";
    }

    @GetMapping("/{id}/select")
    public String selectCarUser(@AuthenticationPrincipal User user, @PathVariable("id") Car car, @ModelAttribute Order order, Model model)
    {
        model.addAttribute("car", car);
        model.addAttribute("user_id", user.getId());
        return "addorder";
    }

}
