package ru.home.lesson3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Worker[] workers = {
                new Turner("Mikk", 10.2),
                new Turner("Leon", 8.5),
                new Turner("Bill", 11.0),
                new Locksmith("Smith", 4500),
                new Locksmith("Ron", 4000),
                new Locksmith("Stitch", 5000)
        };
        System.out.println(Arrays.toString(workers));

        SortWorkers list = new WorkerList(workers);
        list.sortName();
        System.out.println("Список рабочих, отсортированный по имени");
        System.out.println(list);
        System.out.println();

        list.sortSalary();
        System.out.println("Список рабочих, отсортированный по зарплате");
        System.out.println(list);
        System.out.println();

        System.out.println("Информация о сотрудниках:");
        WorkerList workerList = new WorkerList(workers);
        workerList.printInfo();
    }
}
