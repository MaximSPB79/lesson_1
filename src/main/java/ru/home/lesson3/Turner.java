package ru.home.lesson3;

public class Turner extends Worker {

    private final String name;

    private final double hourlyRate;


    public Turner(String name, double hourlyRate) {
        this.name = name;
        this.hourlyRate = hourlyRate;
    }


    public String getName() {
        return name;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    double getCalcAverageMonthlySalary() {

        double numberWorkingDays = 20.8;
        double numberHours = 8;

        return numberWorkingDays * numberHours * hourlyRate;
    }

    @Override
    public String toString() {
        return "Turner{" +
                "name='" + name + '\'' +
                ", averageMonthlySalary=" + getCalcAverageMonthlySalary() +
                '}';
    }

}
