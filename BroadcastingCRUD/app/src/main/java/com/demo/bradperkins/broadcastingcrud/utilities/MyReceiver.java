
package com.demo.bradperkins.broadcastingcrud.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.Toast;

import com.demo.bradperkins.broadcastingcrud.activities.AddActivity;
import com.demo.bradperkins.broadcastingcrud.fragments.AddFragment;

import java.util.ArrayList;

public class MyReceiver extends BroadcastReceiver {

    public static String ACTION_VIEW_DATA = "com.demo.bradperkins.broadcastingcrud.ACTION_VIEW_DATA";
    public static String ACTION_SAVE_DATA = "com.demo.bradperkins.broadcastingcrud.ACTION_SAVE_DATA";
    public static String ACTION_DELETE_DATA = "com.demo.bradperkins.broadcastingcrud.ACTION_DELETE_DATA";
    public static String ACTION_UPDATE_DATA = "com.demo.bradperkins.broadcastingcrud.ACTION_UPDATE_DATA";


    public static String EXTRA_FIRST_NAME = "com.demo.bradperkins.broadcastingcrud.PERSON_FIRST_NAME";
    public static String EXTRA_LAST_NAME = "com.demo.bradperkins.broadcastingcrud.PERSON_LAST_NAME";
    public static String EXTRA_AGE = "com.demo.bradperkins.broadcastingcrud.PERSON_AGE";

    ArrayList<Person> personList;
    Person person;
    AddFragment addFragment;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receivin
        FileHelper fileHelper = new FileHelper();
        AddActivity addActivity = new AddActivity();

        //VIEW DATA
        if (intent.getAction().equals(ACTION_VIEW_DATA)) {
            EXTRA_FIRST_NAME = intent.getStringExtra("EXTRA_FIRST_NAME");
            EXTRA_LAST_NAME = intent.getStringExtra("EXTRA_LAST_NAME");
            EXTRA_AGE = intent.getStringExtra("EXTRA_AGE");

            Toast.makeText(context, EXTRA_FIRST_NAME + EXTRA_LAST_NAME + EXTRA_AGE + "!!!!!", Toast.LENGTH_SHORT).show();
        }

        //SAVE DATA
        if (intent.getAction().equals(ACTION_SAVE_DATA)) {
            addFragment = new AddFragment();

            EXTRA_FIRST_NAME = intent.getStringExtra("EXTRA_FIRST_NAME");
            EXTRA_LAST_NAME = intent.getStringExtra("EXTRA_LAST_NAME");
            EXTRA_AGE = intent.getStringExtra("EXTRA_AGE");

            Toast.makeText(context, EXTRA_FIRST_NAME + EXTRA_LAST_NAME + EXTRA_AGE + "!!!!!", Toast.LENGTH_SHORT).show();
        }

        //UPDATE DATA
        if (intent.getAction().equals(ACTION_UPDATE_DATA)) {

        }

        //DELETE DATA
        if (intent.getAction().equals(ACTION_DELETE_DATA)) {

        }

//        String first = intent.getStringExtra("first");
//        String last = intent.getStringExtra("last");
//        String age = intent.getStringExtra("age");
//
//        EXTRA_FIRST_NAME = first;
//        EXTRA_LAST_NAME = last;
//        EXTRA_AGE = age;
//        Toast.makeText(context, EXTRA_FIRST_NAME + EXTRA_LAST_NAME + EXTRA_AGE + "!!!!!", Toast.LENGTH_SHORT).show();


    }
}
