package com.jt.repository.wrapper;

import com.jt.entity.Article;
import com.jt.vo.ArticleVO;
import com.jt.vo.PageVO;



import java.util.List;

public interface ArticleWrapper{

    List<Article> listArticles(PageVO page);

    List<Article> listArticles(ArticleVO article, PageVO page);

    List<ArticleVO> listArchives();

}
