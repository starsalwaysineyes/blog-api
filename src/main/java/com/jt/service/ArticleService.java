package com.jt.service;

import com.jt.entity.Article;
import com.jt.vo.ArticleVO;
import com.jt.vo.PageVO;

import java.util.List;

public interface ArticleService {

    List<Article> listArticles(PageVO pageVO);

    List<Article> listArticles(ArticleVO articleVO, PageVO pageVO);

    List<Article> findAll();

    Article getArticleById(Integer id);

    Integer publishArticle(Article article);

    Integer saveArticle(Article article);

    Integer updateArticle(Article article);

    void deleteArticle(Integer id);

    List<Article> listArticlesByTag(Integer tagId);

    List<Article> listArticlesByCategory(Integer categoryId);

    Article getArticleAndAddViews(Integer id);

    List<Article> listHotArticles(int limit);

    List<Article> listNewArticles(int limit);

    List<ArticleVO> listArchives();




}
