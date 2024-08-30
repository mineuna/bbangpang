package com.bread.bbangpang.image.controller;

import com.bread.bbangpang.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/post/image")
    @ResponseBody
    public Map<String, Object> imageUpload(MultipartRequest request) throws Exception {

        Map<String, Object> responseData = new HashMap<>();

        try {
            String imageUrl = imageService.uploadImage(request);

            responseData.put("file", true);
            responseData.put("url", imageUrl);

            return responseData;
        } catch (IOException e) {
            responseData.put("file", false);

            return responseData;
        }

    }

}
