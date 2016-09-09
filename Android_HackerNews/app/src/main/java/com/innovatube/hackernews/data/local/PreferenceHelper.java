package com.innovatube.hackernews.data.local;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Thanh on 07/09/2016.
 */
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
