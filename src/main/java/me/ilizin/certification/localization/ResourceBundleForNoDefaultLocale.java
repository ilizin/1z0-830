package me.ilizin.certification.localization;

import java.util.Locale;

public class ResourceBundleForNoDefaultLocale {

    public static void main(String[] args) {

        System.out.println();
        /* The process for creating a requested bundle for a non-default locale is almost the same as for the default locale.
           The only difference is that when getBundle is not able to find any locale specific properties file, it doesn't
           immediately return the base resource bundle. Before returning the base bundle, it tries to find and return the bundle
           for the default locale. The default locale of the user is es_ES and you request a bundle for fr_FR locale by calling
           getBundle("helloapp", Locale.of("fr", "FR")). Now, if this method is not able to find any properties file that
           matches fr_FR locale (i.e. helloapp_fr_FR.properties or helloapp_fr.properties), it will try to create and return
           the bundle for the default locale, i.e., helloapp_es_ES. If it is not able to find any properties file for es_ES
           locale either, it will return the base bundle, i.e., helloapp.
           If getBundle is not able to find any properties file that it can use to create any bundle at all. Well, in that
           case it throws a MissingResourceException. */
        java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("helloapp",
                Locale.of("it", "IT"));
        String name = rb.getString("What is your name?") + " Ana";
        /* Loading a resource bundle and looking up the key in a resource bundle are two separate and independent steps.
           If a properties file for the non-default locale is present, then a resource bundle will be created using that file
           as explained in the previous section. The key lookup in the non-default bundle will happen in this non-default
           bundle hierarchy only and if the key is not found in it, a MissingResourceException will be thrown.
           The key will NOT be looked up in the resource bundle hierarchy of the default locale. The resource bundle hierarchy
           of the default locale is used only when no locale specific resource bundle is found. */
        String greetings = rb.getString("Hi");
        System.out.println(greetings + " " + name);
    }
}
