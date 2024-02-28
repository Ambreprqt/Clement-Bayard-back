package com.clementbayard.clement_ws.article;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    final private ArticleService service;

    @Autowired
    private ModelMapper mapper;

    public ArticleController(ArticleService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<ArticleDto> getAll(){
        return service.getAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ArticleDto getById(@PathVariable long id){
        return service.getById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public String create(@RequestBody ArticleDto articleDto){
        service.createArticle(articleDto);
        return "Article créé :) !";
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){

        return service.deleteArticle(id);
    }
}
