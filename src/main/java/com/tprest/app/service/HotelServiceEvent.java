package com.tprest.app.service;

import org.springframework.context.ApplicationEvent;

/**
 * Created by marwen on 23/03/16.
 */
public class HotelServiceEvent extends ApplicationEvent {

    public HotelServiceEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "My HotelService Event";
    }

}
