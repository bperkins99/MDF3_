package com.demo.bradperkins.broadcastingcrud.utilities;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by bradperkins on 10/27/16.
 */
public class FileHelper {

    private static final String FILENAME = "persondata.dat";

    static Person person = new Person();

    public static void writeData(ArrayList<Person> personList, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(personList);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Person> readData(Context context) {
        ArrayList<Person> personList = null;
        try {
            FileInputStream is = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(is);

            personList = (ArrayList<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            //In case there is nothing in personlist, catch it
            personList = new ArrayList<>();
            e.printStackTrace();
        }

        return personList;
    }




}
