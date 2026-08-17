package me.ilizin.certification.array;

public class OneDimensionalArray {

    private static class MyClass {}

    public static void main(String[] args) {

        /* An array is an object that holds a fixed number of values of a given type. An array of a given type cannot
           hold anything else except values of that type. */
        int i; // i is an int
        int[] ia1, ia2;  // ia1 and ia2 are one dimensional arrays of ints
        int[][] iaa;  // iaa is a two dimensional array of ints

        // An array declaration can never include the size of the array. Thus, the following are declarations will not compile:
        // int[2] Invalid;
        // int[3][] Invalid;
        // int[][4] Invalid;

        /* Java allows you to apply square brackets to the variable name instead of type name as well.*/
        int i2, ia[];  // i2 is an int but ia is a one dimensional array of int values
        int[] ia3, iaa2[];  // ia3 is a one dimensional array of ints but iaa2 is a two dimensional array of ints and so on

        // Arrays of objects are declared the same way. For example,
        Object[] obja, objaa[];  // obja is a one dimensional array of Objects but objaa is a two dimensional array of Objects
        String[] strA; // strA is a one dimensional array of Strings

        /* The statements shown above only declare array variables, You use the new keyword to create an array object.
           The parts on the right-hand side of = sign in the above statements are called "array creation expressions".
           Every element of the array is also initialized to its default value automatically by the JVM. The default
           values of array elements are very straightforward - references are initialized to null, numeric primitives to 0,
           and booleans to false. All the elements of an array are initialized to the same value. */
        int[] ia4 = new int[10]; // An array of ints of size 10
        boolean[] ba = new boolean[3]; // An array of booleans of size 3
        String[] stra = new String[5]; // An array of Strings of size 5
        MyClass[] myca = new MyClass[5]; // An array of MyClass of size 5
        // int[] invalid = new int[]; //missing size. will not compile.

        /* In Java, arrays are objects of specific classes. It not an instance of Object class but since Object is the root of every
           class in Java, an array object is an Object and all methods of the Object class can be invoked on an array.
           The output shows that ia6, which is declared to be of type int[], is not an instance of int but of a class named [I
           and so on.
           Actually, Java cooks up the name of the class of an array by looking at the number of dimensions and the type of the
           elements. For each dimension, there is one opening square bracket. This is followed by a letter for the class
           of the elements and, if the array is not of a primitive, the name of the class followed by a semi-colon.
           */
        int[] ia6 = new int[10];
        boolean[] ba6 = new boolean[3];
        String[] stra6 = new String[5];
        MyClass[] ta = new MyClass[5];
        System.out.println(ia6.getClass().getName() + " , " + ia6.getClass().getSuperclass().getName()); // [I , java.lang.Object
        System.out.println(ba6.getClass().getName() + " , " + ba6.getClass().getSuperclass().getName()); // [Z , java.lang.Object
        System.out.println(stra6.getClass().getName() + " , " + stra6.getClass().getSuperclass().getName()); // [Ljava.lang.String; , java.lang.Object
        System.out.println(ta.getClass().getName() + " , " + ta.getClass().getSuperclass().getName()); // [LTestClass; , java.lang.Object

        /* It is possible to create array objects without using the new keyword. The parts on the right-hand side of the =
           sign in the above statements are called "array initializers". An array initializer is a shortcut that allows
           you to create as well as initialize each element of the array with the values that you want */
        int[] ia9 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }; // An array of ints of size 10
        String[] str = {"a", "b", "c", "d", "e"}; // An array of Strings of size 5

        // It is prohibited to specify the size if you are specifying individual elements.
        // int[] ia = new int[2]{ 1, 2 }; // Will not compile.
    }
}