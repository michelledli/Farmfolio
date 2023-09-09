package com.iloveyou.domain;

import java.util.UUID;

/**
 * User object class used as a Data Transfer Object (DTO).
 * @author Niketa Kosyuk
 */
public class User {
    private UUID id;
    private String email, password, firstName, lastName;

    /**
     * Regular constructor for the user object.
     * @param id
     * @param email
     * @param password
     */
    public User(UUID id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the users unique ID.
     * @return
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Gets the users first name.
     * @return
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Gets the users last name.
     * @return
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Gets the users email.
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the users password.
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the users email.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the users password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}