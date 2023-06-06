package ru.home.lesson3;

import java.util.Arrays;
import java.util.Comparator;

public record WorkerList(Worker[] arrays) implements SortWorkers {

    @Override
    public void sortName() {
        Arrays.sort(arrays, Comparator.comparing(Worker::getName));
    }

    @Override
    public void sortSalary() {
        Arrays.sort(arrays, Comparator.comparing(Worker::getCalcAverageMonthlySalary));
    }

    @Override
    public String toString() {
        return "WorkerList{" +
                "arrays=" + Arrays.toString(arrays) +
                '}';
    }

    public void printInfo(){

        for (Worker array : arrays) {
            System.out.println(array);
        }
    }
}
