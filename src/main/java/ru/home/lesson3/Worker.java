package ru.home.lesson3;

public abstract class Worker {

    private String name;

    public String getName() {
        return name;
    }

    abstract double getCalcAverageMonthlySalary();

}