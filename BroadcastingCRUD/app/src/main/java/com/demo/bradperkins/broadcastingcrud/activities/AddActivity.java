package com.demo.bradperkins.broadcastingcrud.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.demo.bradperkins.broadcastingcrud.R;
import com.demo.bradperkins.broadcastingcrud.fragments.AddFragment;


public class AddActivity extends AppCompatActivity {

    private static final String ADD_FRAGMENT = "add_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setTitle("Add Person");

        AddFragment savedFragment = (AddFragment) getSupportFragmentManager().findFragmentByTag(ADD_FRAGMENT);

        if(savedFragment == null) {
            AddFragment fragment = new AddFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.add_placeHolder, fragment, ADD_FRAGMENT);
            fragmentTransaction.commit();
        }


    }

    public void saveBtn(View view) {
        Toast.makeText(AddActivity.this, "Saved", Toast.LENGTH_SHORT).show();
    }
}
