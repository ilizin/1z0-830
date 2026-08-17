package me.ilizin.certification.array;

public class OneDimensionalArray {

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
    }
}