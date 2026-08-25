package me.ilizin.certification.localization;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalizingText {

    /* While developing an internationalized application, all user facing text or words used by the application are
       identified and listed in file along with their translations in the form of name-value pairs. */
    public static void main(String[] args) {

        System.out.println();
        /* The application contains two user facing strings - "What is your name?"and "Hi". To internationalize this
           application, these two strings must not be hardcoded. Instead, the code must use keys to look up the
           actual phrases at run time. This is done as follows:

           A ResourceBundle object contains locale-specific objects. Once the entries in the files are loaded in the
           resource bundle, the program can just lookup up a key in that resource bundle and use the returned locale
           specific value. A resource bundle is not limited to containing just strings.

            java.util.ResourceBundle is an abstract class but the JDK also provides a couple of concrete subclasses.
            One of those subclasses is PropertyResourceBundle, which can load key-value pairs from a properties file.
            However, instead of directly instantiating the PropertyResourceBundle class, we use ResourceBundle's
            getBundle method because this method is smart enough to figure out exactly which properties files are
            needed to create the desired resource bundle based on the user's locale, which is what we want.

            Therefore, while locating a bundle named helloapp, the getBundle method expects helloapp.properties to be
            located directly on the classpath. If the name of the bundle were test.helloapp, then helloapp.properties
            would have to be present in the test folder and the test folder would have to be on the classpath.*/
        ResourceBundle rb = ResourceBundle.getBundle("helloapp");
        /* The name of the keys in the properties file can be anything but the code must use the same key to loop up
           the value. The backslashes (\) in the properties files are required to escape the space character. */
        String name = rb.getString("What is your name?") + " Pablo";
        String greetings = rb.getString("Hi");
        System.out.println(greetings + " " + name);

        System.out.println();
        /* If test.properties is missing you will get a java.util.MissingResourceException. */
        try {
            rb = ResourceBundle.getBundle("test");
        } catch (MissingResourceException ex) {
            System.out.println("MissingResourceException");
        }
    }
}
