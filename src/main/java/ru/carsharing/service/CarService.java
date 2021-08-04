package ru.carsharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.carsharing.model.Car;
import ru.carsharing.repository.CarRepository;

import java.util.Optional;

@Service
public class CarService
{
    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> findAllCars()
    {
        return carRepository.findAll();
    }

    public void saveCar(Car car)
    {
        carRepository.save(car);
    }

    public Iterable<Car> findAllByAccess(boolean access)
    {
        return carRepository.findByAccess(access);
    }

    public Optional<Car> findById(Long id)
    {
        return carRepository.findById(id);
    }

}
