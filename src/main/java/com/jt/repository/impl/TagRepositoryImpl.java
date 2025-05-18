package com.jt.repository.impl;


import com.jt.repository.wrapper.TagWrapper;
import com.jt.vo.TagVO;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TagRepositoryImpl implements TagWrapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TagVO> findAllDetail(){

        String sql = "SELECT t.* ,count(at.tag_id) as acticles from me_article_tag at"
                + "right join me_tag t on at.tag_id = t.id"
                + "group by t.id";

        SQLQuery query = getSession().createSQLQuery(sql);

        query.addScalar("id");
        query.addScalar("tagName");
        query.addScalar("avatar");
        query.addScalar("articles", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(TagVO.class));

//        List<TagVO> resultList = (List<TagVO>) query.list();

        return query.list();

//        return resultList;

//        return null;
    }




    @Override
    public TagVO getTagDetail(Integer tagId) {

        String sql = "SELECT t.* ,count(at.tag_id) as articles from me_article_tag at "
                + "right join me_tag t on at.tag_id = t.id "
                + "where t.id = :tagId";

        SQLQuery query = getSession().createSQLQuery(sql);

        query.setInteger("tagId",tagId);

        query.addScalar("id");
        query.addScalar("tagName");
        query.addScalar("avatar");
        query.addScalar("articles", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(TagVO.class));

        return (TagVO)query.uniqueResult();



//        return null;
    }


    private Session getSession(){
        return em.unwrap(Session.class);
    }


}
