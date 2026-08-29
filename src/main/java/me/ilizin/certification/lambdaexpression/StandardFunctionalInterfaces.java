package me.ilizin.certification.lambdaexpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class StandardFunctionalInterfaces {

    static record Car(String company, int year, double price, String type) {
        public String toString() {  return "(" + company + " " + year + ")"; }
    }

    /* The OCP exam expects you to know about a few standard functional interfaces defined in the java.util.function
       package, namely: Predicate, Consumer, Supplier, Function, and BiFunction.
       All of them have exactly one abstract method (called as functional method) and are, therefore, functional
       interfaces.

       Many existing interfaces in other JDK packages have also been marked with @FunctionalInterface annotation
           because they represent an independent function, among those:
           1. java.util.Comparator - Used while sorting collections.
           2. java.lang.Runnable- Used while creating new threads. */
    public static void main(String[] args) {

        System.out.println();
        /* A Consumer<T> is meant to "consume" an object of type T, we want to do something with the given object.
           The functional method of the Consumer interface is named void accept(T t).
           Creating a Consumer that consumes a String, the lambda expression contains only the logic for consuming
           the String argument. */
        Consumer<String> strConsumer = s -> System.out.println(s.length());
        strConsumer.accept("hello");

        System.out.println();
        /* A Supplier<T> supplies an object of type T whenever invoked. Its functional method is T get() */
        Supplier<Car> carSupplier = () -> new Car("Honda", 2012, 9000.0, "HATCH");
        List<Car> cars = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            System.out.println(carSupplier.get());
            cars.add(carSupplier.get());
        }

        System.out.println();
        /* A Function<T, R> takes an argument of type T, performs some transformation on that argument,
           and returns the result of type R. Its functional method is R apply(T t).
           Besides the apply method, Function has two default methods, namely, andThen and compose. */
        Function<Car, String> carPropertyFunction = c2 -> c2.company();
        String company = carPropertyFunction.apply(new Car("Honda", 2012, 9000.0, "HATCH"));
        System.out.println(company);

        System.out.println();
        /* A BiFunction<T,U,R> is similar to (but does not extend) Function except that it takes two arguments of type T
        and U respectively, and returns a result of type R. Its functional method is R apply(T t, U u). */
        BiFunction<Car, String, Double> costBiF = (car, city) -> car.price() * 0.01;
        /* BiFunction is commonly used for implementing mathematical functions */
        BiFunction<Double, Double, Double> areaBiF = (a, b) -> a * b;
        double result = areaBiF.apply(3d, 4d);
        System.out.println(result);

        System.out.println();
        /* A UnaryOperator<T> extends Function<T, T> and represents an operation on a single operand that produces a
           result of the same type as its operand. */
        UnaryOperator<Integer> flip = i -> -1 * i;
        int res = flip.apply(3);
        System.out.println(res);

        /* The functional interfaces described above can be used only with reference types and not with primitive types. */
        // BiFunction<double, double, double> areaBiF2 = (a, b) -> a * b;

        System.out.println();
        /* Iterating through a collection is very common requirement. Prior to Java 8, a common way to iterate through
           a collection was to use a regular for loop. The Collection interface actually extends java.lang.Iterable interface
           and so, it was also possible to use the enhanced for loop (aka the for-each loop) for this purpose (Java 5).
           With Java 8, a default method named forEach(Consumer<E> consumer)was added to the Iterable interface, */
        List<String> list = List.of("a", "b", "c");
        // Old way
        for(String s : list){
            System.out.println(s);
        }
        // New way
        list.forEach(s -> System.out.println(s));

        System.out.println();
        /* The Map interface defines a default forEach method that takes a BiConsumer instead of Consumer.
           Here is an example of how it is used to process the elements of a Map: */
        BiConsumer<String, Integer> bc = (s, i) -> System.out.println(s + " is mapped to " + i);
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.forEach(bc);

        System.out.println();
        /* Another common operation performed with collections is filtering. The Collection interface has a default
           removeIf(Predicate<? super E> filter) method for this purpose. This method removes all of the elements of this
           list that satisfy the given predicate. */
        List<Integer> iList = List.of(1, 2, 3, 4, 5, 6);
        System.out.println(iList);
        Predicate<Integer> p = x -> x % 2 == 0;
        iList.removeIf(p);
        System.out.println(iList);

        System.out.println();
        /* A collection has no notion of order but a list does. It makes sense, therefore, that List interface has a
           default sort(Comparator<? super E> comparator) method which allows you to sort the elements of this list using
           the sorting order determined by the comparator. The java.util.Comparator interface has been around since
           Java 1.2 but it has been made a functional interface in Java 8. Its functional method is
           int compare(T o1, T o2), which compares its two arguments and returns a negative integer, zero, or a
           positive integer if the first argument is less than, equal to, or greater than the second.

           If you want to sort the list in the reverse order, you can change the lambda expression to,
           (a, b) -> -a.compareTo(b). Observe the minus sign in front of a.compareTo(b). Observe that we are using the
           compareTo method available in the String class to compare two strings. */
        List<String> games = new ArrayList<>(List.of("football", "cricket", "baseball", "tennis"));
        System.out.println(games);
        games.sort( (a, b) -> a.compareTo(b));
        System.out.println(games);
        games.sort( (a, b) -> - a.compareTo(b));
        System.out.println(games);
    }
}
