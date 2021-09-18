package com.company;

import java.time.LocalDateTime;

public class Customer implements Comparable{
    private String name;
    private LocalDateTime timeGenarate;
    private int timeInStore;


    public Customer(LocalDateTime timeGenarate, int timeInStore, String name) {
        this.timeGenarate = timeGenarate;
        this.timeInStore = timeInStore;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public LocalDateTime getTimeGenarate() {
        return timeGenarate;
    }
    public int getTimeInStore() {
        return timeInStore;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}