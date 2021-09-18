package com.company;

import java.time.LocalDateTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Store {

    private List<Customer> customers;
    private boolean isOpen; // указатель рабочего времени

    private static final int minCustomersInStore = 0;
    private static final int maxCustomersInStore = 5;

    public Store() {
        customers = new ArrayList<>();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    // Клиенты заходят в магазин
    public boolean add(Customer customer) {

        if (!isOpen() || customers.size() == maxCustomersInStore) {
            System.out.println("Клиент уходит ");
            return false;
        } else if (customers.size() < maxCustomersInStore) {
            customers.add(customer);
            System.out.println("В магазин зашел "
                    + customer.getName() + " на "
                    + customer.getPlanTimeShopping() + " c. В магазине клиентов: " + customers.size());
        }
        return true;
    }

    // Клиенты покидают магазин
    public List<Customer> get() {

        while (true) {
            if (!isOpen()) { //если перерыв - все выходят
                customers.clear();
            }
            // уходят в рабочее время по истечении запланированного времени
            else if (customers.size() > minCustomersInStore) {
                LocalDateTime currentTime = LocalDateTime.now();
                for (int i = 0; i < customers.size(); i++) {
                    long timeInStore = SECONDS.between(customers.get(i).getTimeGenarate(), currentTime);
                    long planTimeInStore = customers.get(i).getPlanTimeShopping();
                    if (timeInStore >= planTimeInStore) {
                        System.out.println(customers.get(i).getName() + " вышел из магазина через " +
                                planTimeInStore + " c.");
                        customers.remove(i);
                    }
                }
                return customers;
            }
            return null;
        }
    }
}