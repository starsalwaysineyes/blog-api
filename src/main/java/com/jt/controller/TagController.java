package com.jt.controller;


import com.jt.common.annotation.LogAnnotation;
import com.jt.common.constant.Base;
import com.jt.common.constant.ResultCode;
import com.jt.common.result.Result;
import com.jt.entity.Tag;
import com.jt.service.TagService;
import com.jt.vo.TagVO;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tags")
public class TagController {



    @Autowired
    private TagService tagService;

    @GetMapping
    @LogAnnotation(module = "tag",operation ="get all tags")
    public Result listTags(){

        List<Tag> tags = tagService.findAll();

        return Result.success(tags);


    }


    @GetMapping("detail")
    @LogAnnotation(module = "tag",operation = "get all tags details")
    public Result listCategoryDetial(){
        List<TagVO> categorys = tagService.findAllDetail();

        return Result.success(categorys);
    }


    @GetMapping("/hot")
    @LogAnnotation(module = "tag",operation = "get hot tag")
    public Result listHotTags(){
        int limit =6;
        List<Tag> tags = tagService.listHotTags(limit);

        return Result.success(tags);

    }

    @GetMapping("/{id}")
    @LogAnnotation(module = "tag",operation = "get tag by id")
    public Result getTagById(@PathVariable("id") Integer id){
        Result r = new Result();

        if(null == id){
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        Tag tag = tagService.getTagById(id);

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(tag);
        return r;


    }


    @GetMapping("/detial/{id}")
    @LogAnnotation(module = "tag",operation = "get tag detail by id")
    public Result getTagDetail(@PathVariable("id") Integer id){
        Result r = new Result();
        if(null == id){
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        TagVO tagVO = tagService.getTagDetail(id);
        r.setResultCode(ResultCode.SUCCESS);
        r.setData(tagVO);
        return r;

    }

    @PostMapping("/create")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "tag",operation = "create tag")
    public Result saveTag(@Validated @RequestBody Tag tag){
        Result r = new Result();

        Integer tagId = tagService.saveTag(tag);

        r.setResultCode(ResultCode.SUCCESS);
        r.simple().put("tagId",tagId);

        return r;

    }




    @PostMapping("/update")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "tag",operation = "update tag")
    public Result updateTag(@RequestBody Tag tag){
        Result r = new Result();

        if(null == tag.getId()){
            r.setResultCode(ResultCode.USER_NOT_EXIST);
            return r;
        }

        Integer tagId = tagService.updateTag(tag);

        r.setResultCode(ResultCode.SUCCESS);
        r.simple().put("tagId",tagId);

        return r;

    }


    @GetMapping("/delete/{id}")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "tag",operation = "delete tag by id")
    public Result deleteTagById(@PathVariable("id") Integer id){
        Result r = new Result();

        if(null == id){
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        tagService.deleteTagById(id);

        r.setResultCode(ResultCode.SUCCESS);
        return r;

    }




}
