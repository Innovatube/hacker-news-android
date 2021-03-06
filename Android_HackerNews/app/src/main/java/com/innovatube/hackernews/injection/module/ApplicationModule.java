package com.innovatube.hackernews.injection.module;

import android.app.Application;
import android.content.Context;

import com.innovatube.hackernews.data.local.RealmHelper;
import com.innovatube.hackernews.data.remote.InnovatubeService;
import com.innovatube.hackernews.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;

@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    InnovatubeService provideInnovatubeService(Retrofit retrofit) {
        return retrofit.create(InnovatubeService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitInstance() {
        return InnovatubeService.Creator.newRetrofitInstance();
    }


    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    RealmHelper provideRealmHelper() {
        return new RealmHelper();
    }
}
