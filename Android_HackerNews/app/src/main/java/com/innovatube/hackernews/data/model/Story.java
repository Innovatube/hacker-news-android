package com.innovatube.hackernews.data.model;

public class Story {
    private String by;
    private int descendants;
    private int score;
    private long time;
    private String title;
    private String type;
    private String url;


    public Story(String by, int descendants, int score, long time, String title, String type, String url) {
        this.by = by;
        this.descendants = descendants;
        this.score = score;
        this.time = time;
        this.title = title;
        this.type = type;
        this.url = url;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
