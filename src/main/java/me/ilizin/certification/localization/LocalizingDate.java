package me.ilizin.certification.localization;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Date;
import java.util.Locale;

public class LocalizingDate {

    public static void main(String[] args) {
        System.out.println();
        /* To format the LocalDate/Time objects of the java.time package, you need to use the
        java.time.format.DateTimeFormatter class. It has three different methods, ofLocalizedDate, ofLocalizedTime,
        and ofLocalizedDateTime, which can be used for formatting LocalDate, LocalTime, and LocalDateTime respectively,
        they require a FormatStyle argument.
        DateTimeFormatter also has a ofPattern method, which can format the LocalDate/Time objects for any given Locale. */
        Locale frFR = Locale.of("fr", "Fr");
        Locale enUS = Locale.of("en", "US");
        DateTimeFormatter dfLocal = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println(dfLocal.format(LocalDate.of(2024, 12, 1)));
        System.out.println(dfLocal.format(LocalDateTime.of(2024, 12, 1, 11, 0)));
        try {
            System.out.println(dfLocal.format(LocalTime.of(11, 0)));
        } catch (UnsupportedTemporalTypeException ex) {
            System.out.println("UnsupportedTemporalTypeException");
        }
        dfLocal = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        try {
            System.out.println(dfLocal.format(LocalDate.of(2024, 12, 1)));
        } catch (UnsupportedTemporalTypeException ex) {
            System.out.println("UnsupportedTemporalTypeException");
        }
        System.out.println(dfLocal.format(LocalDateTime.of(2024, 12, 1, 11, 0)));
        System.out.println(dfLocal.format(LocalTime.of(11, 0)));

        DateTimeFormatter dfFr = DateTimeFormatter.ofPattern("dd MMM uuuu", frFR);
        System.out.println(dfFr.format(LocalDate.of(2024, 12, 1)));
        DateTimeFormatter dfUs = DateTimeFormatter.ofPattern("dd MMM uuuu", enUS);
        System.out.println(dfUs.format(LocalDate.of(2024, 12, 1)));

        System.out.println();
        /* JDK has a java.text.DateFormat class, which is similar to the java.text.NumberFormat and can be used for
           format dates according the given Locale. However, since this class was created long before the new Date/Time
           API, it can only format java.util.Date objects. Unfortunately, it hasn't been enhanced to format
           java.time.LocalDate/Time objects. */
        Date now = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(now);
        System.out.println("Formatted Date String: " + formattedDate);
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ITALY);
        formattedDate = formatter.format(now);
        System.out.println("Formatted Date String: " + formattedDate);

        System.out.println();
        /* DateTimeFormatter has several methods that can parse a string into a LocalDate/Time objects. Technically,
           parsing dates is mentioned in the objectives but parsing dates is quite complicated and we haven't seen anyone
           getting a question on this. */
        dfFr = DateTimeFormatter.ofPattern("dd MMM uuuu", frFR);
        System.out.println(dfFr.parse("23 août 2026"));
        dfUs = DateTimeFormatter.ofPattern("dd MMM uuuu", enUS);
        System.out.println(dfUs.parse("23 Aug 2026"));
        try {
            System.out.println(dfUs.parse("23 août 2026"));
        } catch (DateTimeParseException ex) {
            System.out.println("DateTimeParseException");
        }
    }
}
