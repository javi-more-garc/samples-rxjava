package com.jmgits.samples.rxjava.chapter2;

import io.reactivex.Observable;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public class ObservableWithCache {

    public static void main(String[] args) {

        //
        // without cache: starting, create, element 1, complete, create, element 1, complete, exit

        Observable<Integer> intsWithoutCache = Observable
                .create(subscriber -> {

                    System.out.println("Create");

                    subscriber.onNext(1);
                    subscriber.onComplete();

                    System.out.println("Complete");

                });

        System.out.println("Starting");

        intsWithoutCache.subscribe(i -> System.out.println("Element: " + i));
        intsWithoutCache.subscribe(i -> System.out.println("Element: " + i));

        System.out.println("Exit");

        //
        // with cache: starting, create, element 1, complete, element 1, exit

        Observable<Integer> intsWithCache = Observable.<Integer>create(subscriber -> {

            System.out.println("Create");

            subscriber.onNext(1);
            subscriber.onComplete();

            System.out.println("Complete");

        }).cache();

        System.out.println("Starting");

        intsWithCache.subscribe(i -> System.out.println("Element: " + i));
        intsWithCache.subscribe(i -> System.out.println("Element: " + i));

        System.out.println("Exit");
    }
}
