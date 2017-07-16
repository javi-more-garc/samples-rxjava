package com.jmgits.samples.rxjava.chapter1;

import io.reactivex.Observable;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public class HelloWorld {

    public static void main(String[] args) {

        Observable.create(s -> {
            s.onNext("Hello World!");
            s.onComplete();
        }).subscribe(System.out::println);
    }
}
