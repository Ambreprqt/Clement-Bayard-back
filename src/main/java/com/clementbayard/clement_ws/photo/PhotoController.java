package com.clementbayard.clement_ws.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.clementbayard.clement_ws.photographe.PhotographeDto;
import com.clementbayard.clement_ws.photographe.PhotographeService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotoController {
    private final PhotoService photoService;
    private final PhotographeService photographeService;
    private final ResourceLoader resourceLoader;

    @Autowired
    public PhotoController(PhotoService photoService, PhotographeService photographeService, ResourceLoader resourceLoader) {
        this.photoService = photoService;
        this.photographeService = photographeService;
        this.resourceLoader = resourceLoader;
    }
    @PostMapping(value = "/upload")
    public ResponseEntity<PhotoDto> uploadImage(
            @RequestParam("type") String type,
            @RequestParam("file") MultipartFile file,
            @RequestParam("photographeId") Long photographeId
    ) {
        PhotographeDto photographeDto = photographeService.getById(photographeId);
        if (photographeDto == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            PhotoDto savedPhoto = photoService.savePhoto(type, file, photographeDto );
            return ResponseEntity.ok(savedPhoto);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping(value = "/byId/{id}")
    public ResponseEntity<PhotoDto> findById(@PathVariable long id){
        return ResponseEntity.ok(photoService.findById(id));
    }

    @GetMapping(value = "/byPhotographe/{id}")
    public ResponseEntity<List<PhotoDto>> findByPhotographe(@PathVariable long id){
        return ResponseEntity.ok(photoService.findAllByPhotographe(id));
    }
    @GetMapping(value = "/byPhotographeAndType/{id}/{type}")
    public ResponseEntity<List<PhotoDto>> findByPhotographeAndType(@PathVariable long id, @PathVariable String type){
        return ResponseEntity.ok(photoService.findAllByPhotographeAndType(id, type));
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable String filename) {
        try {
            // Charger la ressource du classpath
            Resource resource = resourceLoader.getResource("classpath:/photos/" + filename);

            // Lire le contenu de la ressource
            try (InputStream inputStream = resource.getInputStream()) {
                byte[] imageBytes = inputStream.readAllBytes();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
