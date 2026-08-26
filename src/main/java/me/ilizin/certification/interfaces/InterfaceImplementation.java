package me.ilizin.certification.interfaces;

public class InterfaceImplementation {

    interface Movable {
        void move();
    }
    interface Readable {
        void read();
        Number sum();
        Number sum2() throws RuntimeException;
    }
    /* A class can implement any number of interfaces by specifying their names in its implements clause. For
       example, in the following code the Price class implements two interfaces.
        Of course, once a class declares that it implements an interface, it must then have the implementation for all
        of the abstract methods declared in that interface */
    class Price implements Movable, Readable {

        /* The method must be explicitly defined as publicbecause abstract methods of an interface are implicitly public
           and you cannot reduce accessibility of a method. */
        public void move() { System.out.println("Moving..."); }
        /* Although not mandatory, it is a good practice to apply @Override annotation to the method in the class. */
        @Override
        public void read() { System.out.println("Reading..."); }
        /* The return type must be covariantwith the return type declared by the interface method just like when
           overriding a method.  */
        public Integer sum() { return 0; }
        /* The throws clause must be compatible with the throws clause of the interface method, therefore the same
           exception or a child exception */
        public Number sum2() throws IllegalArgumentException, RuntimeException {
            return 0;
        }
        /*public Number sum2() throws Exception {
            return 0;
        }*/ // Invalid
    }

    interface Movable3 {
        void move();
        void move2();
    }
    /* If the class does not have implementation for even one of the abstract methods declared in the interface that it
       says it implements, the class must be declared abstract. Otherwise, the compiler will refuse to compile the
       class. */
    abstract class Price3 implements Movable3 {
        public void move() { System.out.println("Moving..."); }
    }

    interface Movable2 {
        default void move() { }
        default void move2() { }
    }
    /* If an interface provides a default implementation for a method in the form of a default method, the
       implementing class does not necessarily have to provide implementation for that method. */
    class Price2 implements Movable2 {
        public void move2() { }
    }

    interface Movable4 {
        void move();
        void move2();
    }
    abstract class Price4 implements Movable4 {
        public void move() { }
    }
    /* Note that the order of extends clause and implements clause is important, the extends clause must appear before
       the implements clause. */
    public class StockPrice extends Price4 implements Movable4 {
        public void move2() { }
    }

    interface Movable5 {
        static void p() {
            System.out.println("In Movable5.p()");
        }
    }
    class Price6 {
        static void p2() {
            System.out.println("In Price6.p2()");
        }
    }
    /* Unlike the static methods of a class, the static methods of an interface cannot be inherited. compile. To invoke
       a static method of an interface, one must use the <interface name>.<method name> syntax. */
    class Price5 extends Price6 implements Movable5 {
        void doSomething() {
            // p(); // Invalid
            p2();
            Movable5.p();
        }
    }

    interface Task {
        public default void doIt() {
            System.out.println("Doing Task");
        }
    }
    interface Activity {
        public default void doIt() {
            System.out.println("Doing Activity");
        }
    }
    /* Since it is possible for a class to implement multiple interfaces, it is possible for a class to inherit multiple
       implementations of a default method from more than one interface.
       Process implements two interfaces and since each of them contains a default method named doIt with the same signature,
       Process now has two implementations for the same method. This is a problem because when you call doIt method on a
       Process object, the JVM will not be able to determine which implementation of doIt to invoke.
       Java resolves this problem by forcing the class to provide an implementation of the method of its own to remove
       the ambiguity in invocation. */
    // class Process implements Task, Activity {} // Invalid
    class Process implements Task, Activity {
        public void doIt(){
            System.out.println("Doing Process");
        }
    }

    /* When a class inherits a method from its superclass and also from an interface that it implements, the version that
      it inherits from a superclass overrides the default method defined in any of the interfaces that it implements. */
    interface Task2 {
        public default void doIt() {
            System.out.println("Doing Task");
        }
    }
    class BaseProcess {
        public void doIt() { System.out.println("Running process"); }
    }
    class Process2 extends BaseProcess implements Task2 {
    }

    interface Task3 {
        default void doIt() {
            System.out.println("Doing Task");
        }
    }
    interface Activity3 {
        void doIt();
    }
    /* Observe that doIt method is declared by both the interfaces but only one of them provides an implementation.
       Thus, Process inherits only one implementation for doIt method. There is no ambiguity for the JVM in determining
       which implementation of doIt to invoke. Therefore, it should compile, right? Wrong! */
    class Process3 implements Task3, Activity3 {
        public void doIt() {
            System.out.println("Doing Task");
        }
    }


    public static void main(String[] args) {

    }
}
