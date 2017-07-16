package com.jmgits.samples.rxjava.common;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public final class TweeterUtils {

    public static final int DEFAULT_MESSAGES = 10;

    public static Observable<Tweet> simulateObservableThatCompletes() {
        return simulateObservableThatCompletes(DEFAULT_MESSAGES);
    }

    public static Observable<Tweet> simulateObservableThatCompletes(final int numTweets) {

        return Observable.create(s ->

                new Thread(() -> {

                    generateNextMessages(s, numTweets);

                    s.onComplete();

                }).start()
        );
    }

    public static Observable<Tweet> simulateObservableThatFails() {

        return Observable.create(s ->

                new Thread(() -> {

                    int numTweetsBeforeError = new Random().nextInt(DEFAULT_MESSAGES + 1);

                    generateNextMessages(s, numTweetsBeforeError);

                    s.onError(new RuntimeException("Test error"));

                }).start()
        );
    }

    private TweeterUtils(){
    }

    //
    // private methods

    private static void generateNextMessages(ObservableEmitter<Tweet> s, int numTweet) {

        IntStream.range(0, numTweet).forEach(tmp -> {

            sleep();

            s.onNext(new Tweet("Message_" + tmp, "Author_" + tmp, LocalDateTime.now()));

        });
    }

    private static void sleep() {

        // no more than half a second
        long timeToSleepInMs = (long) (500 * Math.random());

        try {
            Thread.sleep(timeToSleepInMs);
        } catch (InterruptedException e) {

            // we don't really care
            throw new RuntimeException(e);
        }

    }
}
