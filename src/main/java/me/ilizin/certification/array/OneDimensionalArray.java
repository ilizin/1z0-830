package me.ilizin.certification.array;

import java.util.Arrays;

public class OneDimensionalArray {

    private static class MyClass {}

    /* Arrays are quite powerful as a data structure but they are somewhat primitive as a data type.
     Arrays have only one field and merely a couple of methods. But because of their simplicity, arrays are used as building
     blocks for other data types and data structures. For example, the String and ArrayList data type is built upon an array of chars */
    public static void main(String[] args) {

        /* An array is an object that holds a fixed number of values of a given type. An array of a given type cannot
           hold anything else except values of that type. An array declaration can never include the size of the array.
           Java allows you to apply square brackets to the variable name instead of type name as well. */
        int i; // i is an int
        int[] ia1, ia2;  // ia1 and ia2 are one dimensional arrays of ints
        int[][] iaa;  // iaa is a two-dimensional array of ints
        // int[2] Invalid;
        // int[3][] Invalid;
        // int[][4] Invalid;
        int i2, ia[];  // i2 is an int but ia is a one-dimensional array of int values
        int[] ia3, iaa2[];  // ia3 is a one dimensional array of ints but iaa2 is a two-dimensional array of ints

        /* Arrays of objects are declared the same way */
        Object[] obja, objaa[];  // obja is a one dimensional array of Objects but objaa is a two-dimensional array of Objects
        String[] strA; // strA is a one dimensional array of Strings

        System.out.println();
        /* The statements shown above only declare array variables, You use the new keyword to create an array object.
           The parts on the right-hand side of = sign in the below statements are called "array creation expressions".
           Every element of the array is also initialized to its default value automatically by the JVM. The default
           values of array elements are very straightforward - references are initialized to null, numeric primitives to 0,
           and booleans to false. All the elements of an array are initialized to the same value. */
        int[] ia4 = new int[10]; // An array of ints of size 10
        boolean[] ba = new boolean[3]; // An array of booleans of size 3
        String[] stra = new String[5]; // An array of Strings of size 5
        MyClass[] myca = new MyClass[5]; // An array of MyClass of size 5
        // int[] invalid = new int[]; //missing size. will not compile.
        System.out.println(Arrays.toString(ia4));
        System.out.println(Arrays.toString(ba));
        System.out.println(Arrays.toString(stra));

        System.out.println();
        /* In Java, arrays are objects of specific classes. It's not an instance of Object class but since Object is
           the root of every class in Java, an array object is an Object and all methods of the Object class can be
           invoked on an array.
           The output shows that ia6, which is declared to be of type int[], is not an instance of int but of a class named [I
           and so on. Actually, Java cooks up the name of the class of an array by looking at the number of dimensions
           and the type of the elements. For each dimension, there is one opening square bracket. This is followed by a
           letter for the class of the elements and, if the array is not of a primitive, the name of the class followed by a semi-colon. */
        int[] ia6 = new int[10];
        boolean[] ba6 = new boolean[3];
        String[] stra6 = new String[5];
        MyClass[] ta = new MyClass[5];
        System.out.println(ia6.getClass().getName() + " , " + ia6.getClass().getSuperclass().getName()); // [I , java.lang.Object
        System.out.println(ba6.getClass().getName() + " , " + ba6.getClass().getSuperclass().getName()); // [Z , java.lang.Object
        System.out.println(stra6.getClass().getName() + " , " + stra6.getClass().getSuperclass().getName()); // [Ljava.lang.String; , java.lang.Object
        System.out.println(ta.getClass().getName() + " , " + ta.getClass().getSuperclass().getName()); // [LTestClass; , java.lang.Object

        System.out.println();
        /* It is possible to create array objects without using the new keyword. The parts on the right-hand side of the =
           sign in the above statements are called "array initializers". An array initializer is a shortcut that allows
           you to create as well as initialize each element of the array with the values that you want */
        int[] ia9 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }; // An array of ints of size 10
        String[] str = {"a", "b", "c", "d", "e"}; // An array of Strings of size 5
        int[] iap = new int[]{ 1, 2 };
        iap = new int[]{ 1 };
        int iap2;
        // iap2 = {1, 2}; // Array initializer is not allowed here, it goes with the array declaration always.
        // It is prohibited to specify the size if you are specifying individual elements.
        // int[] ia = new int[2]{ 1, 2 }; // Will not compile.
        System.out.println(Arrays.toString(ia9));
        System.out.println(Arrays.toString(str));
        System.out.println(Arrays.toString(iap));

