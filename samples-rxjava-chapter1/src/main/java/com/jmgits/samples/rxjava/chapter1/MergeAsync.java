package com.jmgits.samples.rxjava.chapter1;

import io.reactivex.Observable;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public class MergeAsync {

    public static void main(String[] args) {

        Observable<String> observable1 = Observable.create(s ->
                new Thread(() -> {
                    s.onNext("1a");
                    s.onNext("1b");
                    s.onComplete();

                }).start()
        );

        Observable<String> observable2 = Observable.create(s ->
                new Thread(() -> {
                    s.onNext("2a");
                    s.onNext("2b");
                    s.onComplete();
                }).start()
        );

        // 1a will appear before 1b (analogous for 2a and 2b)
        // 1a will appear before or after 2a

        Observable<String> mergedObservable = Observable.merge(observable1, observable2);

        mergedObservable.subscribe(System.out::println);
    }
}
