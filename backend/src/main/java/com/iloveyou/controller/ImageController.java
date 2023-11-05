package com.iloveyou.controller;

import com.iloveyou.entity.Animal;
import com.iloveyou.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.iloveyou.service.ImageService;

import java.io.IOException;

@RequestMapping("/images")
@RestController
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    AnimalRepository animalRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") Long id) {
        String data = imageService.getImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(data);
    }

    // api/images/animalId
    @PostMapping("/{animalId}")
    public ResponseEntity<?> uploadImage(@PathVariable Long animalId, @RequestPart(
            "image") MultipartFile image) {
        Animal animal =
                animalRepository.findById(animalId).get();
        Long imageId = imageService.setImage(image);
        animal.setImageId(imageId);

        animalRepository.save(animal);
        return ResponseEntity.ok("Image uploaded successfully");
    }
}