package com.iloveyou.controller;

import com.iloveyou.entity.Animal;
import com.iloveyou.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iloveyou.service.ImageService;

@RequestMapping("/images")
@RestController
public class ImageController {
    @Autowired
    ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") Long id) {
        String data = imageService.getImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(data);
    }

    @PostMapping
    public ResponseEntity<?> setImage(@RequestParam("file") MultipartFile file) {
        return imageService.setImage(file);
    }
}