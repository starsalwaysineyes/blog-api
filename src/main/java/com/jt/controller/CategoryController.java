package com.jt.controller;


import com.jt.common.annotation.LogAnnotation;
import com.jt.common.constant.Base;
import com.jt.common.constant.ResultCode;
import com.jt.common.result.Result;
import com.jt.entity.Category;
import com.jt.service.CategoryService;
import com.jt.vo.CategoryVO;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    @LogAnnotation(module = "category",operation = "get all category")
    public Result listCategory(){
        Result r = new Result();
        List<Category> categories = categoryService.findAll();

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(categories);
        return r;
    }

    @GetMapping("detail")
    @LogAnnotation(module = "category",operation = "get all category detail")
    public Result listCategoryDetail(){
        List<CategoryVO> categoryVOS = categoryService.findAllDetail();

        return Result.success(categoryVOS);
    }

    @GetMapping("/{id}")
    @LogAnnotation(module = "category",operation = "get category by id")
    public Result getCategoryById(@PathVariable("id") Integer id){
        Result r = new Result();

        if(null == id){
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        Category category = categoryService.getCategoryById(id);

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(category);

        return r;
    }

    @GetMapping("/detail/{id}")
    @LogAnnotation(module = "category",operation = "get category detail by id")
    public Result getCategoryDetail(@PathVariable("id") Integer id){
        Result r = new Result();

        if(null == id){
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        CategoryVO categoryVO = categoryService.getCategoryDetail(id);

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(categoryVO);
        return r;

    }


    @PostMapping("/create")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "category",operation = "create category")
    public Result saveCategory(@Validated @RequestBody Category category){
        Integer categoryId = categoryService.saveCategory(category);

        Result r = new Result();
        r.setResultCode(ResultCode.SUCCESS);
        r.simple().put("categoryId", categoryId);
        return r;

    }

    @PostMapping("/update")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "category",operation = "update category")
    public Result updateCategory(@RequestBody Category category){

        Result r = new Result();

        if(null == category.getId()){
            r.setResultCode(ResultCode.USER_NOT_EXIST);
            return r;
        }

        Integer categoryId = categoryService.updateCategory(category);
        r.setResultCode(ResultCode.SUCCESS);
        r.simple().put("categoryId", categoryId);
        return r;


    }

    @GetMapping("/delete/{id}")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "category",operation = "delete category")
    public Result deleteCategoryById(@PathVariable("id") Integer id){
        Result r = new Result();

        if(null == id){
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        categoryService.deleteCategory(id);
        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }



}
