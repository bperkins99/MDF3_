package com.demo.bradperkins.broadcastingcrud.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.demo.bradperkins.broadcastingcrud.utilities.Person;
import com.demo.bradperkins.broadcastingcrud.fragments.PersonListFragment;
import com.demo.bradperkins.broadcastingcrud.R;

public class MainActivity extends AppCompatActivity implements PersonListFragment.OnPersonInterface {

    public static final String PERSON_BUNDLE = "PERSON_BUNDLE";
    private static final int REQUEST_CODE = 1001;
    private static final String ACTION_VIEW_DATA = "com.demo.bradperkins.broadcastingcrud.ACTION_VIEW_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_activity) {

            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


    ///////////SEnd this bundle to BroadcastReceiver
    @Override
    public void onItemSelected(Person person) {
        Bundle bundle = person.toBundle();
        Intent intent = new Intent(ACTION_VIEW_DATA);
        intent.putExtra("index", person);

        String first = person.getFirstName();
        String last = person.getLastName();
        String age = person.getAge();

        intent.putExtra("EXTRA_FIRST_NAME", first);
        intent.putExtra("EXTRA_LAST_NAME", last);
        intent.putExtra("EXTRA_AGE", age);

        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        this.sendBroadcast(intent);

        Intent detailIntent = new Intent(this, DetailActivity.class);
        startActivityForResult(detailIntent, REQUEST_CODE);

    }
}
