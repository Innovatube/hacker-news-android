package com.innovatube.hackernews.ui.topstory;

import com.innovatube.hackernews.data.model.Story;
import com.innovatube.hackernews.ui.base.BaseViewInterface;

public interface TopStoriesViewInterface extends BaseViewInterface {
    void addStoryItemToAdapter(Story story);

    void hideLoadingViews();
}
