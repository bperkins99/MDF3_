package com.example.bperk.mapsdetaildemo.utilities;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by bperk on 11/4/2016.
 */

public class FileHelper {

    private static final String FILENAME = "MDF3mapphotos";

    static PhotoInfo photoInfo = new PhotoInfo();

    public static void writeData(ArrayList<PhotoInfo> photoInfoList, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(photoInfoList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<PhotoInfo> readData(Context context) {
        ArrayList<PhotoInfo> photoInfoList = null;
        try {
            FileInputStream is = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(is);

            photoInfoList = (ArrayList<PhotoInfo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            //In case there is nothing in photoInfolist, catch it
            photoInfoList = new ArrayList<>();
            e.printStackTrace();
        }

        return photoInfoList;
    }

}
