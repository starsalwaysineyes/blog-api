package com.jt.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.jt.common.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sys_user")
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Column(name = "account",unique = true,length = 10)
    private String account;


    @NotBlank
    @Column(name = "password",length = 64)
    private String password ;

    private String avatar;

    @Column(name = "email" , unique = true , length = 20)
    private String email;

    @NotBlank
    @Column(name = "nickname",length = 10)
    private String nickname ;

    @Column(name = "mobile_phone_number",length = 20)
    private String mobilePhoneNumber;

    //加密时用的种子
    private String salt;



    //创建时间
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    //最后一次登录时间
    @JSONField(format = "yyyy-mm-dd HH:mm:ss")
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;


    //系统用户状态
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.normal;


    //是否是管理员
    private Boolean admin = false;

    //逻辑删除flag
    public Boolean deleted = Boolean.FALSE;

    public String getAccount(){
        return account;
    }

    public void setAccount(String account){
        this.account = account;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getAvatar(){
        return avatar;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public String getMobilePhoneNumber(){
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber){
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getSalt(){
        return salt;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public Date getCreateDate(){
        return createDate;
    }

    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public Date getLastLogin(){
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin){
        this.lastLogin = lastLogin;
    }

    public UserStatus getStatus(){
        return status;
    }
    public void setStatus(UserStatus status){
        this.status = status;
    }

    public Boolean getAdmin(){
        return admin;
    }
    public void setAdmin(Boolean admin){
        this.admin = admin;
    }

    public Boolean getDeleted(){
        return deleted;
    }
    public void setDeleted(Boolean deleted){
        this.deleted = deleted;
    }

    @Override
    public String toString(){
        return "User [account=" + account + ", password=" + password + ", avatar=" + avatar
                + ", email=" + email + ", nickname=" + nickname + ", mobilePhoneNumber=" + mobilePhoneNumber
                + ", salt=" + salt + ", createDate=" + createDate + ", lastLogin=" + lastLogin
                + ", status=" + status + ", admin=" + admin + ", deleted=" + deleted + "]";
    }




}
