package com.utilproject;

import com.utilproject.pojo.Person;
import com.utilproject.util.Rand;
import com.utilproject.util.Reflect;
import com.utilproject.util.Validator;
import java.beans.IntrospectionException;
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
	    Reflect.invokeGetters(Person.class, person);
	} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
	    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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
