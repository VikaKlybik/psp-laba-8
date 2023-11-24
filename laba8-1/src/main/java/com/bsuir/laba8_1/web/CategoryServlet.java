package com.bsuir.laba8_1.web;

import java.io.*;
import java.util.List;

import com.bsuir.laba8_1.enums.ImgCategory;
import com.bsuir.laba8_1.model.Image;
import com.bsuir.laba8_1.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "categoryServlet", value = "/image")
public class CategoryServlet extends HttpServlet {
    private ImageService imageService;


    public void init() {
        imageService = ImageService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String category = request.getParameter("category");
        List<Image> imageList = imageService.getAllImages(category);
        request.setAttribute("imageList", imageList);
        request.setAttribute("categories", ImgCategory.values());
        request.getRequestDispatcher("/WEB-INF/pages/images.jsp").forward(request, response);
    }
}