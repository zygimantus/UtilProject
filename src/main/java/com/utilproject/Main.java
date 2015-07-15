package com.utilproject;

import com.utilproject.pojo.Person;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zygimantus
 */
public class Main {

    public static void main(String[] args) {

        // create object
        Person person = new Person("zygimantus", "zygimantus@gmail.com");

        // do methods
        try {
            invokeGetters(Person.class, person);
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // http://stackoverflow.com/a/2638662/1766166
    public static void invokeGetters(Class aClass, Object object) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        for (PropertyDescriptor pd : Introspector.getBeanInfo(aClass).getPropertyDescriptors()) {
            if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                System.out.println(pd.getReadMethod().invoke(object));
            }
        }
    }
}
