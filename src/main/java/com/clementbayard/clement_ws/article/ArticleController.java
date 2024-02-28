package com.clementbayard.clement_ws.article;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public String create(@RequestBody ArticleDto articleDto){
        service.createArticle(articleDto);
        return "Article créé :) !";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        service.deleteArticle(id);
        return "Article supprimé :o !";
    }
}
