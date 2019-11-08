package com.example.project.demo.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: lcx
 * @Date: 2018/12/3 20:56
 * @Description: shiro配置
 */
@Configuration
public class ShiroConfig {

    public MyHashedCredentialsMatcher hashedCredentialsMatcher() {
        MyHashedCredentialsMatcher hashedCredentialsMatcher = new MyHashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(7);
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(
//            RedisCacheManager redisCacheManager,
            MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm);
//        securityManager.setCacheManager(redisCacheManager);
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        sessionManager.setGlobalSessionTimeout(8 * 60 * 60 * 1000);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/api/unLogin");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/api/login", "anon");
        filterChainDefinitionMap.put("/api/ssoLogin", "anon");
        filterChainDefinitionMap.put("/api/oa/downloadCFile", "anon");
        filterChainDefinitionMap.put("/api/oa/reciver", "anon");
        filterChainDefinitionMap.put("/api/es/reIndex", "anon");
        filterChainDefinitionMap.put("/api/template/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
//      filterChainDefinitionMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

//    /**
//     * 如果用到权限验证相关注解，则需做此配置
//     *
//     * @return
//     */
//    @Bean
//    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    /**
//     * 如果用到权限验证相关注解，则需做此配置
//     *
//     * @return
//     */
//    @Bean
//    @DependsOn({"getLifecycleBeanPostProcessor"})
//    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
//        daap.setProxyTargetClass(true);
//        return daap;
//    }

//    /**
//     * 注册全局异常处理
//     * @return
//     */
//    @Bean(name = "simpleMappingExceptionResolver")
//    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
//        Properties mappings = new Properties();
//        mappings.setProperty("DatabaseException", "/api/error/unknown");
//        mappings.setProperty("UnauthorizedException", "/api/error/unauthorized");
//        mappings.setProperty("UnauthenticatedException","/api/error/unauthenticated");
//        r.setExceptionMappings(mappings);
//        r.setDefaultErrorView("/api/error");
//        return r;
//    }


}
