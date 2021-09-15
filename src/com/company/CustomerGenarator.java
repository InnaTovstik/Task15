package com.company;

import java.time.LocalDateTime;

public class CustomerGenarator implements Runnable {

    private Store store;

    private static final int minTimesInShop = 1;
    private static final int maxTimesInShop = 8;

    public CustomerGenarator(Store store) {
        this.store = store;
    }

    @Override
    public void run() {

        while (true) {
            if (store.isStatus() == true) {
                int timeInStore = getRandomNumber(minTimesInShop, maxTimesInShop);
                String name = "Name" + getRandomNumber(0, 20);
                Customer currentCustomer = new Customer(LocalDateTime.now(), timeInStore, name);
                store.add(currentCustomer);
                store.get();
                try {
                Thread.sleep(CustomerGenarator.getRandomNumber(2, 5) * 1_000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        }
    }

    public static int getRandomNumber(int from, int to) {
        return from + (int) (Math.random() * ((to - from) + 1));
    }
}