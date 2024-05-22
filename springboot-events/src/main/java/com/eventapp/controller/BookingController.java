package com.eventapp.controller;

import com.eventapp.data.HotelBookRequest;
import com.eventapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookingController {

    ReservationService reservationService;
    @PostMapping
    public void bookHotel(@RequestBody HotelBookRequest hotelBookRequest){

        reservationService.publisReservationEvent(hotelBookRequest);
        System.out.println("Kullanıcı işlemi isteğe alındı !" +hotelBookRequest);
    }

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}
