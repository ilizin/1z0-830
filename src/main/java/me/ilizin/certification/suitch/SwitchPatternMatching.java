package me.ilizin.certification.suitch;

public class SwitchPatternMatching {

    public static void main(String[] args) {
        /* If/else statement can benefit from pattern matching with instanceof.
        The pattern matching feature is extended to switch.
        The following code listing shows a decision making logic written using the
        switch statement written using new as well as old syntax: */


        /*
        The code tries to match the actual type of the object referred to by
        obj with the reference types given in the case labels. The block associated with the case label that matches
        with the type of the object is then executed. For each of the case labels, a pattern variable is also defined,
        which can be used within that case block. The switch statement with the old syntax requires
        a break statement for each case block. */

        /*
            Exhaustive - A regular switch statement need not be exhaustive but a switch statement that uses
            pattern matching is required to be exhaustive. A switch expression must always be exhaustive

        */

        Object object = 10;
        //using the new arrow syntax, it produces "Integer 10"
        switch (object) {

            case Integer i -> System.out.println("Integer " + i);
            case Double d -> {
                System.out.println("Double " + d);
                break; //allowed but redundant
            }
            default -> System.out.println("Object " + object);
        }

        //using the old syntax
        switch (object) {
            case Integer i : System.out.println("Integer " + i); break;
            case Double d : System.out.println("Double " + d); break;
            default : System.out.println("Object " + object);
        }

        /* The "fall through" behavior is not permitted when using pattern matching.
           So, a break statement is required in each case block in the old syntax.
               The reason for prohibiting fall through is clear from the following code:

           If obj points to an Integer and if the first case is allowed to fall through to the second one,
           there will be a problem because the second case block expects str to be initialized to point to a String */

        Object object2 = 2;
        switch (object2) {
            case Integer i : System.out.println(i);
                // case String str : System.out.println(str.length()); // won't compile
            default : System.out.println(object2);
        }

        /* However, it is possible to fall through to case null and default because no variables are expected
           to be available in these blocks. Therefore, the following is valid:*/

        /* The matching is done in the order of appearance of case labels.
           It is therefore, important to specify case labels in the order of increasing "dominance".
        * */
        switch(object2) {
            // A pattern variable is required with each case label, except default and null
            case Integer i : System.out.println(i); break; //fall through is NOT allowed here
            // String i is also valid
            case String str : System.out.print(str.length()); //fall through is allowed here
            case null : System.out.print("null"); //fall through is allowed here
            default : System.out.print(object2);
        }


        /* A case label dominates another case label if every value that matches another case label also matches
        this case label but not vice-versa. For example, case Object dominates case Number n, because every value
        that matches Number also matches Object. Thus, you cannot put case Object o before case Number n. The reason is simple.
        If you do that, then case Number n will become unreachable. default label can only be used at the end of the switch
        block because default dominates every other case label and any case label after default will be unreachable. */

        Object object3 = new String();
        switch (object3) {

            case String str : System.out.print(str.length()); break;//fall through is allowed here
            case Object str : System.out.print(str); // Before String str won't compile

                // case null does not dominate any other label and is dominated only by default.
                // Thus, a case null may appear at any position but not after default.
            case null : System.out.print("null"); //fall through is allowed here
                // default : System.out.print(object3); // won't compile unreachable because of Object stra
        }
    }
}
