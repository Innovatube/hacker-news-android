package com.innovatube.hackernews.injection.component;

import android.app.Application;
import android.content.Context;

import com.innovatube.hackernews.data.DataManager;
import com.innovatube.hackernews.data.remote.InnovatubeService;
import com.innovatube.hackernews.injection.ApplicationContext;
import com.innovatube.hackernews.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by TOIDV on 4/4/2016.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    Retrofit retrofit();

    InnovatubeService inploiService();

    DataManager dataManager();
}
