package com.utilproject.pojo;

/**
 *
 * @author Zygimantus
 */
public class Person extends Entity {

    private final String name;
    private final String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
