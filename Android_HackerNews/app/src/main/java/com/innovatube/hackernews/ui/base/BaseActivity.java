package com.innovatube.hackernews.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.innovatube.hackernews.InnovatubeApplication;
import com.innovatube.hackernews.injection.component.ActivityComponent;
import com.innovatube.hackernews.injection.component.DaggerActivityComponent;

/**
 * Created by Thanh on 05/09/2016.
 */
public class BaseActivity extends AppCompatActivity {
    ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(InnovatubeApplication.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }
}
