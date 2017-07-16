package com.jmgits.samples.rxjava.chapter1;

import io.reactivex.Observable;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public class MixSyncAndAsyncComputation {

    public static void main(String[] args) {

        Observable<Integer> integerObservable = Observable.create(s ->

                new Thread(() -> {
                    s.onNext(1);
                    s.onNext(2);
                    s.onNext(3);
                    s.onNext(4);
                    s.onComplete();
                }).start()

        );

        integerObservable
                .doOnNext(i -> System.out.println(Thread.currentThread()))
                .filter(i -> i % 2 == 0)
                .map(i -> "Value " + i + " processed on " + Thread.currentThread())
                .subscribe(s -> System.out.println("SOME VALUE =>" + s));

        System.out.println("Will print BEFORE values are emitted");
    }
}
