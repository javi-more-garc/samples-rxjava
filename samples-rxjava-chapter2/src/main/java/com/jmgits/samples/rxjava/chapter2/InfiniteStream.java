package com.jmgits.samples.rxjava.chapter2;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public class InfiniteStream {

    public static void main(String[] args) throws InterruptedException {

        Observable<BigInteger> naturalNumbers = Observable.create(subscriber ->

                new Thread(() -> {

                    sleep(1);

                    BigInteger i = ZERO;
                    while (!subscriber.isDisposed()) {
                        subscriber.onNext(i);
                        i = i.add(ONE);
                    }

                }).start()
        );

        Disposable subscribe1 = naturalNumbers.subscribe(v -> System.out.println("Subscribe 1: " + v));
        Disposable subscribe2 = naturalNumbers.subscribe(v -> System.out.println("Subscribe 2: " + v));

        sleep(5);
        subscribe1.dispose();

        sleep(5);
        subscribe2.dispose();
    }

    public static void sleep(long timeToSleepInMs) {

        try {
            Thread.sleep(timeToSleepInMs);
        } catch (InterruptedException e) {

            // we don't really care
            throw new RuntimeException(e);
        }

    }
}
