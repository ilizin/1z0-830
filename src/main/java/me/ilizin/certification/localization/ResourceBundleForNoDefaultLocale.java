package me.ilizin.certification.localization;

import java.io.Console;
import java.util.Locale;

public class ResourceBundleForNoDefaultLocale {

    /* The process for creating a requested bundle for a non-default locale is almost the same as for the default locale.
       The only difference is that when getBundle is not able to find any locale specific properties file, it doesn't
       immediately return the base resource bundle. Before returning the base bundle, it tries to find and return the bundle
       for the default locale. The default locale of the user is es_ES and you request a bundle for fr_FR locale by calling
       getBundle("helloapp", Locale.of("fr", "FR")). Now, if this method is not able to find any properties file that
       matches fr_FR locale (i.e. helloapp_fr_FR.propertiesor helloapp_fr.properties), it will try to create and return
       the bundle for the default locale, i.e., helloapp_es_ES. If it is not able to find any properties file for es_ES
       locale either, it will return the base bundle, i.e., helloapp.
       If getBundle is not able to find any properties file that it can use to create any bundle at all. Well, in that
       case it throws a MissingResourceException. */
    public static void main(String[] args) {
        System.out.println();
        java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("helloapp", Locale.of("hi", "IN"));
        Console c = System.console();
        String name = c.readLine(rb.getString("What is your name?")+" ");
        String greetings = rb.getString("Hi");
        System.out.println(greetings + " " + name);
    }
}
