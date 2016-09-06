package com.innovatube.hackernews.injection.component;


import com.innovatube.hackernews.injection.PerActivity;
import com.innovatube.hackernews.injection.module.ActivityModule;
import com.innovatube.hackernews.ui.topstory.TopStoriesActivity;

import dagger.Component;

/**
 * Created by TOIDV on 4/4/2016.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)


public interface ActivityComponent {
    void inject(TopStoriesActivity activity);
}
