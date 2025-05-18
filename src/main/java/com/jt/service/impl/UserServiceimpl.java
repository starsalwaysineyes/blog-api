package com.jt.service.impl;

import com.jt.entity.User;
import com.jt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

import com.jt.common.util.PasswordHelper;
import com.jt.service.UserService;

public class UserServiceimpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }


    @Override
    public User getUserByAccount(String account){
        return userRepository.findByAccount(account);
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public Long saveUser(User user) {
        PasswordHelper passwordHelper = new PasswordHelper();

        int index = new Random().nextInt(6)+1;
        String avatar = "/static/user/user_"+index+".png";

        user.setAvatar(avatar);

        return userRepository.save(user).getId();


    }


    @Override
    @Transactional
    public Long updateUser(User user) {
        User oldUser = userRepository.findOne(user.getId());

        oldUser.setNickname(user.getNickname());

        return oldUser.getId();



    }

    @Override
    @Transactional
    public void deleteUserById(Long id){
        userRepository.delete(id);
    }





}
