package com.demo.bradperkins.broadcastingcrud.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;

public class MyReceiver extends BroadcastReceiver {

    public static String EXTRA_FIRST_NAME = "com.demo.bradperkins.broadcastingcrud.PERSON_FIRST_NAME";
    public static String EXTRA_LAST_NAME = "com.demo.bradperkins.broadcastingcrud.PERSON_LAST_NAME";
    public static String EXTRA_AGE = "com.demo.bradperkins.broadcastingcrud.PERSON_AGE";

    ArrayList<Person> personList;
    Person person;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receivin
        FileHelper fileHelper = new FileHelper();

        String first = intent.getStringExtra("first");
        String last = intent.getStringExtra("last");
        String age = intent.getStringExtra("age");

        EXTRA_FIRST_NAME = first;
        EXTRA_LAST_NAME = last;
        EXTRA_AGE = age;
        Toast.makeText(context, EXTRA_FIRST_NAME + EXTRA_LAST_NAME + EXTRA_AGE + "!!!!!", Toast.LENGTH_SHORT).show();

        person = new Person(EXTRA_FIRST_NAME, EXTRA_LAST_NAME, EXTRA_AGE);
        personList.add(person);
        fileHelper.writeData(personList, context);

    }
}
