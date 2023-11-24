package com.bsuir.laba8_1.exception;

public class ImageNotFoundException extends RuntimeException {
    private final Integer id;
    public ImageNotFoundException(Integer id) {
        super("Image with id ="+ id+" not found!");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}