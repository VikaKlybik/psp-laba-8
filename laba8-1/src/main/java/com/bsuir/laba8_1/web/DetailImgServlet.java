package com.bsuir.laba8_1.web;

import com.bsuir.laba8_1.model.Image;
import com.bsuir.laba8_1.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "detailImgServlet", value = "/image/*")
public class DetailImgServlet extends HttpServlet {
    private ImageService imageService;


    public void init() {
        imageService = ImageService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer id = Integer.parseInt(request.getPathInfo().substring(1));
        Image image = imageService.getImageById(id);
        request.setAttribute("image", image);
        request.getRequestDispatcher("/WEB-INF/pages/image-details.jsp").forward(request, response);
    }
}