package com.demo.bradperkins.newsreader;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by bradperkins on 10/29/16.
 */
public class News implements Parcelable{


    String newsTitle;
    String newsUrl;

    public News(String newsTitle, String newsUrl) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
    }

    public News() {
        this.newsUrl = newsUrl;
        this.newsTitle = newsTitle;
    }

    protected News(Parcel in) {
        newsTitle = in.readString();
        newsUrl = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(newsTitle);
        dest.writeString(newsUrl);
    }
}
