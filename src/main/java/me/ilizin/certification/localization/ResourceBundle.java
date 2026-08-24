package me.ilizin.certification.localization;

import java.io.Console;

public class ResourceBundle {

    public static void main(String[] args) {

        /* Resource bundles are hierarchical, meaning, a resource bundle always has a parent bundle unless the bundle
           is at the top of the hierarchy, in which case, it is called the "base bundle".
           The resource bundles whose names start with a common base name, such as helloapp. The remaining part of the
           name of a resource bundle is created using various attributes of a locale such as the language and the
           country separated by underscores. These attributes must always be in the following order:

           baseName + "_" + language + "_" + script + "_" + country + "_" + variant

           So, for example, there can be a hierarchy of resource bundles with names helloapp, helloapp_fr, and
           helloapp_fr_CA, where the bundle named helloapp is at the top (grandparent) and the bundle named helloapp_fr_CA
           is at the bottom. Note that a bundle named helloapp_fr_FR will also be a part of this hierarchy but it will
           be on a new branch splitting from helloapp_fr.

           Understanding the hierarchy of resource bundles is important because when a key is not found in a particular
           bundle, it is automatically searched for in the parent bundle up to the base bundle. When a key is not found
           in any of the ancestor bundles either, a MissingResourceException is thrown.

           For example, we want to localize our app for French speaking people in Canada? Right, hellopapp_fr_CA
           indeed. But remember that the values from this bundle will not be used for French speaking people in France
           because their locale would be fr_FR and they would search for the keys in helloapp_fr_FR.

           However, if we have resource bundle named helloapp_fr instead of helloapp_fr_CA, then the values from this
           bundle can be used for all French speaking locales because helloapp_fr is a parent of helloapp_fr_CA as well
           as helloapp_fr_FR.
           */
        System.out.println();
        /* The ResourceBundle class has several overloaded static get Bundle methods out of which two are particularly
           important - getBundle(String baseName) and getBundle(String baseName, Locale locale). The former returns a
           ResourceBundle for the user's default locale while the later returns a ResourceBundle for the given locale.
           The baseName is the common name of the all the resource bundles of a family as explained before. */
        java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("helloapp");
        Console c = System.console();
        String name = c.readLine(rb.getString("What is your name?")+" ");
        /* To look up a key in a ResourceBundle, you need to first get hold of a ResourceBundle that you are interested
           in using one of the getBundle methods and then call one of the following three getXXX methods on that resource bundle:
                1. String getString(String key): Gets a string for the given key from this resource bundle or one of its parents.
                2. String[] getStringArray(String key): Gets a string array for the given key from this resource bundle or one of its parents.
                3. Object getObject(String key): Gets an object for the given key from this resource bundle or one of its parents. */
        String greetings = rb.getString("Hi");
        System.out.println(greetings + " " + name);
    }
}
