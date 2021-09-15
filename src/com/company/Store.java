package com.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static java.time.temporal.ChronoUnit.SECONDS;

public class Store {

    private List<Customer> customers;

    private static final int minCustomersInStore = 0;
    private static final int maxCustomersInStore = 5;
    private boolean isOpen = true;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public Store() {
        customers = new ArrayList<>();
    }

    // Добавление клиентов в магазин
    public synchronized boolean add(Customer customer) {

        if (customers.size() < maxCustomersInStore) {
            customers.add(customer);
            System.out.println("В магазин зашел клиент " + customer.getName());
        } else if (customers.size() == maxCustomersInStore) {
            System.out.println("В магазине нет места. Клиент проходит мимо ");
            return false;
        }
        return true;
    }

    // Клиенты покидают магазин
    public List<Customer> get() {

        if (isOpen() == true) {// в рабочее время
            if (customers.size() > minCustomersInStore) {
                for (int i = 0; i < customers.size(); i++) {
                    LocalDateTime currentTime = LocalDateTime.now();
                    long timeInStore = SECONDS
                            .between(customers
                                            .get(i)
                                            .getTimeGenarate()
                                    , currentTime);
                    long planTimeInStore = customers
                            .get(i)
                            .getTimeInStore();
                    if (timeInStore >= planTimeInStore) {
                        System.out.println(customers
                                .get(i)
                                .getName() + " вышел из магазина через " +
                                planTimeInStore + " c.");
                        customers.remove(i);
                    }
                }
                return customers;
            }
        } else { //перерыв
            customers.clear();
            System.out.println("Все клиенты вышли из магазина.");
            Thread.currentThread().interrupt();
        }
        return null;
    }
}