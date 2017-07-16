package com.jmgits.samples.rxjava.chapter1;

import io.reactivex.Observable;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public class SyncComputation {

    public static void main(String[] args) {

        Observable<Integer> observable = Observable.create(s -> {
            s.onNext(1);
            s.onNext(2);
            s.onNext(3);
            s.onComplete();
        });

        observable.map(i -> "Number: " + i).subscribe(System.out::println);
    }
}
