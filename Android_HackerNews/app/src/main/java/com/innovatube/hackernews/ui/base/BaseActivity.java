package com.innovatube.hackernews.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.innovatube.hackernews.InnovatubeApplication;
import com.innovatube.hackernews.injection.component.ActivityComponent;
import com.innovatube.hackernews.injection.component.DaggerActivityComponent;

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
