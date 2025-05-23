package com.jt.oauth;


import com.jt.common.cache.RedisManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

//把session保存到redis
public class OAuthSessionDAO extends CachingSessionDAO implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(OAuthSessionDAO.class);

    private RedisManager redisManager;

    @Override
    protected Serializable doCreate(Session session){
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);

        logger.info(sessionId.toString(),session,RedisManager.DEFAULT_EXPIRE);

        return sessionId;
    }


    @Override
    protected void doUpdate(Session session){
        if(session instanceof ValidatingSession && !( (ValidatingSession) session ).isValid()){
            return ;
        }

        logger.info(session.getId().toString());

        redisManager.set(session.getId().toString(),session,RedisManager.DEFAULT_EXPIRE);

    }

    @Override
    protected void doDelete(Session session){
        redisManager.delete(session.getId().toString());
    }

    @Override
    protected Session doReadSession(Serializable sessionId){
        logger.info(sessionId.toString());

        return redisManager.get(sessionId.toString(),Session.class);


    }

    public RedisManager getRedisManager(){
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager){
        this.redisManager = redisManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(null == this.redisManager){
            logger.error("StringRedisTemplate should be not null");
        }
    }



}
