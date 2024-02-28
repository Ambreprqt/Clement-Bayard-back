package com.clementbayard.clement_ws.article;

import com.clementbayard.clement_ws.categories.Categorie;
import com.clementbayard.clement_ws.categories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Article> articleOptional = articleRepository.findById(id);

        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            articleRepository.delete(article);

            if (articleRepository.findById(id).isPresent()) {
                return "La suppression de l'article a échoué.";
            } else {
                return "Article supprimé avec succès!";
            }
        } else {
            return "L'article avec l'ID " + id + " n'existe pas.";
        }
    }




    public List<ArticleDto> getAll() {
        List<Article> articles = articleRepository.findAll();

        return articles.stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    public ArticleDto getById(long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);

        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            return modelMapper.map(article, ArticleDto.class);
        } else {
            throw new EntityNotFoundException("Article avec l'ID " + id + " n'a pas été trouvé");
        }
    }

}
