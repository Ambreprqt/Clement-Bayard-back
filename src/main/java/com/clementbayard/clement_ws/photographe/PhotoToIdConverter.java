package com.clementbayard.clement_ws.photographe;


import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import com.clementbayard.clement_ws.photo.Photo;

public class PhotoToIdConverter implements Converter<Photo, Long> {
    @Override
    public Long convert(MappingContext<Photo, Long> context) {
        Photo photo = context.getSource();
        return photo != null ? photo.getId() : null;
    }
}

