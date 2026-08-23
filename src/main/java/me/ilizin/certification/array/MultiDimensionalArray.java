package me.ilizin.certification.array;

import java.util.Arrays;

public class MultiDimensionalArray {

    public static void main(String[] args) {

        /* Java doesn't have a multidimensional arrays. Java has a class of an array of ints is [I.
           Now, what if you want to have an array of objects of this class. In other words, an array of "array of ints".
           You can declare it like this. Visually, the declaration looks like iaa is a two-dimensional matrix of ints.
           But in reality, iaa points to a single dimensional array, where each element of the array is an array of ints.
           In a two-dimensional array, the length of each row will always be the same. While in an array of arrays,
           there is no such restriction. Each row can refer to an array of any length. */
        int[][] iaa;
        int[] iaa4[];

        /* You never specify the length of the array in the type declaration. */
        //int[3] ia;
        //int[2][] iaa;

        System.out.println();
        /* iaa2 is created using an array creation expression. iaa2 refers to an array of length 2. Each element of this
           array refers to an array of ints of length 3. Each element of both the arrays of ints is initialized to 0. */
        int[][] iaa2 = new int[2][3];
        System.out.println(Arrays.toString(iaa2));

        System.out.println();
        /* iaa3 is created using an array creation expression. iaa3 refers to an array of length 3. Observe that the
           type of each element is "array of ints", which means iaa3 is an array of objects (and not of primitives).
           Since every element of array of objects is automatically initialized to null, each element of the array
           pointed to by iaa3 is initialized to null
           The size of the second dimension is not needed because the arrays pointed to by those references can be
           created later and can be of different lengths. */
        int[][] iaa3 = new int[3][];
        iaa3[0] = new int[2]; // iaa3[0] points to an array of ints of length 2
        iaa3[1] = new int[3]; // iaa3[1] points to an array of ints of length 3
        // int[][] iaa4 = new int[][4]; // Invalid
        System.out.println(Arrays.toString(iaa3));

        System.out.println();
        /* This statement uses an array creation expression coupled with array initializer. */
        int[][] iaa5 = new int[][]{ new int[]{ 1, 2 } };
        int[][] iaa6 = { { 1, 2 } }; // This is the same as above but with array initializer.
        System.out.println(Arrays.toString(iaa5));
        System.out.println(Arrays.toString(iaa6));

        /* Every array is an object, this means, you can assign any array object to variable of type Object */
        int[] intArray = new int[]{ 0, 1, 2 };
        Object obj = intArray;
        System.out.println(Arrays.toString(intArray));
        /* This will not compile because elements of the array pointed to by intArray are not Objects. They are ints. */
        // Object[] oa = intArray;
        Integer[] intArray2 = new Integer[]{ 0, 1, 2 };
        Object[] oa = intArray2;
        Object[] oa2 = new int[2][3]; // This is valid.
        System.out.println(Arrays.toString(intArray2));
        // Object[][] oaa = new int[2][3]; // This will not compile.
    }
}
