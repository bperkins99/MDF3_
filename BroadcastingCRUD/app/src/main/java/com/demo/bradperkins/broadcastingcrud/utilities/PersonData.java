package com.demo.bradperkins.broadcastingcrud.utilities;

import com.demo.bradperkins.broadcastingcrud.utilities.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradperkins on 10/27/16.
 */
public class PersonData {

    private List<Person> personList = new ArrayList<>();
    public List<Person> getPersonList() {
        return personList;
    }

    public PersonData() {
        //personList.add(new Person("Brad", "Perkins", "1"));

    }


}
