package com.bsuir.laba8_1.service;

import com.bsuir.laba8_1.dao.ImageDAO;
import com.bsuir.laba8_1.enums.ImgCategory;
import com.bsuir.laba8_1.model.Image;

import java.util.List;

public class ImageService {
    private final ImageDAO imageDAO;
    private static ImageService instance;

    public static ImageService getInstance() {
        if(instance == null) {
            instance = new ImageService();
        }
        return instance;
    }
    private ImageService() {
        imageDAO = ImageDAO.getInstance();
    }

    public List<Image> getAllImages(String imgCategoryStr) {
        ImgCategory imgCategory = null;
        if(imgCategoryStr != null){
            imgCategory = ImgCategory.valueOf(imgCategoryStr);
        }
        return imageDAO.findAllImagesByType(imgCategory);
    }

    public Image getImageById(Integer id) {
        return imageDAO.findById(id);
    }
}