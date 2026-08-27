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

    interface Activity8{
        long SIZE = 20;
    }
    interface Task8 {
        long SIZE = 30;
    }
    /* Fields of an interface are inherited by a sub class and therefore Process8 does get two versions of SIZE
       variable. Java allows a class to inherit multiple fields with the same name as long as you don't try to use
       those fields ambiguously. */
    class Process8 implements Task8, Activity8 {
        public static void test(){
            // System.out.println(SIZE); //will not compile
            System.out.println(Task8.SIZE);
            System.out.println(Activity8.SIZE);
        }
    }

    interface Readable1 {
        int SIZE = 0;
        void read();
    }
    interface Writable1 {
        void write();
    }
    /* It is possible for an interface to extend any number of interfaces. A class cannot extend an interface, it can
       only implement an interface. Whereas, an interface cannot implement any interface it can only extend an
       interface. The extending interface inherits all the members except static methods of each of the other extended
       interfaces. */
    interface ReadWritable extends Readable1, Writable1 {
        //inherits SIZE and read() from Readable
        //inherits write() from Writable
        void delete();
    }

    interface Readable2 {
        int SIZE = 10;
        void read();
        static void staticMethod(){
            System.out.println("In Readable.staticMethod");
        };
        default void defaultMethod(){
            System.out.println("In Readable.defaultMethod");
        };
    }
    interface Writable2 {
        int SIZE = 20;
        void write();
        static void staticMethod(){
            System.out.println("In Writable.staticMethod");
        };

        /* commenting the following two methods out
        default void defaultMethod() {
            System.out.println("In Writable.defaultMethod");
        };
        void defaultMethod(); */
    }
    /* It is possible for an interface to inherit a field or an abstract method with the same signature from two of
       its super interfaces. But inheriting multiple default methods or one default and one or more abstract methods
       with the same signature, Java does not allow it. If you uncomment either of the defaultMethods in Writable2,
       ReadWritable2 will fail to compile because it would be inheriting two different implementations (or one
       implementation and one declaration) of defaultMethod, and ReadWritable2 must provide its own implementation
       of the defaultMethod to resolve the ambiguity.
       Observe that staticMethod is also defined in both the super interfaces but it does not cause any problem because
       static methods of an interface are never inherited, which means ReadWritable2 does not get even a single
       implementation of staticMethod from either of its superinterfaces. */
    interface ReadWritable2 extends Readable2, Writable2 {
        //inherits SIZE, read(), and defaultMethod() from Readable
        //inherits SIZE and write() from Writable
    }

    public static void main(String[] args) {

    }
}
