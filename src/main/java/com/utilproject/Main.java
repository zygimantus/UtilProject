package com.utilproject;

import com.utilproject.pojo.Person;
import com.utilproject.util.Rand;
import com.utilproject.util.Reflect;
import com.utilproject.util.Time;
import com.utilproject.util.Validator;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import org.apache.log4j.Logger;

/**
 *
 * @author Zygimantus
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        logger.info("Program started");

        Calendar cal = Calendar.getInstance();

        String ats = Time.getDurationString((int) (cal.getTimeInMillis() / 1000));
        System.out.println(ats);
        ats = Time.secondsToHMS((int) (cal.getTimeInMillis() / 1000));
        System.out.println(ats);

        // create object
        Person person = new Person("zygimantus", "zygimantus@gmail.com");

        // do methods
        try {
            Reflect.invokeGetters(Person.class, person);
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            logger.error(ex);
        }

        Class[] arr = Reflect.getClasses("com.utilproject");
        for (Class arr1 : arr) {
            System.out.println(arr1);
        }

        Reflect.getPrivateFields(Person.class);

        Reflect.getAllMethods(Person.class);

        Reflect.getPublicMethods(Person.class);

        int counter = 0;
        for (int i = 0; i < 100; i++) {

            String s = Rand.randomString();
            if (Validator.email(s)) {
                System.out.println(s);
                counter++;
            }
        }
        System.out.println("TOTAL VALID EMAILS: " + counter);

        logger.info("Program ended");
    }

}
