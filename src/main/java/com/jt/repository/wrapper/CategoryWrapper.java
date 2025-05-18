package com.jt.repository.wrapper;

import java.util.List;

import com.jt.vo.CategoryVO;

public interface CategoryWrapper{

    List<CategoryVO> findAllDetail();

    CategoryVO getCategoryDetail(Integer categoryId);


}
