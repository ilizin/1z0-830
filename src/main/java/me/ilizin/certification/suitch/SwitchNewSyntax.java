package me.ilizin.certification.suitch;

public class SwitchNewSyntax {

    /* There is a new syntax available since Java 14 , Here are a few points
         1. Every switch label is associated with either a single expression statement or single a code block.
         2. The switch statement breaks automatically after the expression or the block executes.
            It is valid to write a break statement in the code block, but it is redundant.
         3. The new syntax has only one way to allow multiple values to be associated with a case block:
              case 2, 3 ->
         4. Cannot mix old and new syntax in the same switch statement. */
    public static void main(String[] args) {

        System.out.println();
        int selector = 0;
        switch(selector) {
            case 0 -> System.out.println("The value is 0");
            // break; // invalid only single expression statement or single a code block are allowed
            case 1 -> {
                System.out.println("The value is 1");
                break; //valid, but redundant
            }
            // The unique way to allow multiple values to be associated with a case block
            case 2, 3 -> System.out.println("The value is 2 or 3");
            //case 4 -> case 5 -> System.out.println("Four or five arguments"); // Invalid
        }
    }
}
