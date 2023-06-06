package ru.home.lesson3;

public class Locksmith extends Worker {

    private final String name;

    private final double fixedMonthlyPayment;


    public Locksmith(String name, double fixedMonthlyPayment) {
        this.name = name;
        this.fixedMonthlyPayment = fixedMonthlyPayment;
    }

    public String getName() {
        return name;
    }


    @Override
    double getCalcAverageMonthlySalary() {
        return fixedMonthlyPayment;
    }


    @Override
    public String toString() {
        return "Locksmith{" +
                "name='" + name + '\'' +
                ", averageMonthlySalary=" + getCalcAverageMonthlySalary() +
                '}';
    }

}
