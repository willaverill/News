package com.example.android.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> {
    private NewsAdapter mNewsAdapter;

    private ListView mNewsListView;
    private TextView mEmptyStateTextView;
    private ProgressBar mLoadingSpinner;

    private static final int LOADER_ID = 0;
    private static final String URL = "https://content.guardianapis.com/search?api-key=a1352fff-3120-4c73-91d9-8c2e62aba3ec&q=surfing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsListView = (ListView) findViewById(R.id.news_list_view);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_state_text_view);
        mLoadingSpinner = (ProgressBar) findViewById(R.id.loading_spinner);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mLoadingSpinner.setVisibility(View.GONE);
        }
    }

    @Override public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {

        return new NewsLoader(MainActivity.this, URL);
    }

    @Override public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> newsStories) {
        mLoadingSpinner.setVisibility(View.GONE);

        mNewsAdapter = new NewsAdapter(this, newsStories);
        mNewsListView.setAdapter(mNewsAdapter);
    }

    @Override public void onLoaderReset(Loader<ArrayList<News>> loader) {
        mNewsAdapter = new NewsAdapter(this, new ArrayList<News>());
        mNewsListView.setAdapter(mNewsAdapter);
    }
}
