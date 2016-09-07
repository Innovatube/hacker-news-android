package com.innovatube.hackernews.injection.component;

import com.innovatube.hackernews.injection.PerActivity;
import com.innovatube.hackernews.injection.module.ActivityModule;
import com.innovatube.hackernews.ui.topstory.TopStoriesActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)


public interface ActivityComponent {
    void inject(TopStoriesActivity activity);
}
