package com.clementbayard.clement_ws.photo;


import net.coobird.thumbnailator.Thumbnails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.clementbayard.clement_ws.photographe.Photographe;
import com.clementbayard.clement_ws.photographe.PhotographeDto;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    ModelMapper modelMapper= new ModelMapper();

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public PhotoDto savePhoto(String type, MultipartFile file, PhotographeDto photographeDto) throws IOException {
        byte[] content = file.getBytes();
        Photo photo = new Photo();
        photo.setType(type);
        photo.setPhotographe(modelMapper.map(photographeDto, Photographe.class));
        String filename = photographeDto.getId() + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        photo.setFilename(filename);

        String filePath = Paths.get(uploadDir, filename).toString();
        Path path = Paths.get(filePath);
        Files.write(path, content);

        Photo savedPhoto = photoRepository.save(photo);

        return modelMapper.map(savedPhoto, PhotoDto.class);
    }

    public PhotoDto findById(long id){
        Photo photo= photoRepository.findById(id).orElse(null);
        if(photo == null){
            throw new NullPointerException("Photo not found for ID: " + photo.getId());
        }
        return  modelMapper.map(photo, PhotoDto.class);
    }

    public List<PhotoDto> findAllByPhotographe(long id){
        List<Photo> photos=photoRepository.getPhotosByPhotographeId(id);
        List<PhotoDto> photoDtos= new ArrayList<>();
        for (Photo photo :photos) {
            photoDtos.add(modelMapper.map(photo, PhotoDto.class));
        }
        return photoDtos;
    }

    public List<PhotoDto> findAllByPhotographeAndType(long id, String type) {
        List<Photo> photos=photoRepository.getPhotosByPhotographeIdAndType(id, type);
        List<PhotoDto> photoDtos= new ArrayList<>();
        for (Photo photo :photos) {
            photoDtos.add(modelMapper.map(photo, PhotoDto.class));
        }
        return photoDtos;
    }

    public byte[] compressImage(InputStream inputStream) throws IOException {
        BufferedImage originalImage = ImageIO.read(inputStream);

        BufferedImage compressedImage = Thumbnails.of(originalImage)
                .size(800,800)
                .outputQuality(1)
                .asBufferedImage();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(compressedImage, "jpg", baos); // Vous pouvez ajuster le format selon vos besoins
        return baos.toByteArray();
    }
}