package com.company;

import java.time.LocalDateTime;

public class Customer {
    private int planTimeShopping;
    private String name;
    private LocalDateTime timeGenarate;

    public Customer( int timeInStore, String name, LocalDateTime timeGenarate) {
        this.timeGenarate = timeGenarate;
        this.planTimeShopping = timeInStore;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public LocalDateTime getTimeGenarate() {
        return timeGenarate;
    }
    public int getPlanTimeShopping() {
        return planTimeShopping;
    }
}