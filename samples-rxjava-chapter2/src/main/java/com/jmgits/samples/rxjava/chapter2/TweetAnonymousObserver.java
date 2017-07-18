package com.jmgits.samples.rxjava.chapter2;


import com.jmgits.samples.rxjava.common.Tweet;
import com.jmgits.samples.rxjava.common.TweeterObservableUtils;
import io.reactivex.Observable;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public class TweetAnonymousObserver {

    public static void main(String[] args) {

        Observable<Tweet> tweetObservable = args.length == 0 || !args[0].toUpperCase().contains("ERROR") ?
                TweeterObservableUtils.simulateOneThatCompletes() :
                TweeterObservableUtils.simulateOneThatFails();

        tweetObservable.subscribe(
                TweetAnonymousObserver::printTweet,
                TweetAnonymousObserver::printError,
                TweetAnonymousObserver::printComplete
        );
    }

    //
    // private methods

    private static void printTweet(Tweet tweet) {
        System.out.println("Tweet: " + tweet);
    }

    private static void printError(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }


    private static void printComplete() {
        System.out.println("Completed");
    }

}
