package me.ilizin.certification.lambdaexpression;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class StandardFunctionalInterfaces {

    static record Car(String company, int year, double price, String type) {
        public String toString() {  return "(" + company + " " + year + ")"; }
    }

    /* The OCP exam expects you to know about a few standard functional interfaces defined in the java.util.function
       package, namely, Predicate, Consumer, Supplier, Function, and BiFunction.
       All of them have exactly one abstract method (called as functional method) and are, therefore, functional
       interfaces. */
    public static void main(String[] args) {

        /* A Consumer<T> is meant to "consume" an object of type T, we want to do something with the given object.
           The functional method of the Consumer interface is named accept(T t). */

        /* Creating a Consumer that consumes a String, the lambda expression contains only the logic for consuming
           the String argument. It doesn't contain the actual data on which this logic is to be applied. */
        Consumer<String> strConsumer = s -> System.out.println(s.length());
        strConsumer.accept("hello"); //prints 5

        /* A Supplier<T> supplies an object of type T whenever invoked. Its functional method is T get() */
        Supplier<Car> carSupplier = () -> new Car("Honda", 2012, 9000.0, "HATCH");
        List<Car> cars = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            cars.add(carSupplier.get());
        }

        /* A Function<T, R> takes an argument of type T, performs some transformation on that argument,
           and returns the result of type R. Its functional method is R apply(T t).
           Besides the apply method, Function has two default methods, namely, andThen and compose.
           Besides the apply method, BiFunction has one default method, named, andThen. */
        Function<Car, String> carPropertyFunction = c2 -> c2.company();

        /* A BiFunction<T,U,R> is similar to (but does not extend) Function except that it takes two arguments of type T
        and U respectively, and returns a result of type R. Its functional method is R apply(T t, U u). */
        BiFunction<Car, String, Double> costBiF = (car, city) -> car.price() * 0.01;
        /* BiFunction is commonly used for implementing mathematical functions */
        BiFunction<Double, Double, Double> areaBiF = (a, b) -> a * b;

        /* A UnaryOperator<T> extends Function<T, T> and represents an operation on a single operand that produces a
           result of the same type as its operand. */
        UnaryOperator<Integer> flip = i -> -1 * i;

        /* The functional interfaces described above can be used only with reference types and not with primitive types.
           So, for example, you cannot do: BiFunction<double, double, double> areaBiF = (a, b) -> a * b;

           many existing interfaces in other JDK packages have also been marked with @FunctionalInterface annotation
           because they represent an independent function, among those:
           1. java.util.Comparator - Used while sorting collections.
           2. java.util.Comparator - Used while sorting collections. */
    }
}
