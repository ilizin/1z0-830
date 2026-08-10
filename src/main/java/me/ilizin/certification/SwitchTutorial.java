package me.ilizin.certification;

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

        // Java does not impose any particular order for the case statements and the default block
        // (except in special situations involving null and pattern matching, which I will talk about later).
        // Thus, although it is customary to have the default block at the end of a switch block, you can have it even
        // at the beginning. Similarly, Java does not care about the ordering of the case labels.

        b2 = 20;
        switch (b2) {
            default : System.out.println("default");
            case 20 : System.out.println("20");
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
}