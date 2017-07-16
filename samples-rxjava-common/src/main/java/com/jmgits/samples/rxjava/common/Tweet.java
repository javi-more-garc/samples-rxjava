package com.jmgits.samples.rxjava.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created by javi.more.garc on 16/07/17.
 */
@AllArgsConstructor
@Getter
@ToString
public class Tweet {

    private String message;
    private String author;
    private LocalDateTime date;
}
