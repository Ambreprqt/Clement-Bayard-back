package com.clementbayard.clement_ws.photographe;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotographeService {
    ModelMapper modelMapper= new ModelMapper();

    private final PhotographeRepository photographeRepository;
    @Autowired
    public PhotographeService(PhotographeRepository photographeRepository) {
        this.photographeRepository = photographeRepository;
    }
    public PhotographeDto getById(long id){
        Photographe photographe=photographeRepository.findById(id).get();
        modelMapper.addConverter(new PhotoToIdConverter());
        return modelMapper.map(photographe, PhotographeDto.class);
    }

    public PhotographeDto save(Photographe photographe){
         modelMapper.addConverter(new PhotoToIdConverter());
        return modelMapper.map(photographeRepository.save(photographe), PhotographeDto.class);
    }

    public void delete(long id){
        photographeRepository.deleteById(id);
    }
}
