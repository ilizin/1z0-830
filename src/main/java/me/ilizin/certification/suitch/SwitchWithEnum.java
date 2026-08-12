package me.ilizin.certification.suitch;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SwitchWithEnum {

    public static void main(String[] args) {

        // Enums are used very often in a switch statement.
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        switch (dayOfWeek) {
            case MONDAY:
                System.out.println("Today is Monday");
                break;
            /* Prior to Java 21, the case statements could only use constant without the enum name,
               so this would cause a compilation error. */
            case DayOfWeek.TUESDAY:
                System.out.println("Today is Tuesday");
                break;
            default:
                System.out.println("Today is a different day");
        }

        dayOfWeek = DayOfWeek.THURSDAY; // It prints "Today is Thursday, Saturday or Sunday"
        switch (dayOfWeek) {
            case MONDAY, TUESDAY -> System.out.println("Today is Monday or Tuesday");
            case WEDNESDAY, FRIDAY -> System.out.println("Today is Wednesday or Friday");
            default -> System.out.println("Today is Thursday, Saturday or Sunday");
        }

        dayOfWeek = DayOfWeek.TUESDAY; // It prints "Today is Thursday, Saturday or Sunday"
        switch (dayOfWeek) {
            // TUESDAY is just a label applied to the print statement.
            case MONDAY: TUESDAY:
                System.out.println("Today is Monday or Tuesday");
                break;
            case WEDNESDAY: FRIDAY:
                System.out.println("Today is Wednesday or Friday");
            default : System.out.println("Today is Thursday, Saturday or Sunday");
        }
    }
}
