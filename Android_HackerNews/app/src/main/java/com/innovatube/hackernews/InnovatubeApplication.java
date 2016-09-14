package com.innovatube.hackernews;

import android.app.Application;
import android.content.Context;

import com.innovatube.hackernews.injection.component.ApplicationComponent;
import com.innovatube.hackernews.injection.component.DaggerApplicationComponent;
import com.innovatube.hackernews.injection.module.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class InnovatubeApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    public static InnovatubeApplication get(Context context) {
        return (InnovatubeApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public synchronized ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }
}
