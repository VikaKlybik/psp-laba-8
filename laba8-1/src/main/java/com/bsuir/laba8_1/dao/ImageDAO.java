package com.bsuir.laba8_1.dao;

import com.bsuir.laba8_1.enums.ImgCategory;
import com.bsuir.laba8_1.exception.ImageNotFoundException;
import com.bsuir.laba8_1.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageDAO {
    private List<Image> imageList;
    private static ImageDAO instance;
    public static synchronized ImageDAO getInstance() {
        if(instance == null) {
            instance = new ImageDAO();
        }
        return instance;
    }

    private ImageDAO() {
        generateTestData();
    }
    private void generateTestData() {
        imageList = new ArrayList<>();
        imageList.add(new Image(1, "https://i.bigenc.ru/resizer/resize?sign=_kkwY7Adec4DTTW-zNEIBA&filename=vault/447854841afef6007ca5d6ae7191fd5f.webp&width=1280", "Abraham Lincoln", "Abraham Lincoln (February 12, 1809 – April 15, 1865) was an American statesman and lawyer who served as the 16th President of the United States from March 1861 until his assassination in April 1865. Lincoln led the United States through the American Civil War—its bloodiest war and perhaps its greatest moral, constitutional, and political crisis. In doing so, he preserved the Union, abolished slavery, strengthened the federal government, and modernized the economy", ImgCategory.PEOPLE));
        imageList.add(new Image(2, "https://otdyhateli.com/wp-content/uploads/2016/03/32635862283_888199ea09_h.jpg", "Canada Nature", "Exploring Canada's nature is not just a visual feast; it's an immersive experience that taps into the soul's connection with the Earth. Whether it's the serene solitude of a remote alpine lake or the adrenaline rush of witnessing a grizzly bear in its natural habitat, Canada's nature beckons adventurers, poets, and dreamers alike to discover the wonders that lie within its borders.", ImgCategory.NATURE));
        imageList.add(new Image(3, "https://cdn.imagin.studio/getImage?angle=01&billingTag=web&customer=carwow&make=mercedes&modelFamily=g-class&modelVariant=station-wagon&modelYear=2024&paintId=pspc0004&tailoring=carwow&width=1200&zoomLevel=0&zoomType=fullscreen", "2023 Mercedes-Benz G-Class", " Mercedes G-Class is all about image, and that’s why it looks the way it does. In addition, because it caters to a wealthy clientele accustomed to getting exactly what they want, you can choose from an array of paint and trim colors, wheel designs, interior hues, and dashboard accents. However, they all come with the front bull guard that makes the G-Class resemble a gangly teenager fresh from a visit to the orthodontist. (A Mercedes spokesperson explained to me that the bars are there to meet crash-test requirements.)", ImgCategory.AUTO));
    }

    public List<Image> findAllImagesByType(ImgCategory imgCategory) {
        if(imgCategory == null) {
            return imageList;
        }

        List<Image> imageListByType = new ArrayList<>();
        for(Image image: imageList) {
            if(image.getImgCategory() == imgCategory) {
                imageListByType.add(image);
            }
        }
        return imageListByType;
    }

    public Image findById(Integer id) {
        for(Image image: imageList) {
            if(image.getId().equals(id)) {
                return image;
            }
        }
        throw new ImageNotFoundException(id);
    }

}