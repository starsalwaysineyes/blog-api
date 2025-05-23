package com.jt.config;


import com.jt.common.cache.RedisManager;
import com.jt.oauth.OAuthRealm;
import com.jt.oauth.OAuthSessionDAO;
import com.jt.oauth.OAuthSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        filterChainDefinitionMap.put("/**","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;

    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher  = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");

        hashedCredentialsMatcher.setHashIterations(2);

        return hashedCredentialsMatcher;

    }

    @Bean
    public OAuthRealm oAuthRealm(){
        OAuthRealm oAuthRealm = new OAuthRealm();

        oAuthRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        return oAuthRealm;

    }

    @Bean
    public SecurityManager securityManager(OAuthRealm oAuthRealm, SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuthRealm);

        securityManager.setSessionManager(sessionManager);

        return securityManager;

    }



    @Bean
    public SessionManager sessionManager(OAuthSessionDAO authSessionDAO){
        OAuthSessionManager oAuthSessionManager = new OAuthSessionManager();

        oAuthSessionManager.setSessionDAO(authSessionDAO);

        return oAuthSessionManager;

    }

    @Bean
    public OAuthSessionDAO authSessionDAO(RedisManager redisManager){
        OAuthSessionDAO authSessionDAO = new OAuthSessionDAO();
        authSessionDAO.setRedisManager(redisManager);
        return authSessionDAO;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }



}
