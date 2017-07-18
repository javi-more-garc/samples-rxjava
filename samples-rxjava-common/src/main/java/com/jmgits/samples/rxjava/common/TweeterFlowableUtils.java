package com.jmgits.samples.rxjava.common;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

import static io.reactivex.BackpressureStrategy.MISSING;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public final class TweeterFlowableUtils {

    public static final int DEFAULT_MESSAGES = 10;

    public static Flowable<Tweet> simulateOneThatCompletes() {
        return simulateOneThatCompletes(DEFAULT_MESSAGES);
    }

    public static Flowable<Tweet> simulateOneThatCompletes(final int numTweets) {

        return Flowable.create(s ->

                new Thread(() -> {

                    generateNextMessages(s, numTweets);

                    s.onComplete();

                }).start()
        , MISSING);
    }

    public static Flowable<Tweet> simulateOneThatFails() {

        return Flowable.create(s ->

                new Thread(() -> {

                    int numTweetsBeforeError = new Random().nextInt(DEFAULT_MESSAGES + 1);

                    generateNextMessages(s, numTweetsBeforeError);

                    s.onError(new RuntimeException("Test error"));

                }).start()
        , MISSING);
    }

    private TweeterFlowableUtils(){
    }

    //
    // private methods

    private static void generateNextMessages(FlowableEmitter<Tweet> s, int numTweet) {

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
