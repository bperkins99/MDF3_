package com.demo.bradperkins.newsreader;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradperkins on 10/29/16.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    private Context context;
    private ArrayList<News> objects;

    public NewsAdapter(Context context, int resource, ArrayList<News> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = objects.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.new_list_item, null);
        TextView tv = (TextView) view.findViewById(R.id.newsTitle);
        tv.setText(news.getNewsTitle());

        return view;

    }
}
