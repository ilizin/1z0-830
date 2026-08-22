package me.ilizin.certification.record;

public class RecordCompactConstructor {

    /* There's a new shorter way of writing the canonical constructor, called the "compact constructor".
       Exactly one constructor, either long-form or compact, not both. */
    public record Student(int id, String name, String address) {

        /* There is no formal parameter list in the compact constructor, the compiler just copies it from
           the one present in the record header. We are not assigning the value of the variables to the record fields,
           there is no this.id = id actually, you are not allowed to set or modify the component fields explicitly at all
           anywhere in the compact constructor.
           Component fields are final, and they are set only once at the end of the compact constructor by code
           inserted by the compiler. */
        public Student {
            if (id < 1) throw new RuntimeException("Invalid ID");
            if (name.length() < 0) {
                name = "";
                // this.name = name // Not allowed
            }
        }
    }

    public static void main(String[] args) {
        System.out.println();
        Student student = new Student(1, "John", "Main square");
        System.out.println(student);
    }
}
