package com.innovatube.hackernews.injection.component;

import android.app.Application;
import android.content.Context;

import com.innovatube.hackernews.data.DataManager;
import com.innovatube.hackernews.data.remote.InnovatubeService;
import com.innovatube.hackernews.injection.ApplicationContext;
import com.innovatube.hackernews.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    Retrofit retrofit();

    InnovatubeService inploiService();

    DataManager dataManager();

    Realm realm();
}
