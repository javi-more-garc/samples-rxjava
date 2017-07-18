package com.jmgits.samples.rxjava.chapter2;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.OAuth2Authorization;

/**
 * Created by javi.more.garc on 18/07/17.
 */
public class TwitterSubject {

    private final PublishSubject<Status> subject = PublishSubject.create();

    public TwitterSubject() {

        // introduce OAuth2 values
        OAuth2Authorization oAuth2Authorization = null;

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance(oAuth2Authorization);
        twitterStream.addListener(new StatusAdapter() {
            @Override
            public void onStatus(Status status) {
                subject.onNext(status);
            }

            @Override
            public void onException(Exception ex) {
                subject.onError(ex);
            }
        });

        twitterStream.sample();
    }

    public Observable<Status> observe() {
        return subject;
    }

    public static void main(String[] args) {

        TwitterSubject twitterSubject = new TwitterSubject();

        Observable<Status> observable = twitterSubject.observe();

        observable.subscribe(System.out::println);
    }
}
