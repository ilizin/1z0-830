package me.ilizin.certification;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static javax.swing.DropMode.ON;

public class SwitchTutorial {

    public void part1() {
        /*
        * A switch statement allows you to select which code blocks should be executed based on a variable value.
        * A switch statement can be written in the old way and the new way (since Java 14).
        * The old way uses a colon (:) to separate the case label from the case block, while the new way uses an arrow (->).
        *
        * Depending on the selector expression value, the control will enter the code block associated
        * with that particular switch label and keep on executing statements until it finds a break statement.
        * If the value doesn't match with any of the case labels, the control enters the default block.
        * If there is no default block, the control does not enter the switch block.
        * */
        int switchExpression = 2;
        // The expression within () is named selector expression
        switch (switchExpression) {  // The switch block starts here
            case 0:
                System.out.println("The switchExpression is 0");
                break;
            case 1: {
                System.out.println("The switchExpression is 1");
                break;
            }//enclosing code within { } is optional
            //associating multiple case labels with the same case block
            case 2, 3:
                System.out.println("The switchExpression might be 2 or 3");
                break;

            //associating multiple case labels with the same case block
            case 4:
            case 5: // This is named case label
                System.out.println("The switchExpression might be 4 or 5");
                break;
            default: // This is named default label, both case labels and default labels are named switch labels
                // There can be at most one default block in a switch statement.
                // The default block is executed if the
                // value of the switch expression does not match with any of the case labels.
                // The code associated with a switch label is called switch block
                System.out.println("The switchExpression is different from 0, 1, 2, 3, 4 or 5");
                break;
        } // The switch block ends here

        /* Case labels must consist of one or more compile time constants that are assignable to the
           type of the selector expression.
         */

        String switchExpressionStr = "hello";
        switch(switchExpressionStr) {
            // Valid because "1", "2" and "1" + "2" are compile time constants assignable to String.
            case "1", "2" : System.out.println("one or two");
            case "1" + "2" : System.out.println("one plus two");
            //will not compile because "abc".toUpperCase() is not a compile time constant
            // case "abc".toUpperCase() : System.out.println("ABC");
        }

        byte b = 10;
        // A switch statement doesn't necessarily have to have a case label or the default label.
        switch (b) {
            // It won't compile because 10000 is too large to fit into a byte
            // case 10000 : System.out.println("10000");
        }

        // It is common to use a single variable as the selector expression but you can use any expression inside the switch.
        byte b2 = 10;
        switch (b2 + 1) { // type of the selector expression here is now int due to numeric promotion
            case 1000 : // 1000 can fit into an int
                System.out.println("1000");
        }

        // Java does not impose any particular order for the case and the default block,
        // except in special situations involving null and pattern matching.
        // It's common to have the default block at the end of a switch block, but you can have it
        // at the beginning also. Case and default blocks order in combination
        // with the use of the break statement determines the case block that get executed.

        // It prints:
        // default
        // 20
        b2 = 10;
        switch (b2) {
            default : System.out.println("default");
            case 20 : System.out.println("20");
        }

        // the break statement is optional.
        // This is called "fall through" behavior
        // The control entered at the block labelled case 0, and executed all the other switch blocks
        int c = 0;
        switch (c) {  //switch block starts here
            case 0 : System.out.println("0");
            case 1 : System.out.println("1");
            case 2 : System.out.println("2");
            default : System.out.println("default");
        }

        // The code blocks associated with the switch labels are called switch blocks.
        // In a switch statement written using the old syntax
        // the switch block might be empty, it could contain zero or more "expression statements", it could contain zero or more code blocks,
        // or it may throw an exception.
        int i = 0;
        switch(i) {
            case 0 : i++; //single expression statement
            case 1 : i++;i++; //multiple expression statements
            case 2 : { int w = 0; w++; } // single code block
            case 3 :  { int w = 0; w++; }  { int w = 0; w++; } // multiple code blocks
            case 4: //empty
            case 5: throw new RuntimeException("Invalid value for i");
        }

    }

    public void part2() {
        /* Prior to Java 21, the switch selector expression was restricted to only a few types: byte, short, char,
           int, their wrapper types, String, and enums
           In Java 21, the following types are allowed:
           1. A limited set of integral types (byte, char, short, int), and their wrapper classes.
           2. The enum type
           3. String expressions since Java 7
           4. Since to Java 21, null as a case constant is allowed.
              Prior to Java 21 if the selector expression returned null, a NullPointerException would be thrown.
           5. With Java 21, the selector expression is allowed to  return any reference type (including all wrapper types).
           */
        String str = null;
        switch (str) {
            case null -> System.out.println("str is null");
            default -> System.out.println("str is not null");
            //the above two lines can also be combined into one line:
            //case null, default -> System.out.println("null or default");
        }

        switch (str) {
            // default can be only combined with case null
            case null, default -> System.out.println("str is null or default");
        }

        /* To maintain backward compatibility, a null value does not match the default label.
        *  Without the case null block, a switch statement will cause a NullPointerException to be thrown if the selector
        * expression returns null.
        * */
        try {
            switch (str) {
                // default can be only combined with case null
                default -> System.out.println("str null or default");
            }
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException was thrown");
        }
    }

    public void part3 () {
        /* There is a new syntax available since Java 14 , Here are a few points
         1. Here are a few points
         2. Every switch label is associated with either a single expression statement or single a code block
         3. The switch statement breaks automatically after
            the expression or the block executes. It is valid to write a break statement in the code block but it is redundant
         4. The new syntax has only one way to allow multiple values to be associated with a case block.
         5. Cannot mix old and new syntax in the same switch statement.
         */

        int i = 0;
        switch(i) {
            case 0 -> System.out.println("0");
            //break; //invalid only single expression statement or single a code block are allowed
            case 1 -> {
                System.out.println("1");
                break; //valid, but redundant
            } //enclosing code within { } is optional
            case 2, 3 -> System.out.println("2 or 3");
            //cannot do this
            //case 4 -> case 5 -> System.out.println("Four or five arguments");//invalid
        }
    }

    public void part4 () {
        // Enums are used very often in a switch statement.

        DayOfWeek dow = LocalDate.now().getDayOfWeek();

         switch(dow) {
             case MONDAY:
                 System.out.println("MONDAY");
                 break;

             // Prior to Java 21, the case statements could only use constant without the enum name
             // To following will cause a compilation error.
             case DayOfWeek.TUESDAY:
                 System.out.println("TUESDAY");
                 break;
             default:
                 System.out.println("ANOTHER DAY");
         }
        dow = DayOfWeek.THURSDAY; // It prints SAT/SUN
        switch(dow){
            case MONDAY, TUESDAY -> System.out.println("MON/TUE");
            case WEDNESDAY, FRIDAY -> System.out.println("WED - FRI");
            default -> System.out.println("SAT/SUN");
        }

        dow = DayOfWeek.THURSDAY; // It prints SAT/SUN
        switch(dow){
            // TUESDAY is just a label applied to the print statement.
            case MONDAY: TUESDAY: System.out.println("MON/TUE"); break;
            case WEDNESDAY: FRIDAY: System.out.println("WED - FRI");
            default : System.out.println("SAT/SUN");
        }
    }

    public void part5 () {
        
    }
}