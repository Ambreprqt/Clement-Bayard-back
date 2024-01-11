package com.clementbayard.clement_ws.photographe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/photographe")
public class PhotographeController {
    @Autowired
    private PhotographeService photographeService;

    public PhotographeController(PhotographeService photographeService) {
        this.photographeService = photographeService;
    }

    @GetMapping(value = "/byId/{id}")
    public ResponseEntity<PhotographeDto> getById(@PathVariable long id){
        PhotographeDto photographe= photographeService.getById(id);
        return ResponseEntity.ok(photographe);
    }
    @PostMapping(value = "/postOne")
    public ResponseEntity<PhotographeDto> create(@RequestBody Photographe photographe){
        PhotographeDto photographeToSaved=photographeService.save(photographe);
        return ResponseEntity.ok(photographeToSaved);
    }
}
