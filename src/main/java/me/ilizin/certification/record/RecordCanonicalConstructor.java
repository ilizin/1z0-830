package me.ilizin.certification.record;

public class RecordCanonicalConstructor {

    public record Student(int id, String name, String address) {

        /* Every record must have exactly one canonical constructor. If you don't provide it in your record explicitly,
           the compiler will provide it. Unlike the default constructor of a regular class, the canonical
           constructor must initialize all of the component fields of the record using the parameters passed to it.
           It has no throws clause and its access modifier is the same as that of the record itself, not more restrictive.
           It is possible defining the canonical constructor explicitly, the order, the names, and the types must
           match with the record header. Initialization of component fields must be done explicitly.
           Explicit calls to this(...) or super(...) are not allowed. */

        public Student(int id, String name, String address) {
            // Throws clause is not allowed. Body may throw unchecked exceptions.
            if (id < 1) throw new RuntimeException("Invalid ID"); //check validity of input
            this.id = id;
            if (name.trim().length() == 0) name = "DUMMY"; //fix input data
            this.name = name; // commenting this will generate a compilation error
            this.address = address;
        }
    };
}
