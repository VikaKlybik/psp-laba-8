package com.bsuir.laba8_1.model;

import com.bsuir.laba8_1.enums.ImgCategory;

public class Image {
    private Integer id;
    private String link;
    private String name;
    private String description;
    private ImgCategory imgCategory;

    public Image(Integer id, String link, String name, String description, ImgCategory imgCategory) {
        this.id = id;
        this.link = link;
        this.name = name;
        this.description = description;
        this.imgCategory = imgCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImgCategory getImgCategory() {
        return imgCategory;
    }

    public void setImgCategory(ImgCategory imgCategory) {
        this.imgCategory = imgCategory;
    }
}