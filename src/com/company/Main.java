package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//В городе успешно работает ювелирный магазин, так как к магазину постоянно приходят клиенты.
// Из-за карантина в магазине не может одновременно находится больше 5 клиентов.
// Клиенты прохотят мимо магазина 1 раз в 2-5 секунд и заходят в магазин если он открыт и в нем есть место.
// Иначе клиент уходит. Магазин закрывается на 10-секундный перерыв один раз в 30 секунд.
// В перерыв все клиенты выходят из магазина и новые не могут зайти.
// Каждый клиент находится в магазине в промежутке от 1 до 8 секунд.

public class Main {

    public static void main(String[] args) {

        Store store = new Store();
        StoreWorkHours storeWorkHours = new StoreWorkHours(store);
        CustomerGenarator customerGenarator = new CustomerGenarator(store);

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*3);

        service.execute(storeWorkHours);
        service.execute(customerGenarator);

        service.shutdown();
   }
}