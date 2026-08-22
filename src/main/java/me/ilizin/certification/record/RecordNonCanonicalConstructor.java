package me.ilizin.certification.record;

public class RecordNonCanonicalConstructor {

    public record Student(int id, String name, String address) {

        /* A record is allowed to have any number of non-canonical constructors but the first line of such constructors
           must be a call to another constructor of that record using the this (...) syntax (also called an
           "alternate constructor"). An implication of this requirement is that by the time the control reaches
           the second line of non-canonical constructor's body, the component fields of this record are already set.
           Since the component fields are final, you can't modify them anymore.
           Name of the parameter in a non-canonical constructor doesn't have to be the same as the name of the component
           field. */
        public Student(int whatever, String name) throws Exception { // Throws clause is allowed.
            /* I haven't provided a canonical constructor, so the compiler will provide it automatically, so the call to
               this(...) is valid. It is possible to validate input arguments or to supply normalized values of input
               arguments to the alternate constructor */
            // this(0); Not allowed, it's detected as a recursive constructor call
            this(checkId(whatever), name.trim(), "DUMMY");
            /* component fields of this record are already set at this point */
            // this.name = "Dummy";
            /* Valid but name refers to the constructor parameter and updating it has no impact on the component field */
            name = "Dummy";
        }

        public Student(int whatever) throws Exception {
            this(0, "");
        }

        private static int checkId(int value) {
            if (value < 0) {
                throw new IllegalArgumentException("Bad Id");
            }
            return value;
        }
    }

    public static void main(String[] args) {
        System.out.println();
        Student student = new Student(1, "John", "Main square");
        System.out.println(student);
    }
}
