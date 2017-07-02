package com.utilproject.util;

import java.beans.*;
import java.io.*;
import java.lang.reflect.*;
import java.net.URL;
import java.util.*;

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
//                logGauta(pd.getReadMethod().getName(), pd.getReadMethod().invoke(object));
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

    /**
     * Gaunami paveldeti laukai. Geriau uz esama getDeclaredFields().
     *
     * @param type
     * @return
     * @see http://stackoverflow.com/a/2405757/1766166
     */
    public static List<Field> getInheritedFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
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

    /**
     * Scans all classes accessible from the context class loader which belong
     * to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     * @see https://dzone.com/articles/get-all-classes-within-package
     */
    public static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList classes = new ArrayList();
        for (Object directory : dirs) {
            classes.addAll(findClasses((File) directory, packageName));
        }
        return (Class[]) classes.toArray(new Class[classes.size()]);
    }

    // reikia lib...
//    public static Set getClassesInPackage(String pack, Class aClass) {
//
//        Reflections ref = new Reflections(pack, new SubTypesScanner(false));
//
//        Set<Class<? extends RStruktura>> allClasses
//                = ref.getSubTypesOf(aClass);
//
//        return allClasses;
//    }
    /**
     * Recursive method used to find all classes in a given directory and
     * subdirs.
     *
     * @param directory The base directory
     * @param packageName The package name for classes found inside the base
     * directory
     * @return The classes
     * @throws ClassNotFoundException
     * @see https://dzone.com/articles/get-all-classes-within-package
     */
    private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    /**
     * getInheritedFields alternatyva.
     *
     * @param fields
     * @param type
     * @return
     * @see http://stackoverflow.com/a/1042827/1766166
     */
    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

}
