package me.ilizin.certification.lambdaexpression;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaExpressionPredicate {

    static record Car(String company, int year, double price, String type) {
        public String toString() {  return "(" + company + " " + year + ")"; }
    }

    static class CarMall {
        List<Car> cars = new ArrayList<>();

        CarMall() {
            cars.add(new Car("Honda", 2012, 9000.0, "HATCH"));
            cars.add(new Car("Honda", 2018, 17000.0, "SEDAN"));
            cars.add(new Car("Toyota", 2014, 19000.0, "SUV"));
            cars.add(new Car("Ford", 2014, 13000.0, "SPORTS"));
            cars.add(new Car("Nissan", 2017, 8000.0, "SUV"));
        }

        List<Car> showCars(Predicate<Car> cp){
            ArrayList<Car> carsToShow = new ArrayList<>();
            for(Car c : cars){
                if(cp.test(c)) carsToShow.add(c);
            }
            return carsToShow;
        }
     }

    /* The Java standard library includes a generic interface for filtering through a list of objects, It looks like this.

       interface Predicate<T> {
          boolean test(T t);
       }

       If the name of that class a Predicate is typed to is T, then the method test will accept an object of type T
       and return a boolean.
       The above showCars code is the same as previous one. But by using the Predicate interface instead of writing a custom
       interface, we have eliminated another three lines of code.
     */

    public static void main(String[] args) {
        CarMall cm = new CarMall();

        /* There is no change in the code that calls showCars method. The lambda expression that we used earlier, i.e.,
           cm.showCars(c -> c.company.equals("Honda")) works for this new method as well. It works because the
           lambda expression never required us to use the name of any interface or method. Lambda expression
           was not tied to a particular interface or method. It was only tied to a particular behavior, to a method
           that takes Car as an argument and returns a boolean. */
        cm.showCars(c -> c.company.equals("Honda"));

        /* The Predicate interface has three default methods and one static method in addition to the abstract test method.
           They have nothing to do with lambda expressions. You will notice that these methods are basically just helpful
           utility methods.

           1. default Predicate<T> and(Predicate<? super T> other):
                Returns a composed predicate that represents a short-circuiting logical AND of this predicate and another. */
        Predicate<Car> p1 = c -> c.company.equals("Honda");
        Predicate<Car> p2 = c -> c.price>(20000.0);

        Car c = new Car("Honda", 2012, 9000.0, "HATCH");
        if(p1.test(c) && p2.test(c)) {
            System.out.println("yes");
        }

        /* You could combine the two predicates into one and use only one call to test. */
        Predicate<Car> p3 = p1.and(p2);
        if (p3.test(c)) {
            System.out.println("yes");
        }

        /* 2. default Predicate<T> negate():
                Returns a predicate that represents the logical negation of this predicate. */
           Predicate<Car> p = c2 -> c2.price < 20000;
           Predicate<Car> notP = p.negate();

        /* 3. default Predicate<T> or(Predicate<? super T> other):
                Returns a composed predicate that represents a short-circuiting logical OR of this predicate and another. */
        Predicate<Car> isHonda = c3 -> c3.company.equals("Honda");
        Predicate<Car> isToyota = c4 -> c4.company.equals("Toyota");
        Predicate<Car> isHondaOrToyota = isHonda.or(isToyota);

        /* 4. static <T> Predicate<T> isEqual(Object targetRef):
                Returns a predicate that tests if two arguments are equal according to Objects.equals(Object, Object).
              For example, normally, you would compare two Car objects using c1.equals(c2).
              You could create a Predicate out of the equals method like this and then compare c1 with other Car objects
              using this Predicate, i.e., equals.test(c2). */

        Predicate equals = Predicate.isEqual(c);
        Car c2 = new Car("Honda", 2012, 9000.0, "HATCH");
        equals.test(c2);
    }
}
