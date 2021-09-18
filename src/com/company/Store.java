package com.company;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Store {

    //private List<Customer> customers;
    private TreeSet<Customer> customers;
    private static final int minCustomersInStore = 0;
    private static final int maxCustomersInStore = 5;
    private boolean isOpen = true;

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Store() {
        // customers = new ArrayList<>();
        customers = new TreeSet<>();
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
    //public List<Customer> get() {

    public TreeSet<Customer> get() throws NoSuchElementException {
        long timeInStore;
        long planTimeInStore;

        if (!isOpen()) { //перерыв
            customers.clear();
            System.out.println("Все клиенты вышли из магазина.");
            Thread.currentThread().interrupt();
        }
        // в рабочее время
        else if (customers.size() > minCustomersInStore) {
            LocalDateTime currentTime = LocalDateTime.now();
            Iterator<Customer> iterator = customers.iterator();
            if (iterator.hasNext()) {
                timeInStore = SECONDS.between(iterator
                                .next()
                                .getTimeGenarate()
                        , currentTime);
                planTimeInStore = iterator.next()
                        .getTimeInStore();
                if (timeInStore >= planTimeInStore) {
                    System.out.println(iterator.next()
                            .getName() + " вышел из магазина через " +
                            planTimeInStore + " c.");
                    customers.remove(iterator.next());
                }
                return customers;
            } else {
                timeInStore = SECONDS.between(customers
                                .first()
                                .getTimeGenarate()
                        , currentTime);
                planTimeInStore = customers.first()
                        .getTimeInStore();
                if (timeInStore >= planTimeInStore) {
                    System.out.println(iterator.next()
                            .getName() + " вышел из магазина через " +
                            planTimeInStore + " c.");
                    customers.remove(iterator.next());
                }
                return customers;
            }
        }
        return null;
    }
}


//                    long timeInStore = SECONDS
//                            .between(customers
//                                            .get(i)
//                                            .getTimeGenarate()
//                                    , currentTime);
//                    long planTimeInStore = customers
//                            .get(i)
//                            .getTimeInStore();

//                    long timeInStore = SECONDS
//                            .between(customers
//                                            .first()
//                                            .getTimeGenarate()
//                                    , currentTime);
//                    long planTimeInStore = customers
//                            .first()
//                            .getTimeInStore();
//                    if (timeInStore >= planTimeInStore) {
//                        System.out.println(customers
//                                .first()
//                                .getName() + " вышел из магазина через " +
//                                planTimeInStore + " c.");
//                        customers.pollFirst();

