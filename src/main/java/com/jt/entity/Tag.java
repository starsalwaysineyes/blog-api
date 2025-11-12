package com.jt.entity;

import com.jt.common.entity.BaseEntity;
import javax.validation.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "me_tag")
public class Tag extends BaseEntity<Integer> {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String tagName;

    @NotBlank
    private String avatar;


    public String getTagName(){
        return tagName;
    }

    public void setTagName(String tagName){
        this.tagName = tagName;
    }

    public String getAvatar(){
        return avatar;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }


}
