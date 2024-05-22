package com.eventapp.event.listener;

import com.eventapp.event.ReservationCreatedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ReservationEventListener implements ApplicationListener<ReservationCreatedEvent> {
    @Override
    public void onApplicationEvent(ReservationCreatedEvent reservationCreatedEvent) {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("EventListener --> " + reservationCreatedEvent.getSource().toString());
    }
}
