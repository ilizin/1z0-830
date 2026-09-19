package me.ilizin.certification.strings;

import java.io.File;
import java.io.IOException;

public class CreatingStrings {

    /* In Java, a "string" is an object of class java.lang.String. It represents a series of characters.
       String is a final class, which means it cannot be extended. It extends Object and implements java.lang.CharSequence.
       it is such a fundamental object that Java provides special treatment to strings in terms of how they are created,
       how they are managed, and how they are used. */
    public static void main(String[] args) throws IOException {
        /* The String class has several constructors but for the purpose of the exam, you only need to be aware of the
           following: */
        System.out.println();
        System.out.println("*** PART 1 ***");
        String word1 = new String(); // The no-args constructor creates an empty String.
        System.out.println(word1);
        // Create a new String by copying the sequence of characters currently contained in the passed String or StringBuilder objects.
        String word2 = new String("Hello");
        System.out.println(word2);
        String word3 = new String(new StringBuilder("Hello"));
        System.out.println(word3);
        // Creates a new String by decoding the specified array of bytes using the platform's default charset.
        String word4 = new String(new byte[]{72, 101, 108, 108, 111});
        System.out.println(word4);
        // Creates a new String so that it represents the sequence of characters currently contained in the character array argument.
        String word5 = new String(new char[]{'H', 'e', 'l', 'l', 'o'});
        System.out.println(word5);

        /* A string is composed of an array of chars. But that does not mean a string is the same as a char array.
           Therefore, you cannot apply the array indexing operator on a string. */
        String str = new String("Hello");
        //char c = str[0]; // Invalid
    }
}
