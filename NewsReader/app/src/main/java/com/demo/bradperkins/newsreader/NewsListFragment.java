package com.demo.bradperkins.newsreader;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends ListFragment {

    ArrayList<News> newsList;
    private OnNewsInterface activity;

    String titleString;
    String urlString;

    public NewsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        updateData();

//        titleString = getArguments().getString("title");
//        urlString = getArguments().getString("url");

        newsList =  new ArrayList<>();

//        newsList.add(new News("Yahoo", "http://www.yahoo.com"));
        newsList.add(new News(titleString, urlString));

        NewsAdapter adapter = new NewsAdapter(getActivity(), R.layout.fragment_news_list, newsList);
        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);


        Toast.makeText(view.getContext(), "kjsdksj", Toast.LENGTH_SHORT).show();




        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (activity != null){
//            newsList = activity.getNewsList();
//        }
//    }

    public void updateData(){
        newsList =  new ArrayList<>();
//        newsList.add(new News("Yahoo", "http://www.yahoo.com"));
        newsList.add(new News(titleString, urlString));
        NewsAdapter adapter = new NewsAdapter(getActivity(), R.layout.fragment_news_list, newsList);
        setListAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (OnNewsInterface) context;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        News index = newsList.get(position);
        activity.onItemSelected(index);
    }

    public interface OnNewsInterface{
        void onItemSelected(News index);
//        ArrayList<News> getNewsList();
    }


}
