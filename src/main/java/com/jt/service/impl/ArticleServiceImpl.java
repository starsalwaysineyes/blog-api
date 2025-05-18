package com.jt.service.impl;

import com.jt.common.util.UserUtils;
import com.jt.entity.Article;
import com.jt.entity.User;
import com.jt.repository.ArticleRepository;
import com.jt.service.ArticleService;
import com.jt.vo.ArticleVO;
import com.jt.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;





    @Override
    public List<Article> listArticles(PageVO pageVO) {

        return articleRepository.listArticles(pageVO);
//        return List.of();
    }

    @Override
    public List<Article> listArticles(ArticleVO articleVO, PageVO pageVO) {
        return articleRepository.listArticles(articleVO,pageVO);
//        return List.of();
    }

    @Override
    public List<Article> findAll() {
//        return List.of();
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Integer id) {
//        return null;
        return articleRepository.getOne(id);
    }

    @Override
    @Transactional
    public Integer publishArticle(Article article) {
//        return 0;

        if(null !=article.getId()){
            return this.updateArticle(article);
        }
        else {
            return this.saveArticle(article);
        }


    }

    @Override
    @Transactional
    public Integer saveArticle(Article article) {
//        return 0;
        User currentUser = UserUtils.getCurrentUser();

        if(null != currentUser){
            article.setAuthor(currentUser);
        }

        article.setCreateDate(new Date());
        article.setWeight(Article.Article_Common);

        return articleRepository.save(article).getId();

    }

    @Override
    public Integer updateArticle(Article article) {
        return 0;
    }

    @Override
    public void deleteArticle(Integer id) {

    }

    @Override
    public List<Article> listArticlesByTag(Integer tagId) {
        return List.of();
    }

    @Override
    public List<Article> listArticlesByCategory(Integer categoryId) {
        return List.of();
    }

    @Override
    public Article getArticleAndAddViews(Integer id) {
        return null;
    }

    @Override
    public List<Article> listHotArticles(int limit) {
        return List.of();
    }

    @Override
    public List<Article> listNewArticles(int limit) {
        return List.of();
    }

    @Override
    public List<ArticleVO> listArchives() {
        return List.of();
    }
}
