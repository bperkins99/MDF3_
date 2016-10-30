package com.demo.bradperkins.newsreader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradperkins on 10/29/16.
 */
public class NewsData {

    private List<News> newsList = new ArrayList<>();
    public List<News> getNewsList() {
        return newsList;
    }

    public NewsData() {
        newsList.add(new News("www.nfl.com", "Nfl"));

    }

}
