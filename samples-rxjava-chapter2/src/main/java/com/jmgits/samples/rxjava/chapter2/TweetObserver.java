package com.jmgits.samples.rxjava.chapter2;


import com.jmgits.samples.rxjava.common.Tweet;
import com.jmgits.samples.rxjava.common.TweeterObservableUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public class TweetObserver {

    public static void main(String[] args) {

        Observable<Tweet> tweetObservable = args.length == 0 || !args[0].toUpperCase().contains("ERROR") ?
                TweeterObservableUtils.simulateOneThatCompletes() :
                TweeterObservableUtils.simulateOneThatFails();

        tweetObservable.subscribe(new TweeterObserver());
    }

    private static class TweeterObserver implements Observer<Tweet> {

        @Override
        public void onSubscribe(Disposable disposable) {
            System.out.println("Subscribed!");
        }

        @Override
        public void onNext(Tweet tweet) {
            System.out.println("Tweet: " + tweet);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("Error: " + throwable.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.println("Completed");
        }
    }
}