        System.out.println();
        /* Similarly, if you have an array variable pointing an array of 5 ints, the first element can be accessed
        using ia[0] and the last element using ia[4]. If you try to access any array beyond its range, JVM will throw
        an instance of ArrayIndexOutOfBoundsException. */
        int[] ia10 = new int[3];
        // i = ia10[-1]; // Will cause an ArrayIndexOutOfBoundsException to be thrown.
        // i = ia10[3]; // Will cause an ArrayIndexOutOfBoundsException to be thrown.
        System.out.println(ia10[0]);

        System.out.println();
        /* It is possible to have an array of length 0. There are no elements in this array. An array of length 0 is not
            the same as null. A good example of an array of length 0 is the args parameter of the main method. If you run
            a class with no argument, args will not be null but will refer to an array of Strings of length 0. */
        int[] ia11 = new int[0]; // ia11 points to an array of ints whose length is 0.
        System.out.println(ia11);

        System.out.println();
        /* All array classes have one field named length, which is of type int. This field is public and it stores
           the length of the array. This field is also final, which reflects the fact that you cannot change the length
           of an array after its creation. */
        System.out.println(ia11.length);

        System.out.println();
        /* All array classes also have a public method named clone. This method creates a copy of the array object.
           It merely creates a new array object of the same length and copies the contents of existing array into the new array.
           Which means, if the existing array contained primitive values, those values will be copied to the elements of the new array.
           If the existing array contained references to objects, those references will be copied to the elements of the new array.
           Thus, the elements of the new array will also point to the same objects. This is also known as "shallow copy". */
        int[] myIntArr1 = {1, 2, 3, 4, 5};
        int[] myIntArr2 = (int[]) myIntArr1.clone();
        String[] myIntArr3 = {"1", "2", "3", "4", "5"};
        String[] myIntArr4 = (String[]) myIntArr3.clone();
        System.out.println(Arrays.toString(myIntArr2));
        System.out.println(Arrays.toString(myIntArr4));
        System.out.println(myIntArr3[0] == myIntArr4[0]);
        System.out.println(myIntArr1[0] == myIntArr2[0]);

        System.out.println();
        /* Array classes inherit all the members of the Object class. This includes toString, equals, and hashCode methods. */
        System.out.println(myIntArr3.hashCode());
        System.out.println(myIntArr3.toString());

        System.out.println();
        /* Arrays are "covariant", Meaning, you can store a subclass object in an array that is declared to be of the type of
           its superclass, for example, if you have an array of type java.lang.Number, you can store java.lang.Integer or
           java.lang.Float objects.
           It also means that array types have a supertype-subtype relation between them if and only if such a relation
           exists between their element types. For example, Number[] is considered a "supertype" of Integer[] because
           Number is a supertype of Integer.*/
        Number[] na = { 1, 2, 3 };
        na[0] = 1.2f;
        System.out.println(na[0]);
        na = new Integer[10];
        na[0] = 1;
        System.out.println(na[0]);
        // na[0] = 1.2f; System.out.println(na[0]); // It fails a runtime

        System.out.println();
        /* The second is that arrays are "reified". Meaning, the type information of an array object is preserved in the
           array object itself and is available to the JVM at runtime. This enables the checking of arrays and its elements
           for type safety at runtime by the JVM. In other words, the JVM knows about the type of the actual array object and
           enforces type checking while storing elements in the array.
           For example, if you have an array of type X, the JVM will not let you set any element of that array to point
           to any object other than an X (or a subtype of X) */
        Integer[] ia12  = {1, 2, 3};
        Number[] na12 = ia12; // OK, assigning array of Integers to a variable of type Number[], because Integer[] is-a Number[]
        /* assigning a Double to an element of Number[] its ok Double is-a Number, but fails at runtime,
        the JVM will throw a java.lang.ArrayStoreException The JVM knows that
        this array is actually an array of Integers and since a Double is-not-an Integer, it will not allow this assignment */
        // na12[0] = 1.0;
    }
}