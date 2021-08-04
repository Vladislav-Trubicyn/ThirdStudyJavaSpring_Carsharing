package ru.carsharing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.carsharing.model.Car;

import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long>
{
    Iterable<Car> findByAccess(boolean access);
}
