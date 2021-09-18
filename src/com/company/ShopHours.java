package com.company;

public class ShopHours implements Runnable {

    private Store store;

    public ShopHours(Store store) {
        this.store = store;
    }

    @Override
    public void run() {

        while (true) {
                store.setIsOpen(true);
                Thread.currentThread().setName("Статус работы магазина. ");
                System.out.println(Thread.currentThread().getName() + "Магазин открыт 30 c.");

                try {
                    Thread.sleep(30_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                store.setIsOpen(false);
                store.get();
                System.out.println("Все клиенты вышли из магазина.");
                System.out.println(Thread.currentThread().getName() + "Перерыв на 10 с.");
                try {
                   Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    System.out.println("  ");
                }
            }
        }
   }