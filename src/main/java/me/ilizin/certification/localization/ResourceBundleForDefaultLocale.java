package me.ilizin.certification.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleForDefaultLocale {

    public static void main(String[] args) {
        System.out.println();
        /* The getBundle methods automatically read a properties file whose name matches with the name of the requested
           resource bundle and create a PropertyResourceBundle instance that contains the entries defined in the properties
           file. So, for example, assuming that the user's default locale is es_ES, the call to
           ResourceBundle.getBundle("helloapp"), will look for a properties file named helloapp_es_ES.properties file in the
           classpath and will load the entries into the helloapp_es_ES bundle.

           Since resource bundles are hierarchical, creation of a ResourceBundle triggers the creation of its parent bundle
           as well. Therefore, once helloapp_es_ES bundle is created, its parent bundle helloapp_es would also be created automatically
           Thus, it will look for helloapp_es.properties to create the helloapp_es bundle. Similarly, since the parent of
           helloapp_es is helloapp, helloapp.properties will also be loaded */
        ResourceBundle rb = ResourceBundle.getBundle("helloapp");
        String name = rb.getString("What is your name?") + " Pablo";
        String greetings = rb.getString("Hi");
        System.out.println(greetings + " " + name);

        System.out.println();
        /* If, while creating the hierarchy of the resource bundles, at any stage, the getBundle method is not able to find
           the matching properties file, it tries to create the parent bundle of the requested bundle and returns that parent
           bundle instead. So, if helloapp_es_ES.properties is not available, getBundle("helloapp")
           method will try to create the parent bundle, i.e., helloapp_es as per the process described above and return this
           bundle instead. */
        Locale.setDefault(new Locale.Builder().setLanguage("it").setRegion("IT").build());
        rb = ResourceBundle.getBundle("helloapp");
        System.out.println(rb.getString("Hi"));
        rb = ResourceBundle.getBundle("helloapp2");
        System.out.println(rb.getString("Hi"));
    }
}
