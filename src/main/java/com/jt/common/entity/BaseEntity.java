package com.jt.common.entity;


import com.sun.mail.imap.protocol.ID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;

    public ID getId(){
        return id;
    }

    public void setId(ID id){
        this.id = id;
    }

    @Override
    public boolean equals(Object obj){
        if(null == obj){
            return false;
        }

        if(this == obj){
            return true;
        }

        if(!getClass().equals(obj.getClass())){
            return false;
        }

        BaseEntity that = (BaseEntity)obj;

        return null == this.getId() ? false: this.getId().equals(that.getId());



    }

    @Override
    public int hashCode(){
        int hashCode = 37;
        hashCode += null == this.getId() ? 0 : this.getId().hashCode()*31;

        return hashCode ;
    }



}
