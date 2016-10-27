package com.demo.bradperkins.broadcastingcrud.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.demo.bradperkins.broadcastingcrud.fragments.DetailFragment;
import com.demo.bradperkins.broadcastingcrud.R;

public class DetailActivity extends AppCompatActivity {

    public static final String DETAIL_FRAGMENT = "detail_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            if (savedInstanceState == null) {
//          Create the fragment, set its args, add it to the detail container
                DetailFragment fragment = new DetailFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.detailContainer, fragment)
                        .commit();
            }
//            DetailFragment fragment = new DetailFragment();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.add(R.id.detailContainer, fragment, DETAIL_FRAGMENT);
//            fragmentTransaction.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.delete_person) {

            Toast.makeText(DetailActivity.this, "Delete Person", Toast.LENGTH_SHORT).show();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
