package com.innovatube.hackernews.data.local;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferenceHelper {
    private String url;

    @Inject
    public PreferenceHelper() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
