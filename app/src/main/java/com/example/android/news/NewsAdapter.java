package com.example.android.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Will on 2/3/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private ArrayList<News> mNewsStories;

    public NewsAdapter(Context context, ArrayList<News> newsStories) {
        super(context, 0, newsStories);

        mNewsStories = newsStories;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        News news = mNewsStories.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.title = (TextView) convertView.findViewById(R.id.title);
        viewHolder.section = (TextView) convertView.findViewById(R.id.section);
        viewHolder.date = (TextView) convertView.findViewById(R.id.date);

        viewHolder.title.setText(news.getTitle());
        viewHolder.section.setText(news.getSection());
        viewHolder.date.setText(news.getDate());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mNewsStories.get(position).getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView section;
        TextView date;
    }
}
