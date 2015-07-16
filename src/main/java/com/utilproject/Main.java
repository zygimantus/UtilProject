package com.utilproject;

import com.utilproject.pojo.Person;
import com.utilproject.util.Rand;
import com.utilproject.util.Reflect;
import com.utilproject.util.Validator;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;

/**
 *
 * @author Zygimantus
 */
public class Main {
    
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
	
	logger.info("Program started");

	// create object
	Person person = new Person("zygimantus", "zygimantus@gmail.com");

	// do methods
	try {
	    Reflect.invokeGetters(Person.class, person);
	} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
	    logger.error(ex);
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
    }

}
