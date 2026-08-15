package me.ilizin.certification.lambdaexpression;

import java.util.ArrayList;
import java.util.List;

public class MethodReference {

    /* As per JLS, a method reference expression is used to refer to the invocation of a method without
       actually performing the invocation, they provide even more opportunity to remove boilerplate code that
       the compiler can easily infer from the context. */

    public static void main(String[] args) {

    /*  printing all the elements of a list: It uses a lambda expression to create the accept method of the
        Consumer interface. */
        List<String> list = new ArrayList<>();
        list.forEach( obj -> System.out.println(obj) );

    /*  We are essentially telling the compiler to use the println method of System.out to generate the code for
        the Consumer object expected by the forEach method. The compiler knows that the generated Consumer's accept
        method will be passed an element of the list as an argument. Since println also expects one argument,
        the compiler can easily put two and two together and generate the code that passes the element received by
        the accept method down to the println method.

        You tell the compiler which method you are interested in using the double colon syntax and if the method
        expects any arguments, the compiler will automatically pick up the values that are available in the context
        and pass them as arguments to the method that you are interested in.

        the compiler must be able to figure out exactly which method you want to call unambiguously and that it
        must be possible to call it with the information available in the context, which means the compiler must be
        able to identity the object on which the method has to be invoked (aka target reference) and the compiler
        must be able to identity the values that can be passed as arguments to the method.*/
        list.forEach(System.out::println);
    }

}
