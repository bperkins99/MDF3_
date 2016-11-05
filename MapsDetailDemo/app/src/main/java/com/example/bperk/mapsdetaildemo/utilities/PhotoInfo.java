package com.example.bperk.mapsdetaildemo.utilities;

import android.net.Uri;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by bperk on 11/4/2016.
 */

public class PhotoInfo extends ArrayList<String> implements Serializable {

    private static final long serialVersionUID = -7791154359356162736L;

    private String firstName;
    private String lastName;
    private String coordinates;
    private Uri imageUri;


    public PhotoInfo(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getCoordinate() {
        return coordinates;
    }

    public void setCoordinate(String coordinate) {
        this.coordinates = coordinate;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public PhotoInfo(String firstName, String lastName, String coordinates, Uri imageUri) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.coordinates = coordinates;
        this.imageUri = imageUri;
    }
}
