package com.innovatube.hackernews.injection.module;

import android.app.Activity;
import android.content.Context;

import com.innovatube.hackernews.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideApplication() {
        return mActivity;
    }

}
