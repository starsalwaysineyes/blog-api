package com.jt.service.impl;

import com.jt.entity.Tag;
import com.jt.repository.TagRepository;
import com.jt.service.TagService;
import com.jt.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {


    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Integer saveTag(Tag tag) {

        return tagRepository.save(tag).getId();


//        return 0;
    }

    @Override
    @Transactional
    public Integer updateTag(Tag tag) {
        Tag oldTag = tagRepository.findById(tag.getId()).orElse(null);

        oldTag.setTagName(tag.getTagName());
        oldTag.setAvatar(tag.getAvatar());

        return oldTag.getId();

//        return 0;
    }

    @Override
    @Transactional
    public void deleteTagById(Integer id) {
        Tag tag = tagRepository.findById(id).orElse(null);
        if (tag != null) {
            tagRepository.delete(tag);
        }
    }

    @Override
    public List<Tag> listHotTags(int limit) {
        return tagRepository.listHotTagsByArticleUser(limit);
    }

    @Override
    public List<TagVO> findAllDetail() {
        return tagRepository.findAllDetail();
    }

    @Override
    public TagVO getTagDetail(Integer id) {
        return tagRepository.getTagDetail(id);
    }
}
