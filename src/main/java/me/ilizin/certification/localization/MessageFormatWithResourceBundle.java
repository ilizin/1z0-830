package me.ilizin.certification.localization;

import java.util.Date;
import java.util.ResourceBundle;
import java.text.MessageFormat;

public class MessageFormatWithResourceBundle {

    public static void main(String[] args) {

        System.out.println();
        ResourceBundle rb = ResourceBundle.getBundle("helloapp");
        /* The value for yourbalanceis key will be "Your balance is {0}". in helloapp_es_ES.properties but
        Your balance {0} is in helloapp_fr_FR.properties. */
        String message = MessageFormat.format(rb.getString("yourbalanceis"), 100);
        System.out.println(message);

        System.out.println();
        /* Using the MessageFormat class is quite simple, the parameters are specified using curly braces and besides
           the parameter index (indexing starts with zero), you may specify how you want to format it as well.
           For example, a raw message could be, "On {0, date, short}, your balance was {1, number, currency}"
           and it can be formatted using an array of two objects as arguments like this:
           This will return "On 10/05/24, your balance was $100.00."

           A serious issue with MessageFormat is that it was created before the introduction of the java.time package
           and hasn't been updated to work with the new LocalDate/Time classes. So, if you want to use dates as
           arguments, you have to use java.util.Date objects.*/
        MessageFormat.format(rb.getString("yourbalanceis2"), new Object[]{new Date(), 100});
        System.out.println(message);
    }
}
