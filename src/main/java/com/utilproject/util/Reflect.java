package com.utilproject.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zygimantas
 */
public class Reflect {

    // http://stackoverflow.com/a/2638662/1766166
    public static void invokeGetters(Class aClass, Object object) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

	for (PropertyDescriptor pd : Introspector.getBeanInfo(aClass).getPropertyDescriptors()) {
	    if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
		System.out.println(pd.getReadMethod().getName() + ": " + pd.getReadMethod().invoke(object));
	    }
	}
    }

    // http://stackoverflow.com/a/15315457/1766166
    public static int getPrivateFields(Class aClass) {
	List<Field> privateFields = new ArrayList<>();
	Field[] allFields = aClass.getDeclaredFields();
	for (Field field : allFields) {
	    if (Modifier.isPrivate(field.getModifiers())) {
		privateFields.add(field);
	    }
	}
	return privateFields.size();
    }

    public static void getPublicMethods(Class aClass) {
	Method[] methods = aClass.getMethods();
	for (Method method : methods) {
	    System.out.println("public method: " + method);
	}
    }

    public static void getAllMethods(Class aClass) {
	for (Method method : aClass.getDeclaredMethods()) {
	    System.out.println(method.getName());
	}
    }

}
