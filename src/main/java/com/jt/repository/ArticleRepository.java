package com.jt.repository;

import com.jt.entity.Article;
import com.jt.entity.Category;
import com.jt.entity.Tag;
import com.jt.repository.wrapper.ArticleWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> , ArticleWrapper {

    List<Article> findByTag(Tag tag);

    List<Article> findByCategory(Category category);

    @Query(value = "Select * from me_article order by view_counts desc limit :limit",nativeQuery = true)
    List<Article> findOrderByViewsAndLimit(@Param("limit") int limit);

    @Query(value = "select * from me_article order by create_date desc limit :limit")
    List<Article> findOrderByCreateDateAndLimit(@Param("limit") int limit);


}
