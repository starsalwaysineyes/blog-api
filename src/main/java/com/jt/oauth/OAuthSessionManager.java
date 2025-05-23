package com.jt.oauth;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class OAuthSessionManager extends DefaultWebSessionManager {

    public static final String OAUTH_TOKEN = "Oauth-Token";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public OAuthSessionManager(){
        super();
    }


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse reponse){
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String id = httpRequest.getHeader(OAUTH_TOKEN);

        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,REFERENCED_SESSION_ID_SOURCE);

        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,id);

        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);

        return id;



    }


}
