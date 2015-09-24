package com.utilproject.test;

import com.utilproject.pojo.Person;
import com.utilproject.util.Reflect;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Zygimantas
 */
public class ReflectionTest {

    private static final String USER = "user";
    private static final String EMAIL = "email@gmail.com";

    private static final int NR_OF_FIELDS = 2;
    private static final int NR_OF_INH_FIELDS = 3;

    @Test
    public void test1() {

        Person person = new Person(USER, EMAIL);

        int nrOfFields = Reflect.getPrivateFields(person.getClass());

        Assert.assertTrue(nrOfFields == NR_OF_FIELDS);

        List<Field> list = Reflect.getInheritedFields(person.getClass());
        for (Field f : list) {
            System.out.println(f);
        }
        nrOfFields = list.size();

        Assert.assertTrue(nrOfFields == NR_OF_INH_FIELDS);

    }
}
