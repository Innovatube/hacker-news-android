package com.innovatube.hackernews.ui.storydetail;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.innovatube.hackernews.R;
import com.innovatube.hackernews.ui.base.BaseActivityWithDialog;
import com.innovatube.hackernews.ui.topstory.TopStoriesActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoryDetailActivity extends BaseActivityWithDialog implements StoryDetailViewInterface {
    @BindView(R.id.webview)
    WebView webView;

    @Inject
    StoryDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);

        presenter.attachView(this);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

        });

        webView.loadUrl(presenter.getUrl());
    }

    @Override
    protected void setupDialogTitle() {
        String title = getString(R.string.title_dialog);
        alertDialog.setTitle(title);
        progressDialog.setTitle(title);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, TopStoriesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btnShareFacebook)
    public void OnButtonShareFacebookClick() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        shareIntent.putExtra(Intent.EXTRA_TEXT, presenter.getUrl());
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        boolean facebookInstalled = false;
        for (final ResolveInfo app : activityList) {
            if ((app.activityInfo.name).contains("facebook")) {
                facebookInstalled = true;
                final ActivityInfo activity = app.activityInfo;
                final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                shareIntent.setComponent(name);
                startActivityForResult(shareIntent, 0);
                break;
            }
        }
        if (!facebookInstalled) {
            showAlertDialog(getString(R.string.facebook_not_installed));
        }
    }
}
