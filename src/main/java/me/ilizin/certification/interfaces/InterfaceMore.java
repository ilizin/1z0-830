package me.ilizin.certification.interfaces;

public class InterfaceMore {

    /* Any interface that has exactly one abstract method is a functional interface. It may have other private,
       static, or default methods, but it must have one and only one abstract method. This abstract method is also
       called the "functional method" of that functional interface. */
    @FunctionalInterface
    interface Mover1 {
        void move(int x, int y);
    }
    @FunctionalInterface
    interface Mover2 {
        void move(int x, int y);
        default void no_op(){ System.out.println("do nothing"); }
    }

    /* Valid interface but not a valid functional interface because it has two abstract methods */
    // @FunctionalInterface // Fail to compile if uncomment
    interface Mover3 {
        void move(int x, int y);
        void move3D(int x, int y, int z);
    }

    /* Valid interface but not a valid functional interface because it has no abstract method */
    // @FunctionalInterface // Fail to compile if uncomment
    interface Mover4 {
        default void move(int x, int y){
            System.out.println("moving "+x+" "+y);
        }
    }

    /* Valid interface but not a valid functional interface NewMover has two abstract methods including the one that
       it inherits from Mover2 */
    // @FunctionalInterface // Fail to compile if uncomment
    interface NewMover extends Mover2 {
        void m2(String str);
    }

    /* Abstract classes and interfaces are abstract. Objects of their kind do not exist, which is why they are called
       abstract in the first place. Therefore, they cannot be instantiated. The below  code seems to be instantiating
       Animal even though Animal is abstract! */
    static abstract class Animal {
        public static void main(String[] args) {
            Animal a = new Animal() {  };
        }
    }
    /* The following is an example that seems to instantiate an interface. You should observe is the presence of { }
       between new Animal() and this is not the syntax for instantiating a class. For instantiating Animal, you
       would have to write new Animal(); and if you try to do that you will get a compilation error.
       In fact, the code uses the syntax for declaring as well as instantiating a concrete inner class that extends
       Animal without giving this class a name. Since no name is given to this class, it is called an anonymous class.
       When the compiler sees this code, it actually creates a class, gives this class a weird looking name, and
       generates a separate a class file for this class. Since Animal class doesn't have any abstract method, this
       anonymous class doesn't need to implement any method. */
    interface Dummy {
    }
    public class TestClass {
        public static void main(String[] args){
            Dummy d = new Dummy(){ };
        }
    }

    public static void main(String[] args) {
    }
}
