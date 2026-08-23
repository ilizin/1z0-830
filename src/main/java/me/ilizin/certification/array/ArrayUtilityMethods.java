package me.ilizin.certification.array;

import java.util.Arrays;

public class ArrayUtilityMethods {

    public static void main(String[] args) {

        System.out.println();
        /* Finds and returns the index of the first mismatch between two int arrays, otherwise return -1 if no mismatch
           is found. The index will be in the range of 0 (inclusive) up to the length (inclusive) of the smaller array.
           If the two arrays share a common prefix then the returned index is the length of the common prefix.
           If one array is a proper prefix of the other than the returned index is the length of the smaller array.
           Otherwise, there is no mismatch. */
        int[] ia1 = { 0, 1, 2, 6 };
        int[] ia2 = { 0, 1, 5 };
        System.out.println(Arrays.mismatch(ia1, ia2)); // prints 2
        ia1 = new int[]{ 0, 1, 5, 6 };
        ia2 = new int[]{ 0, 1, 5 };
        System.out.println(Arrays.mismatch(ia1, ia2)); // prints 3
        ia1 = new int[0];
        ia2 = new int[0];
        System.out.println(Arrays.mismatch(ia1, ia2)); // prints -1
        ia1 = new int[]{ 0, 1, 5, 6 };
        ia2 = new int[]{ 1, 1, 5, 6 };
        System.out.println(Arrays.mismatch(ia1, ia2)); // prints 0
        ia1 = new int[]{ 0, 1, 5, 6 };
        ia2 = new int[]{ 0, 1, 5, 6 };
        System.out.println(Arrays.mismatch(ia1, ia2)); // prints -1
        ia1 = new int[]{ 0, 1, 5, 6 };
        ia2 = new int[]{ 0, 1, 5, 7 };
        System.out.println(Arrays.mismatch(ia1, ia2)); // prints 3

        System.out.println();
        /* Compares two int arrays lexicographically. If the two arrays share a common prefix then the lexicographic
           comparison is the result of comparing two elements, as if by Integer.compare(int, int), at an index within
           the respective arrays that is the prefix length. Otherwise, one array is a proper prefix of the other and,
           lexicographic comparison is the result of comparing the two array lengths.
           A null array reference is considered lexicographically less than a non-null array reference. Two null array
           references are considered equal. It returns 0 if the first and second array are equal and contain the same
           elements in the same order; a value less than 0 if the first array is lexicographically less than the
           second array; and a value greater than 0 if the first array is lexicographically greater than the second array. */
        ia1 = new int[]{ 0, 1, 2, 6};
        ia2 = new int[]{ 0, 1, 5};
        System.out.println(Arrays.compare(ia1, ia2)); //prints -1
        ia1 = new int[]{ 0, 1, 5, 6};
        ia2 = new int[]{ 0, 1, 5};
        System.out.println(Arrays.compare(ia1, ia2)); //prints 1
        ia1 = new int[]{ 0, 1, 5, 6};
        ia2 = new int[]{ 0, 1, 5, 6};
        System.out.println(Arrays.compare(ia1, ia2)); //prints 0
        ia1 = null;
        ia2 = new int[]{ 0, 1, 5, 6};
        System.out.println(Arrays.compare(ia1, ia2)); //prints -1
        ia1 = new int[]{ 0, 1, 5, 6};
        ia2 = null;
        System.out.println(Arrays.compare(ia1, ia2)); //prints 1
        ia1 = null;
        ia2 = null;
        System.out.println(Arrays.compare(ia1, ia2)); //prints 0

        System.out.println();
        /* Java standard library does include a utility class named Arrays in package java.util, it contains a large number
        of static utility methods for manipulating any given array object. Arrays.toString method prints the contents of an array.
        Besides those, it also has equals and deepEquals methods that let you compare two arrays based on their content. */
        Integer[] test1 = {2, 3, 4, 5};
        Integer[] test2 = {2, 3, 4, 5};
        System.out.println(Arrays.equals(test1, test2)); // true
        test2[0] = 0;
        System.out.println(Arrays.equals(test1, test2)); // false
        test1 = null; test2 = null;
        System.out.println(Arrays.equals(test1, test2)); // true
        Integer[][] test11 = { { 1, 2, 3 }, { 3, 4, 5 }, { 6, 7, 8 } };
        Integer[][] test12 = { { 1, 2, 3 }, { 3, 4, 5 }, { 6, 7, 8 } };
        System.out.println(Arrays.equals(test11, test12)); // false
        System.out.println(Arrays.deepEquals(test11, test12)); // true
    }
}
