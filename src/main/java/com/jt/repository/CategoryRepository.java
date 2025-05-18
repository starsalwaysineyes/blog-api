package com.jt.repository;

import com.jt.entity.Category;
import com.jt.repository.wrapper.CategoryWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> , CategoryWrapper {






}
