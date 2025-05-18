package com.jt.vo;

import com.jt.entity.Category;

public class CategoryVO extends Category {

    private static final long serialVersionUID = 1L;

    private int articles;

    public int getArticles() {
        return articles;
    }

    public void setArticles(int articles) {

        this.articles = articles;
    }


}
