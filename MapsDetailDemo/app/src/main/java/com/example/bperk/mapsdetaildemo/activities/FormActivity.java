package com.example.bperk.mapsdetaildemo.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bperk.mapsdetaildemo.R;
import com.example.bperk.mapsdetaildemo.fragments.FormFragment;
import com.example.bperk.mapsdetaildemo.utilities.FileHelper;
import com.example.bperk.mapsdetaildemo.utilities.PhotoInfo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FormActivity extends AppCompatActivity implements LocationListener {

    private static final String FRAGMENT_FORM = "FRAGMENT_FORM";

    private static final int REQUEST_TAKE_PICTURE = 0x01001;
    private static final int REQUEST_ENABLE_GPS = 0x02001;

    ImageView imageView;

    PhotoInfo photoInfo;
    ArrayList<PhotoInfo> photoInfoList;
    FileHelper fileHelper = new FileHelper();

    Uri imageUri;
    private String imageName;

    LocationManager mManager;
    String mCoordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        setTitle("Add New Stuff");
        photoInfoList = fileHelper.readData(this);
        imageView = (ImageView) findViewById(R.id.image_view);


        FormFragment formFragment = (FormFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_FORM);

        if (formFragment == null) {
            FormFragment fragment = new FormFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.form_container, fragment, FRAGMENT_FORM);
            fragmentTransaction.commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.save_action) {

            EditText firtNameET = (EditText) findViewById(R.id.first_name_et);
            EditText lastNameET = (EditText) findViewById(R.id.last_name_et);

            String firstName = firtNameET.getText().toString().trim();
            String lastName = lastNameET.getText().toString().trim();

            if (firstName.isEmpty() || lastName.isEmpty()){
                Toast.makeText(this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
            } else {
                enableGps();

                photoInfo = new PhotoInfo(firstName, lastName, mCoordinates, imageUri);
                photoInfoList.add(photoInfo);
                fileHelper.writeData(photoInfoList, this);
                Toast.makeText(this, "Photo and Name Added", Toast.LENGTH_SHORT).show();
                Log.i("Form",  imageUri.toString());
                Log.i("Form", mCoordinates);
                Log.i("Form", firstName);
                Log.i("Form", lastName);
                Log.i("Form", photoInfoList.size() + "");

                //Go Back to map
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            }

        }
        if (id == R.id.capture_action) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageUri = getImageUri();
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(cameraIntent, REQUEST_TAKE_PICTURE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageView = (ImageView) findViewById(R.id.image_view);
        if (requestCode == REQUEST_TAKE_PICTURE && resultCode == RESULT_OK) {
            if (imageUri != null) {
                setPic(imageUri);
                addImageToGallery(imageUri);
            } else {
                imageView.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
            }
        }
    }


    private Uri getImageUri() {
        System.out.println("Getting image URI");

        imageName = new SimpleDateFormat("MMddyyyy_HHmmss").format(new Date(System.currentTimeMillis()));

        File imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File appDir = new File(imageDir, "MappingPhotos");
        appDir.mkdirs();

        File image = new File(appDir, imageName + ".jpg");
        try {
            image.createNewFile();
            System.out.println("image.createNewFile();");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("return Uri.fromFile(image);");
        return Uri.fromFile(image);
    }

    public void addImageToGallery(Uri imageUri) {
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        sendBroadcast(scanIntent);
    }

    //When i set the pic for the map, do this stuff here
    public void setPic(Uri imgUri){
        System.out.println("PIC set successfully");
        String pic = imgUri.getEncodedPath();

        // Get the size of the ImageView
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the size of the image
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pic, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Figure out which way needs to be reduced less
        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        }

        // Set bitmap options to scale the image decode target
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(pic, bmOptions);

        imageView.setImageBitmap(bitmap);

    }

    public void enableGps(){
        if (mManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, (LocationListener) this);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            Location loc = mManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(loc != null) {

                mCoordinates = loc.getLatitude() + " " + loc.getLongitude();

                Log.i("LongLat", " " + loc.getLatitude() + "    " +loc.getLongitude());
            }

        } else {
            new AlertDialog.Builder(this)
                    .setTitle("GPS Unavailable")
                    .setMessage("Please enable GPS in the system settings.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(settingsIntent, REQUEST_ENABLE_GPS);
                        }

                    })
                    .show();
        }
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
