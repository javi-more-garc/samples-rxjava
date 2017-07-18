package com.jmgits.samples.rxjava.chapter2;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by javi.more.garc on 16/07/17.
 */
public class ObservableFactory {

    public static void main(String[] args) throws InterruptedException {

        //
        // using a range

        Observable.range(10, 5).subscribe(value -> System.out.println("Range: " + value));

        //
        // timer (async)

        Observable.timer(1, SECONDS).subscribe(value -> System.out.println("Timer: " + value));

        //
        // interval (async)

        Observable.interval(1, SECONDS).subscribe(value -> System.out.println("Interval: " + value));

        TimeUnit.SECONDS.sleep(5L);

    }
}
