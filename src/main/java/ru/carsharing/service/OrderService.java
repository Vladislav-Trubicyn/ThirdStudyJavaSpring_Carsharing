package ru.carsharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.carsharing.model.Order;
import ru.carsharing.repository.OrderRepository;

@Service
public class OrderService
{
    @Autowired
    OrderRepository orderRepository;

    public Iterable<Order> findAllOrders()
    {
        return orderRepository.findAll();
    }

    public Iterable<Order> findByUserId(Long id)
    {
        return orderRepository.findByUserId(id);
    }

    public void saveOrder(Order order)
    {
        orderRepository.save(order);
    }

    public void deleteById(Long id)
    {
        orderRepository.deleteById(id);
    }

}
