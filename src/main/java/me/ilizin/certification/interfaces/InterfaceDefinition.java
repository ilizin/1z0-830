package me.ilizin.certification.interfaces;

public class InterfaceDefinition {

    /* An interface is used to describe behavior as captured by a group of methods. From a purely OOP perspective,
       an interface should not contain any implementation. It should only contain method declarations. However, Java
       has always permitted interfaces to contain static fields. Furthermore, from Java 8, Java has permitted an interface
       to contain default methods and static methods and Java 9 has allowed an interface to contain private methods
       (static as well as non-static) as well.

       Everything declared inside an interface is implicitly public except methods that are explicitly declared private.
       This means that members of an interface are always public irrespective of whether you define them as public or not.
       Methods of an interface are meant to be implemented by the classes that implement the interface, methods of an
       interface cannot be declared final.

       An interface is implicitly abstract. Although legally valid, it would be redundant to declare an interface as abstract. */
    public interface Movable {
        void move1(int x); //OK, move1 is implicitly public
        public void move2(int x); //OK, move2 is explicitly public
        // public final void move8(); // Invalid
        // private void move3(int x); //NOT OK, abstract methods are always public
        // protected void move4(int x); //NOT OK, abstract methods are always public
        // private int PVT_VALUE = 10; //NOT OK, fields cannot be protected or private
        int VALUE = 10; //OK, VALUE is implicitly public
    }

    /* Private methods do not really fit conceptually in an interface but the need for private methods was felt after default
       methods were introduced in Java 8. If a method gets too big or if there multiple methods with a lot of common code,
       there was no way to refactor them into smaller methods without exposing all of them to the world because
       everything in an interface had to be public. Java 9 fixes this problem by allowing private methods in
       an interface. */
    public interface Movable2 {
        private void pvtMethod() { } //OK, non-abstract methods can be private (but not protected)
    }

    /* An interface may contain abstract methods, they contain just the declaration and no body. It is the same thing like
       in abstract class but here except that the keyword abstract is optional. */
    public interface Movable3 {
        void move3(int x); //implicitly abstract
        abstract void move4(int x); //explicitly abstract
    }

    /* An interface may contain default methods, they are a way for an interface to include a default implementation
       for a method. They are defined using the keyword default. A method cannot be default as well as
       abstract at the same time. Default methods are always public even if you do not declare them as such explicitly.
       You cannot mark them private or protected. */
    public interface Movable4 {
        default void move(int x) {
            System.out.println("Dummy implementation. Moving by " + x + " points");
        }
        /* default abstract void move5(int x) {
            System.out.println("Dummy implementation. Moving by " + x + " points");
        } */ // Invalid
        /* private default void move6(int x){
            System.out.println("Dummy implementation. Moving by "+x+" points");
        } */ // Invalid
        /* protected default void move6(int x){
            System.out.println("Dummy implementation. Moving by "+x+" points");
        } */  // Invalid
    }

    /* An interface may contain static methods, they belong to the interface itself and not to the object implementing
       that interface. They are defined using the keyword static, they can be marked public or private but not protected.
       If no access modifier is specified, they are implicitly public. Static methods cannot be marked default either. */
    public interface Movable5 {
        static void sayHello() {
            System.out.println("Hello!");
        }

        private static void sayHelloPrivate() {
            System.out.println("Hello!");
        }

        /* protected static void sayHelloProtected() {
            System.out.println("Hello!");
        } */ // Invalid
    }

    /* An interface may contain private methods, they (static as well as no-static) have been allowed since Java 9 and,
      as explained above, they are helpful when a default static methods gets too big and needs to be refactored
      into smaller internal methods without exposing the internal methods to the outside world. */
    public interface Movable6 {
        private void moveInternal() {
            System.out.println("in moveInternal");
        }

        private static void moveInternal2() {
            System.out.println("in moveInternal2");
        }
    }

    /* What if you want to disclose members of an interface only to the members of the same package? Well, make the interface
       "default". As discussed earlier, default access allows something to be visible only to members of the same package.
       If the interface itself is not visible outside the package, its members certainly won't be. */
    interface MovableNoPublic {

    }

    /* All variables defined in an interface are implicitly public, static, and final.
       Remember that instance variables are meant to store state, which means they are really a part of implementation.
       Therefore, instance variables have no place in an interface. */
    public abstract interface MovableConstants {
        int UNIT1 = 1;
        static int UNIT2 = 1;
        static final int UNIT3 = 1;
        public static final int UNIT4 = 1;
    }

    /* You may encounter an interface that does not contain anything at all. Such interfaces are called "marker interfaces".
       The purpose of a marker interface is to tag a class with an extra piece of information about that class itself.
       A well known marker interface in Java is java.io.Serializable. It signifies to the JVM that objects
       of classes implementing this interface can be serialized and deserialized.*/
    interface SpecialThing{
    }

    public static void main(String[] args) {}
}
