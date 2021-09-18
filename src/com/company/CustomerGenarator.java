package com.company;

import java.time.LocalDateTime;

public class CustomerGenarator implements Runnable {

    private Store store;

    private static final int minTimesInShop = 1;
    private static final int maxTimesInShop = 8;

    public CustomerGenarator(Store store) {
        this.store = store;
    }

    public static int getRandomNumber(int from, int to) {
        return from + (int) (Math.random() * ((to - from) + 1));
    }

    @Override
    public void run() {
        try {
            while (true) {
                int timeInStore = getRandomNumber(minTimesInShop, maxTimesInShop);
                String name = "Name" + getRandomNumber(0, 50);
                Customer currentCustomer = new Customer(timeInStore, name, LocalDateTime.now());
                store.add(currentCustomer);
                store.get();
                Thread.sleep(2_500);
                //Thread.sleep(CustomerGenarator.getRandomNumber(2, 5) * 1_000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}