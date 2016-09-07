package com.innovatube.hackernews.ui.topstory;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innovatube.hackernews.R;
import com.innovatube.hackernews.data.model.Story;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder> {

    @Inject
    TopStoriesPresenter presenter;
    private ArrayList<Story> storyList = new ArrayList<>();

    @Override
    public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topstory_item, parent, false);
        return new StoryHolder(v);
    }

    @Override
    public void onBindViewHolder(StoryHolder holder, int position) {
        final Story story = storyList.get(position);
        holder.title.setText(story.getTitle());
        holder.dateTime.setText(String.valueOf(story.getTime()));
        holder.storyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presenter != null) {
                    presenter.saveStoryUserChoose(story);
                } else {
                    Log.e("presenter", "null");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public class StoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView title;
        @BindView(R.id.tvDateTime)
        TextView dateTime;
        @BindView(R.id.story_item)
        CardView storyItem;

        public StoryHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public void addItem(Story story) {
        if (!storyList.contains(story)) {
            storyList.add(story);
            notifyDataSetChanged();
        }
    }
}
