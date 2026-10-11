package me.ilizin.certification.strings;

public class StringInterning {

    /* Since strings are objects and since all objects in Java are always stored in the heap space, all strings are stored
       in the heap space. However, Java keeps strings whose values can be determined at compile time in a special area of
       the heap space, called "string pool". Java keeps the strings created at run time, such as the ones created using the
       new keyword or using String manipulation methods, in the regular heap space.
       The purpose of the string pool is to maintain a set of unique strings. Any time you create a new string using
       compile time constant expressions (which basically means hardcoded string literals or strings created using concatenation
        of hardcoded strings or final String variables), Java checks whether the same string already exists in the string pool.
        If it does, Java returns a reference to the same String object and if it does not, Java creates a new String object
        in the string pool and returns its reference.*/
    public static void main(String[] args) {

        System.out.println();
        System.out.println("*** PART 1 ***");
        /* Comparing two different reference variables using the == operator.
           In the above code, str2 gets the reference to the same String object which was created earlier at line 1.
           However, str and str get references to two entirely different String objects. That is why str1 == str2
           returns true but str1 == str3 and str3 == str4 return false.*/
        String str1 = "hello";
        String str2 = "hello";
        System.out.println(str1 == str2); //prints true
        System.out.println(str1.equals(str2)); //prints true
        String str3 = new String("hello");
        String str4 = new String("hello");
        System.out.println(str1 == str3); //prints false
        System.out.println(str3 == str4); //prints false
        String str5 = " world";
        String str6 = "hello world";
        String str7 = str1+str5;//concatenation of non-final String variables
        String str8 = "hello "+"world";//concatenation of hardcoded Strings
        System.out.println(str6 == str7); //prints false
        System.out.println(str6 == str8); //prints true

        System.out.println();
        System.out.println("*** PART 2 ***");
        /* String pooling is Java's way of saving program memory by avoiding creation of multiple String objects containing
           the same value. It is possible to get a string from the string pool by using String's intern method. It is
           called "interning"of string objects. */
        String strr1 = "hello";//str1 points to a String created in the string pool
        String strr2 = new String("hello");//str2 points to a String created in the heap
        String strr3 = strr2.intern(); //gets an interned string referred to by str2
        System.out.println(strr1 == strr2); //prints false
        System.out.println(strr1 == strr3); //prints true
    }
}
