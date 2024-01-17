package com.clementbayard.clement_ws.photo;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.clementbayard.clement_ws.photographe.PhotographeDto;
import com.clementbayard.clement_ws.photographe.PhotographeService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
   /** @PostMapping(value = "/upload")
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
**/
    @GetMapping(value = "/byId/{id}")
    public ResponseEntity<PhotoDto> findById(@PathVariable long id){
        return ResponseEntity.ok(photoService.findById(id));
    }

    @GetMapping(value = "/byPhotographe/{id}")
    public ResponseEntity<List<PhotoDto>> findByPhotographe(@PathVariable long id){
        return ResponseEntity.ok(photoService.findAllByPhotographe(id));
    }
    @GetMapping("/byPhotographeAndType/{id}/{type}")
    @Cacheable(value = "photosCache", key = "{#id, #type}")
    public ResponseEntity<List<PhotoDto>> findByPhotographeAndType(@PathVariable long id, @PathVariable String type) {
        return ResponseEntity.ok(photoService.findAllByPhotographeAndType(id, type));
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> compressImage(@PathVariable String filename) {
        try {
            Resource resource = resourceLoader.getResource("classpath:/photos/" + filename);
            InputStream inputStream = resource.getInputStream();

            byte[] compressedImageBytes = photoService.compressImage(inputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentDispositionFormData("attachment", filename);
            headers.setContentLength(compressedImageBytes.length);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(compressedImageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

}
