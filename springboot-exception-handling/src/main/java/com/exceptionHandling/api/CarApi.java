package com.exceptionHandling.api;

import com.exceptionHandling.dto.Car;
import com.exceptionHandling.exception.EntityNotFountException;
import com.exceptionHandling.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarApi {
    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<Car> getCar(@RequestParam String name) {
        return ResponseEntity.ok(carService.getCar(name));
    }

//    @ExceptionHandler({EntityNotFountException.class}) //sadece bu controller için çalışır.
//    public String entityNotFound(){
//        return "Record not found.";
//    }


}
