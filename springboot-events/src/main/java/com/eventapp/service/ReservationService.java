package com.eventapp.service;

import com.eventapp.data.HotelBookRequest;
import com.eventapp.event.ReservationCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    ApplicationEventPublisher applicationEventPublisher;

    @Async
    public void publisReservationEvent(HotelBookRequest hotelBookRequest){
        applicationEventPublisher.publishEvent(new ReservationCreatedEvent(hotelBookRequest));

    }

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
