package com.jt.entity;

import com.jt.common.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "me_category")
public class Category extends BaseEntity<Integer> {




    private static final long serialVersionUID = 1L;

    @NotBlank
    private String categoryName;

    private String description;

    @NotBlank
    private String avatar;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }













}
