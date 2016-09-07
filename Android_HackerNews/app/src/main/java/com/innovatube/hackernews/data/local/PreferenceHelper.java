package com.innovatube.hackernews.data.local;

import com.innovatube.hackernews.data.model.Story;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Thanh on 07/09/2016.
 */
@Singleton
public class PreferenceHelper {
    private Story story;

    @Inject
    public PreferenceHelper() {
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
