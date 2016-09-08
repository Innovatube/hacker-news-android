package com.innovatube.hackernews.eventbus;

import java.util.Observable;

import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Thanh on 08/09/2016.
 */
public class RxEventBus {
    private static final RxEventBus INSTANCE = new RxEventBus();

    private final Subject<Object, Object> subject = new SerializedSubject<>(PublishSubject.create());

    public static RxEventBus getInstance() {
        return INSTANCE;
    }

    public void post(Object event) {
        subject.onNext(event);
    }

    public Subject<Object, Object> toObserverable() {
        return subject;
    }
}
