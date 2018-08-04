package com.example.conditional;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by wshcatkin on 2018-06-23.
 */
public class BlankDisc {
    private String title;
    private String artist;

    public BlankDisc(@Value("${disc.title}") String title,
                     @Value("${disc.artist}") String artist) {
        this.title = title;
        this.artist = artist;
    }
}
