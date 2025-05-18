package com.jt.oauth;

import com.jt.entity.User;
import com.jt.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.subject.PrincipalCollection;
import com.jt.common.constant.Base;
import java.util.HashSet;
import java.util.Set;
import com.jt.entity.UserStatus;

public class OAuthRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        String account = (String) principals.getPrimaryPrincipal();

        User user = userService.getUserByAccount(account);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Set<String> roles = new HashSet<String>();

        if(user.getAdmin()){
            roles.add(Base.ROLE_ADMIN);
        }

        authorizationInfo.addRoles(roles);

        return authorizationInfo ;



    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String account = (String) token.getPrincipal();

        User user = userService.getUserByAccount(account);

        if(null == user){

            throw new UnknownAccountException();
        }

        if(UserStatus.blocked.equals(user.getStatus())){
            throw new LockedAccountException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getAccount(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );

        return authenticationInfo;




    }


}
