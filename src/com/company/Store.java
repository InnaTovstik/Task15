package com.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Store {

    private List<Customer> customers;

    private static final int minCustomersInStore = 0;
    private static final int maxCustomersInStore = 5;

    private boolean status = true;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
    public synchronized List<Customer> get() {

            if (isStatus() != false) {
                if (customers.size() > minCustomersInStore) {
                    for (int i = 0; i < customers.size(); i++) {
                        LocalDateTime currentTime = LocalDateTime.now();
                        long time = SECONDS.between(customers.get(i).getTimeGenarate(), currentTime);
                        long timeInStore = customers.get(i).getTimeInStore();
                        System.out.println(customers.get(i).getName() + " в магазине уже " + time + "с., заданное время = " + timeInStore);
                        if (time > timeInStore ) {
                            System.out.println(customers.get(i).getName() + " вышел из магазина через " +
                                timeInStore + " c.");
                            customers.remove(i);
                            }
                    }
                    return customers;
                 }
            } else {
              customers.clear();
              System.out.println("Все клиенты вышли из магазина.");
              Thread.currentThread().interrupt();
          }
        return null;
    }
}