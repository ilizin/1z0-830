package me.ilizin.certification.strings;

import java.io.File;
import java.io.IOException;

public class CreatingStrings {

    private static class Account{
        String acctNo;
        Account(String  acctNo){
            this.acctNo = acctNo;
        }

        public String toString() {
            return "Account[" + acctNo + "]";
        }
    }

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

        System.out.println();
        System.out.println("*** PART 2 ***");
        /* Strings are used so commonly in programs that creating strings using the constructor becomes too tedious.
           So, Java allows you to create a string by just specifying the contents of the string within double quotes.
           They are called single line string literals because you have to specify the complete contents of the string
           in a single line of code. You cannot split the contents across multiple lines. */
        String word6 = "Double quote \", Tab \t, New line,\nEnd";
        System.out.println(str);
        /* String word7 = "Double quote \", Tab \t, New line, // Invalid
        End"; */

        System.out.println();
        System.out.println("*** PART 3 ***");
        /* The second common way of creating strings is by using the concatenation operator: +
           The + operator is overloaded in such a way that if either one of its two operands is a string, it converts the
           other operand to a string and produces a new string by joining the two. There is no restriction on the type of
           operands as long as one of them is a string. */
        String word8 = "hello ";
        String word9 = word8 + " world";
        System.out.println(word9);
        /* 1. If the non-string operand is a reference variable, the toString() method is invoked on that reference to get a
           string representation of that object.
           2. If the non-string operand is a primitive variable or a primitive literal value, a wrapper object of the same
           type is created using the primitive value and then a string representation is obtained by invoking toString()
           on the wrapper object.
           3. If the one of the operands is a null literal or a null reference variable, the string "null" is used instead
           of invoking any method on it. */
        String s1 = "hello ";
        String s11 = s1 + 1; //produces "hello 1"
        String s12 = 1 + " hello"; //produces "1 hello"
        String s2 = "" + true; //produces "true";
        double d = 0.0;
        String s3 =  "-" + d +"-"; //produces "-0.0-"
        Object o = null;
        String s4 = "hello " + o; //produces "hello null". No NullPointerException here.
        /* Just like a mathematical expression involving the + operator, string concatenation is also evaluated from left to right.
           Therefore, while evaluating the expression "1"+2+3, "1"+2 is evaluated first to produce "12" and then "12"+3 is evaluated
           to produce "123". On the other hand, the expression 1 + 2 + "3" produces "33". */
        String word10 = "1" + 2 + 3;
        System.out.println(word10);
        String word11 = 1 + 2 + "3";
        System.out.println(word11);
        /* At least one of its operands must be a String. */
        //String x = true + 1; // Invalid
        Object obj = "string";
        /* Will not compile. Even though obj points to a String at runtime, as far as the compiler is concerned, obj is an
           Object and not a String. */
        // String y = obj + obj;

        System.out.println();
        System.out.println("*** PART 4 ***");
        /* The below code produces the following output with and without overriding toString:
           1. Printing account  - Account[A1234]
           2. Printing account  - Account@72bfaced
           Since the Object class has no idea about what a class represents, it just returns a generic string consisting
           of the name of the class of the object, the at-sign character '@', and the unsigned hexadecimal representation
           of the hash code of the object. */
        Account a = new Account("A1234");
        String s = "Printing account  - "+a;
        System.out.println(s);

    }
}
