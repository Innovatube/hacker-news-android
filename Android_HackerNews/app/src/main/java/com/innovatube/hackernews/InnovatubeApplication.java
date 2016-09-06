package com.innovatube.hackernews;

import android.app.Application;
import android.content.Context;

import com.innovatube.hackernews.injection.component.ApplicationComponent;
import com.innovatube.hackernews.injection.component.DaggerApplicationComponent;
import com.innovatube.hackernews.injection.module.ApplicationModule;


/**
 * Created by TOIDV on 4/4/2016.
 */
public class InnovatubeApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    public static InnovatubeApplication get(Context context) {
        return (InnovatubeApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.setDefaultConfiguration(realmConfiguration);
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
//                        .build());


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
