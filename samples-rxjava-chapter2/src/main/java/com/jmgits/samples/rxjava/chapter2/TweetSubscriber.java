package com.jmgits.samples.rxjava.chapter2;


import com.jmgits.samples.rxjava.common.Tweet;
import com.jmgits.samples.rxjava.common.TweeterFlowableUtils;
import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public class TweetSubscriber {

    public static void main(String[] args) {

        Flowable<Tweet> tweetObservable = TweeterFlowableUtils.simulateOneThatCompletes();

        tweetObservable.subscribe(new TweeterSubscriber());
    }

    private static class TweeterSubscriber implements Subscriber<Tweet> {

        private Subscription subscription;


        @Override
        public void onSubscribe(Subscription subscription) {
            System.out.println("Subscribed: " + subscription);

            this.subscription = subscription;
        }

        @Override
        public void onNext(Tweet tweet) {
            System.out.println("Tweet: " + tweet);

            if (Math.random() > .75) {
                this.subscription.cancel();
                System.out.println("Cancelled subscription (" + subscription + ")");
            }
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
