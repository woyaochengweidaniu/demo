package com.example.project.demo.config.shiro;



import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;




/**
 * @Author: lcx
 * @Date: 2018/12/3 21:01
 * @Description:
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Session session = SecurityUtils.getSubject().getSession();

        return authorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*
         * 身份认证：
         * 当调用
         *     Subject user = SecurityUtils.getSubject();
         *     user.login(usernamePasswordToken);
         *     就开始进入到这个方法里
         *
         * usernamePasswordToken  ：就是该方法的接受值
         */
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        //然后根据用户名查出用户
        User user = null;

        if (user!=null){
            //第一个参数主题标识   建议：如果
            // 第二个参数需要对比的密钥，
            // 第三个参数是使用加密的盐，
            // 第五个参数目前未知
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("", "",
                    ByteSource.Util.bytes("".concat("")), getName());
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("USER_SESSION", null);
            return authenticationInfo;
        }else {
            return null;
        }







    }

}
