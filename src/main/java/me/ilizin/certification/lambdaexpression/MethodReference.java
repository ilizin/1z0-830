package me.ilizin.certification.lambdaexpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodReference {

    /* As per JLS, a method reference expression is used to refer to the invocation of a method without
       actually performing the invocation, they remove boilerplate code that the compiler can easily infer from
       the context. */
    public static void main(String[] args) {

        System.out.println();
        /* Printing all the elements of a list: It uses a lambda expression to create the accept method of the
           Consumer interface.

           We are essentially telling the compiler to use the println method of System.out to generate the code for
           the Consumer object expected by the forEach method. The compiler knows that the generated Consumer's accept
           method will be passed an element of the list as an argument. Since println also expects one argument,
           the compiler can easily put two and two together and generate the code that passes the element received by
           the accept method down to the println method.

           The compiler must be able to figure out exactly which method you want to call unambiguously and that it
           must be possible to call it with the information available in the context, which means the compiler must be
           able to identity the object on which the method has to be invoked (aka target reference) and the compiler
           must be able to identity the values that can be passed as arguments to the method.
           The method name is written on the right side of :: in a method reference expression.
           For example, in System.out::println, println is the method name. String::new would mean that you want to create an
           instance of String

           Identifying the target reference - the reference on which the method is to be invoked, is indicated on the
           left side of ::. It could be the name of a class (in case of a static method) Math::random
           A variable that is already in scope (in case of an instance method) System.out::println, the target reference
           is identified by a static variable named out of class System. It refers to an instance of PrintStream. PrintStream
           has an instance method named println. Thus, the out variable can be used to invoke the println method.*/
        List<String> list = new ArrayList<>();
        list.add("hola"); list.add("hello"); list.add("ciao");
        list.forEach( obj -> System.out.println(obj) );
        list.forEach(System.out::println);

        System.out.println();
        /* Identifying the target reference - A reference that is available in the context in which the method reference
           expression is written. The compiler can get hold of the target reference from the context as well. Here is an example:
           reverse is an instance method of StringBuilder, but there is no clearly identifiable StringBuilder reference variable
           present in the below code! the compiler is smart to figure out that this method reference expression is present
           within the context of List's forEach method, which takes a Consumer<StringBuilder> instance as an argument.
           The reverse method is supposed to be executed inside that Consumer<StringBuilder>'s accept method. The accept
           method will be passed a StringBuilder reference as an argument.
           Thus, the context in which the method reference is being used does have a StringBuilder reference and it is this
           StringBuilder reference on which the reverse method will be invoked. */
        List<StringBuilder> sbl = List.of(new StringBuilder("123"));
        System.out.println(sbl);
        sbl.forEach(StringBuilder::reverse);
        System.out.println(sbl);

        System.out.println();
        /* Identifying the arguments - The values to be passed as arguments is the most confusing part of a method reference
           because they are never specified explicitly or implicitly anywhere in a method reference expression. The compiler
           takes all of the available values in the context and passes them to the method as arguments. But for this to happen,
           the number of values available in the context and their types must match the number of arguments and their types
           expected by the method.*/
        Map<String, String> map = new HashMap<>();
        /* Invalid forEach method expects a BiConsumer<String, String> and BiConsumer<String, String>'s accept method
           gets two arguments */
        // map.forEach(System.out::println);
        /* Observe that Map<Person, Person>'s forEach expects a BiConsumer<Person, Person> and its accept method will
           be invoked with two Person references as arguments. Thus, there are two Person references in the context
           of this method reference expression. Now, since isSpouse is an instance method of Person and it expects
           one Person reference as an argument, the compiler needs two Person objects to realize the method reference
           expression Person::isSpouse one for invoking the isSpouse method on, and one for passing as an argument to
           the isSpouse method. Thus, the compiler will use the first Person available in the context as the target
           object and the second Personas an argument to the method. */
        Map<Person, Person> map2 = Map.of(new Person("Amy", "Adams"), new Person("Sarah", "Adams"));
        map2.forEach(Person::isSpouse);
        /* The isSpouse method is now static and it takes two
        Person arguments. As explained above, the context does get two Person references (as arguments to BiConsumer's
        accept method). Since isSpouse is static, the compiler doesn't need a Person reference to invoke the isSpouse
        method. It can invoke the method directly on the Person class and can pass the two-Person references as arguments, */
        Map<Person2, Person2> map3 = Map.of(new Person2("Amy", "Adams"), new Person2("Sarah", "Adams"));
        map3.forEach(Person2::isSpouse);
    }

    record Person(String fname, String lname){
        boolean isSpouse(Person anotherPerson){
            System.out.println("Checking "+fname+" "+lname+":"+anotherPerson.lname);
            return lname.equals(anotherPerson.lname);
        }
    }

    record Person2(String fname, String lname){
        public static boolean isSpouse(Person2 first, Person2 second){
            System.out.println("Checking "+first.fname+" "+first.lname+":"+second.lname);
            return first.lname.equals(second.lname);
        }
    }
}
