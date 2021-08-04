package ru.carsharing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.carsharing.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>
{
    Iterable<Order> findByUserId(Long id);
}
