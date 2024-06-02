package com.exceptionHandling.service;

import com.exceptionHandling.dto.Car;
import com.exceptionHandling.exception.EntityNotFountException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private static List<Car> carList = new ArrayList<>();

    @PostConstruct
    //constructordan sonra 1 kere calısır. @Service dolayısıyla spring container'e eklenirken 1 kere nesnesini oluşturur. Ondan sonra 1 kere bu method da calısır.
    public void init() {
        carList.add(new Car("Car A", "Brand 1"));
        carList.add(new Car("Car B", "Brand 2"));
        carList.add(new Car("Car C", "Brand 3"));
    }

    public Car getCar(String name) {
        if(name.startsWith("1"))
        {
            throw new IllegalArgumentException();
        }
        return carList.stream().filter(item -> item.getName().equals(name)).findFirst().orElseThrow(() -> new EntityNotFountException(name));
    }
}
