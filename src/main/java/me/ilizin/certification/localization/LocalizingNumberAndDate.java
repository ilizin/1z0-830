package me.ilizin.certification.localization;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Date;
import java.util.Locale;

public class LocalizingNumberAndDate {

    /* People in different regions are accustomed to seeing numbers formatted differently. The JDK provides an abstract
       class java.text.NumberFormatter that can format a number as per the local standard. */
    public static void main(String[] args) {

        /* The NumberFormat class has several static factory methods that can be used to create a NumberFormat instance.
           Most of them have overloaded variants that take a Locale as an argument. The ones that do not take a Locale,
           return an instance that formats a number according to the default locale. Note that the instances returned
           by these methods are not thread safe.*/
        int oneMillion = 1_000_000;
        Locale frFR = Locale.of("fr", "Fr");
        Locale enUS = Locale.of("en", "US");
        NumberFormat nfUs = NumberFormat.getCurrencyInstance(enUS);
        NumberFormat nfFr = NumberFormat.getCurrencyInstance(frFR);
        System.out.println("US:" + nfUs.format(oneMillion)); //prints US:$1,000,000.00
        System.out.println("France:" + nfFr.format(oneMillion)); //prints France:1 000 000,00 €

        nfUs = NumberFormat.getPercentInstance(enUS);
        nfFr = NumberFormat.getPercentInstance(frFR);
        System.out.println("US:"  +nfUs.format(0.1)); //prints US:10%
        System.out.println("France:" + nfFr.format(0.1)); //prints France:10 %

        NumberFormat nfC = NumberFormat.getCompactNumberInstance(); //get NumberFormat for the default locale
        System.out.println("Default Locale (Compact):" + nfC.format(oneMillion)); //prints Default Locale (Compact):10L
        nfC = NumberFormat.getCompactNumberInstance(enUS, NumberFormat.Style.SHORT);
        System.out.println("en_US (Compact):" + nfC.format(oneMillion)); //prints en_US (Compact):1M
        nfC = NumberFormat.getCompactNumberInstance(frFR, NumberFormat.Style.SHORT);
        System.out.println("fr_FR (Compact):" + nfC.format(oneMillion)); //prints fr_FR (Compact):1 M

        System.out.println();
        try {
        /* A NumberFormat instance can also be used to parse a String containing a number written in locale specific
           manner. For example, nfC.parse("1M"), will return a Long containing the value 1000000. Be aware that the
           return type of the parse method is Number but it may actually return a Double or a Long depending on whether
           the string has digits after decimal or not. Also remember that this method can potentially throw a
           java.text.ParseException, which is a checked exception.*/
            nfC = NumberFormat.getCompactNumberInstance(enUS, NumberFormat.Style.SHORT);
            System.out.println(nfC.parse("1M"));
        } catch (ParseException ex) {
            System.out.println(ex);
        }

        System.out.println();
        /* To format the LocalDate/Time objects of the java.time package, you need to use the
        java.time.format.DateTimeFormatter class. It has three different methods, ofLocalizedDate, ofLocalizedTime,
        and ofLocalizedDateTime, which can be used for formatting LocalDate, LocalTime, and LocalDateTime respectively,
        they require a FormatStyle argument.
        DateTimeFormatter also has a ofPattern method, which can format the LocalDate/Time objects for any given Locale. */
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
