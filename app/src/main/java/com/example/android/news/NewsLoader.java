package com.example.android.news;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Will on 2/3/2017.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);

        mUrl = url;
    }

    @Override protected void onStartLoading() {
        forceLoad();
    }

    @Override public ArrayList<News> loadInBackground() {

        return QueryUtils.fetchNewsData(mUrl);
    }
}
