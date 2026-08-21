package me.ilizin.certification.array;

import java.util.Arrays;

public class CompareAndMismatch {

    public static void main(String[] args) {
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
    }
}
