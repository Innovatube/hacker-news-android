package com.innovatube.hackernews.ui.topstory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.innovatube.hackernews.R;
import com.innovatube.hackernews.data.model.Story;
import com.innovatube.hackernews.eventbus.RxEventBus;
import com.innovatube.hackernews.eventbus.event.ItemClickEvent;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder> {

    private ArrayList<Story> storyList;

    public StoryAdapter() {
        storyList = new ArrayList<>();
    }

    @Override
    public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topstory_item, parent, false);
        return new StoryHolder(v);
    }

    @Override
    public void onBindViewHolder(StoryHolder holder, final int position) {
        final Story story = storyList.get(position);
        holder.title.setText(story.getTitle());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(story.getTime());
        holder.dateTime.setText(calendar.get(Calendar.HOUR_OF_DAY) + " : " + calendar.get(Calendar.MINUTE));
        holder.storyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxEventBus.getInstance().post(new ItemClickEvent(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public ArrayList<Story> getStoryList() {
        return storyList;
    }

    public void setItems(ArrayList<Story> stories) {
        storyList = stories;
        notifyDataSetChanged();
    }

    public class StoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView title;
        @BindView(R.id.tvDateTime)
        TextView dateTime;
        @BindView(R.id.story_item)
        LinearLayout storyItem;

        public StoryHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public void addItem(Story story) {
        if (!storyList.contains(story)) {
            storyList.add(story);
            notifyItemInserted(storyList.size() - 1);
        } else {
            storyList.set(storyList.indexOf(story), story);
            notifyItemChanged(storyList.indexOf(story));
        }
    }
}
