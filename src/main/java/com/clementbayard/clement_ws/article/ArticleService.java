package com.clementbayard.clement_ws.article;

import com.clementbayard.clement_ws.categories.Categorie;
import com.clementbayard.clement_ws.categories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categorieRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CategoryRepository categorieRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.categorieRepository = categorieRepository;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public String createArticle(ArticleDto articleDto) {

        String categorieNom = articleDto.getCategorieNom();
        Categorie categorie = categorieRepository.findByNom(categorieNom);

        Article article = modelMapper.map(articleDto, Article.class);

        article.setCategorie(categorie);

        articleRepository.save(article);

        return "Article créé avec succès!";
    }

    public String deleteArticle(long id) {
        Article article = articleRepository.getReferenceById(id);
        articleRepository.delete(article);
        return "Article supprimé avec succès!";
    }

}
