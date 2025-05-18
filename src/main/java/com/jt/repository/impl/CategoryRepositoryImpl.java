package com.jt.repository.impl;


import com.jt.repository.wrapper.CategoryWrapper;
import com.jt.vo.CategoryVO;
import org.hibernate.Session;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class CategoryRepositoryImpl implements CategoryWrapper {

    @PersistenceContext
    private EntityManager em;


    private Session getSession(){
        return em.unwrap(Session.class);
    }


    @Override
    public List<CategoryVO> findAllDetail() {
        String sql = "SELECT c.* ,count(a.category_id) as articles "
                + "FROM me_category c "
                + "LEFT join me_article a on a.article_id = c.id "
                + "GROUP BY c.id ";

        SQLQuery query = getSession().createSQLQuery(sql);

        query.addScalar("id");
        query.addScalar("categoryName");
        query.addScalar("description");
        query.addScalar("avatar");
        query.addScalar("articles", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(CategoryVO.class));

        return query.list();




//        return List.of();
    }

    @Override
    public CategoryVO getCategoryDetail(Integer categoryId) {

        String sql = "SELECT c.*,count(a.category_id) as articles "
                + "FROM me_category c "
                + "LEFT join me_article a on a.article_id = c.id "
                + "where c.id =:categoryId";

        SQLQuery query = getSession().createSQLQuery(sql);

        query.setInteger("categoryId", categoryId);

        query.addScalar("id");
        query.addScalar("categoryName");
        query.addScalar("description");
        query.addScalar("avatar");
        query.addScalar("articles", IntegerType.INSTANCE);


        query.setResultTransformer(Transformers.aliasToBean(CategoryVO.class));

        return (CategoryVO) query.uniqueResult();

//        return null;
    }
}
