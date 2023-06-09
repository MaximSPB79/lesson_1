package ru.home.lesson1.sample;
import ru.home.lesson1.regular.Decorator;
import ru.home.lesson1.regular.OtherClass;

public class Main {

    public static void main(String[] args) {
        int result = OtherClass.add(2, 3);
        System.out.println(Decorator.decorate(result));
        result = OtherClass.sub(2, 3);
        System.out.println(Decorator.decorate(result));
        result = OtherClass.mul(2, 3);
        System.out.println(Decorator.decorate(result));
        result = OtherClass.div(2, 3);
        System.out.println(Decorator.decorate(result));
    }

}
