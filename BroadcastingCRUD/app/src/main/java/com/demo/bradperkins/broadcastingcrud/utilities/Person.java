package com.demo.bradperkins.broadcastingcrud.utilities;

import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bradperkins on 10/27/16.
 */
public class Person extends ArrayList<String> implements Serializable {

    private static final long serialVersionUID = -7791154359356162736L;

    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String AGE = "AGE";

    private String firstName;
    private String lastName;
    private String age;

    public Person() {

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public Person(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person(Bundle bundle){
        if (bundle != null) {
            this.firstName = bundle.getString(FIRSTNAME);
            this.lastName = bundle.getString(LASTNAME);
            this.age = bundle.getString(AGE);
        }
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(FIRSTNAME, this.firstName);
        bundle.putString(LASTNAME, this.lastName);
        bundle.putString(AGE, String.valueOf(this.age));
        return bundle;
    }

    public String toString() {
        return firstName;
    }

}
