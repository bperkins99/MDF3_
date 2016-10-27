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
        personList.add(new Person("Brad", "Perkins", "1"));
        personList.add(new Person("Jen", "Perkins", "2"));
        personList.add(new Person("Carson", "Perkins", "3"));
        personList.add(new Person("Kenzi", "Perkins", "4"));
        personList.add(new Person("Parker", "Perkins", "5"));
    }


}
