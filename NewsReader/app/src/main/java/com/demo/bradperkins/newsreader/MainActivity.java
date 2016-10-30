package com.demo.bradperkins.newsreader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsListFragment.OnNewsInterface {

    public static final String TAG = MainActivity.class.getSimpleName();
    private News mNews;
    public static final String NEWS_DATA = "NEWS_DATA";

    public String title;
    public String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String newsUrl = "https://www.reddit.com/r/nfl/hot.json";

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(newsUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    alertUserError();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        //Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mNews = getNewsData(jsonData);
                        } else {
                            //Log.v(TAG, response.body().string());
                            alertUserError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else {
            Toast.makeText(MainActivity.this, "Network is unavailable", Toast.LENGTH_SHORT).show();
        }
//        Log.e(TAG, "MainActivity UI");
//        Toast.makeText(MainActivity.this, "??????" + newsList.size(), Toast.LENGTH_SHORT).show();

    }

    private News getNewsData(String jsonData) throws JSONException{


        JSONObject data = new JSONObject(jsonData);
        JSONObject data1 = data.getJSONObject("data");
        JSONArray children = data1.getJSONArray("children");

        for (int i=1; i<children.length(); i++){
            JSONObject data3 = children.getJSONObject(i);
            JSONObject data4 = data3.getJSONObject("data");

            title = data4.getString("title").toString();
            url = data4.getString("url").toString();



            String newsTitle = title;
            String newsUrl = url;

            Log.i(TAG, "!!!!!!" + newsTitle);
            Log.i(TAG, "!!!!!!" + newsUrl);
            ArrayList<News> newsList = new ArrayList<>();
            newsList.add(new News(newsTitle, newsUrl));
            Log.i(TAG, "!!!!!!" + newsList.size());

            i++;

        }

        return new News();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }


    private void alertUserError(){
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getFragmentManager(), "error_dialog");
    }

    @Override
    public void onItemSelected(News index) {

        String url = index.getNewsUrl();
        String title = index.getNewsTitle();
//
//        Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);

    }

}
