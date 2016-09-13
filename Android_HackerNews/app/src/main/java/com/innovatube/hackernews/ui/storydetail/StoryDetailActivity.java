package com.innovatube.hackernews.ui.storydetail;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.innovatube.hackernews.R;
import com.innovatube.hackernews.ui.base.BaseActivityWithDialog;
import com.innovatube.hackernews.ui.topstory.TopStoriesActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        startActivity(new Intent(this, TopStoriesActivity.class));
    }
}
