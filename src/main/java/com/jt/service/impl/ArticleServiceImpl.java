package com.jt.service.impl;

import com.jt.common.util.UserUtils;
import com.jt.entity.Article;
import com.jt.entity.Category;
import com.jt.entity.Tag;
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
    @Transactional
    public Integer updateArticle(Article article) {

        Article oldArticle = articleRepository.getOne(article.getId());

        oldArticle.setTitle(article.getTitle());
        oldArticle.setSummary(article.getSummary());
        oldArticle.setBody(article.getBody());
        oldArticle.setCategory(article.getCategory());
        oldArticle.setTags(article.getTags());

        return oldArticle.getId();



//        return 0;
    }

    @Override
    @Transactional
    public void deleteArticle(Integer id) {
        articleRepository.delete(id);
    }

    @Override
    public List<Article> listArticlesByTag(Integer tagId) {

        Tag t = new Tag();
        t.setId(tagId);

        return articleRepository.findByTags(t);

//        return List.of();
    }

    @Override
    public List<Article> listArticlesByCategory(Integer categoryId) {
        Category c = new Category();

        c.setId(categoryId);

        return articleRepository.findByCategory(c);


    }

    @Override
    @Transactional
    public Article getArticleAndAddViews(Integer id) {
        int count = 1 ;
        Article article = articleRepository.getOne(id);
        article.setViewCounts(article.getViewCounts() + count);
        return article;
    }

    @Override
    public List<Article> listHotArticles(int limit) {
        return articleRepository.findOrderByViewsAndLimit(limit);
    }

    @Override
    public List<Article> listNewArticles(int limit) {
        return articleRepository.findOrderByCreateDateAndLimit(limit);
    }

    @Override
    public List<ArticleVO> listArchives() {
        return articleRepository.listArchives();
    }
}
