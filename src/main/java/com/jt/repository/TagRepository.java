package com.jt.repository;

import com.jt.entity.Tag;
import com.jt.repository.wrapper.TagWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> , TagWrapper {

    @Query(value = "SELECT t.*,count(at.tag_id) as coutn from me_article_tag at"
        + "right join me_tag t on t.id = at.tag_id "
        + "group by t.id order by count(at.tag_id) desc limit :limit",nativeQuery = true)
    List<Tag> listHotTagsByArticleUser(@Param("limit") int limit);




}
