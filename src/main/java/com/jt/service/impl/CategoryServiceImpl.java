package com.jt.service.impl;

import com.jt.entity.Category;
import com.jt.repository.CategoryRepository;
import com.jt.service.CategoryService;
import com.jt.vo.CategoryVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
//        return List.of();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
//        return null;
    }

    @Override
    @Transactional
    public Integer saveCategory(Category category) {
        return categoryRepository.save(category).getId();
//        return 0;
    }

    @Override
    @Transactional
    public Integer updateCategory(Category category) {
        Category oldCategory = categoryRepository.findById(category.getId()).orElse(null);
        
        if (oldCategory != null) {
            oldCategory.setCategoryName(category.getCategoryName());
            oldCategory.setDescription(category.getDescription());
            oldCategory.setAvatar(category.getAvatar());

            categoryRepository.save(oldCategory);
    //        return categoryRepository.save(category).getId();

            return oldCategory.getId();
        }
//        return 0;
        return null;
    }

    @Override
    @Transactional
    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.delete(category);
        }
    }

    @Override
    public List<CategoryVO> findAllDetail() {
        return categoryRepository.findAllDetail();
//        return List.of();
    }

    @Override
    public CategoryVO getCategoryDetail(Integer categoryId) {
       return categoryRepository.getCategoryDetail(categoryId);
    }



}
