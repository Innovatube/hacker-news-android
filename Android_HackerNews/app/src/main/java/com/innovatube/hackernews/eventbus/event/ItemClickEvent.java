package com.innovatube.hackernews.eventbus.event;

/**
 * Created by Thanh on 08/09/2016.
 */
public class ItemClickEvent {
    private int position;

    public ItemClickEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
