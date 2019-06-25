package com.hrd.springsecurity.service.ArticleService;



import com.hrd.springsecurity.repository.model.Article;

import java.util.List;

public interface ArticleService {
    void add(Article article);
    boolean remove(int id);
    List<Article> findAll();

    List<Article> paginate(int page, int limit);

    Article find(int id);

    List<Article> filter(Article article);

    void update(Article article);

    int countArticle();

}
