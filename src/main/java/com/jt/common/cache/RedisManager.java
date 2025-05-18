package com.jt.common.cache;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/*
* redis相关管理项
*
* */
public class RedisManager {


    //默认过期时长，单位秒
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24 * 7 ;

    //不设置过期时长
    public final static long NOT_EXPIRE = -1 ;

    private RedisTemplate<String, Object> redisTemplate ;

    public void set(String key,Object value , long expire) {
        try{
            if(expire == NOT_EXPIRE){
                redisTemplate.opsForValue().set(key,value);
            }
            else{
                redisTemplate.opsForValue().set(key,value,expire, TimeUnit.SECONDS);
            }
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }

    public void set(String key,Object value){
        set(key,value,DEFAULT_EXPIRE);
    }


    public <T> T get(String key,Class<T> clazz){
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();

        Object value = operations.get(key);

        if( value == null){
            return null;
        }

        if(clazz.isInstance(value)){
            return clazz.cast(value);
        }
        else {
            System.out.println("Warning: Value retrieved for key '" + key + "' is of type "+ value.getClass().getName() + ",not assinable to "+ clazz.getName());
            return null;
        }

        //return null


    }



    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public RedisTemplate<String,Object> getRedisTemplate(){
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

}














