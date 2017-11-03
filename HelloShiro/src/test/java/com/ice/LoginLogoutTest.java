package com.ice;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginLogoutTest {

    public  static Logger logger = LoggerFactory.getLogger(LoginLogoutTest.class);

    @Test
    public void testHelloWorld(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang","1023");
        try {
            subject.login(token);
        }catch (AuthenticationException e){//验证失败
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        //断言
        assertEquals(true,subject.isAuthenticated());
        //退出
        subject.logout();
    }
}
