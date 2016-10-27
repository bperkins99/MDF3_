package com.demo.bradperkins.broadcastingcrud.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    public static String PERSON_FIRST_NAME = "PERSON_FIRST_NAME";
    public static String PERSON_LAST_NAME = "PERSON_LAST_NAME";
    public static String PERSON_AGE = "PERSON_AGE";

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        String first = intent.getStringExtra("first");
        String last = intent.getStringExtra("last");
        String age = intent.getStringExtra("age");

        PERSON_FIRST_NAME = first;
        PERSON_LAST_NAME = last;
        PERSON_AGE = age;
        //Toast.makeText(context, PERSON_FIRST_NAME + PERSON_LAST_NAME + PERSON_AGE + "!!!!!", Toast.LENGTH_SHORT).show();




    }
}
