package me.ilizin.certification;

public class RecordTutorialTest {


    public class StudentOld {
        private int id;
        private String name;
        private String address;

        public StudentOld(int id, String name, String address){
            this.id = id;
            this.name = name;
            this.address = address;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getAddress() {
            return address;
        }
    }

    /*
    * Observe that the Student class is a set of fields, a constructor that initializes those fields,
    * and getter methods for those fields.
    * I want Student objects to be immutable, the following is how I would do it in Java 16:
    *  * A record definition has two parts -
     * record header and record body. Record header is where you specify the fields
     * and record body is where you may optionally specify constructors and methods of that record.
     * So, (int id, String name, String address) is the record header and { } is the record body.
     *
     * The parameters specified in a record header are called record components which
     * completely describe the state that a record is meant to keep and based on this state information,
     * the compiler derives and generates its "API" using a set of rules. The API of a record is nothing but a constructor,
     * accessor methods, the equals method, the toString method, and the hashCode method.
     *
     *
     * For each record component, a record class has a field with the same
     * name as the record component and the same type. This field,
     * which is declared implicitly, is known as a component field. For each record component, a record
     * class has a method with the same name as the record component and an empty formal parameter list. This method,
     * which is declared explicitly or implicitly, is known as an accessor method.
    * */

    /*
    * A record implicitly extends java.lang.Record class but it is not allowed to have an extends clause.
    * The reason is that if it were allowed to extend another class, it would inherit the instance fields of
    * that class and its state would depend on that class as well.
    *
    * */
    public record Student(int id, String name, String address) {
        // A record is not allowed to define any instance field explicitly.
        // It may have static fields though because static fields do not constitute the state of an object.
        // int a;
        static int b;
    };

    // A record is allowed to implement interfaces and may inherit default methods from that interface.
    public interface Person {
        public default void run() {};
        // void walk(); // Student3 fails to compile
    }

    // A record is implicitly final.
    // For the same reason, you cannot make a record abstract, sealed, or unsealed.
    // Nested records are implicitly static.
    public static final record Student3(int id, String name, String address) implements Person {

    };

    void part1() {
        /*
        * There is no difference in how you use a record because it's nothing more than an immutable class.
        * For example, here is how to instantiate a Student record and access its field:
        * */
        Student s  = new Student(1, "Adam Smith", "123 Main Street");
        String name = s.name(); //observe that it is not getName() but just name()


        /*

        * */
    }
}
