package com.bootdo.system.config

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect
import com.example.firstapp.common.config.Constant
import com.example.firstapp.common.redis.shiro.RedisManager
import com.example.firstapp.system.shiro.UserRealm
import org.apache.shiro.cache.ehcache.EhCacheManager
import org.apache.shiro.mgt.SecurityManager
import org.apache.shiro.session.SessionListener
import org.apache.shiro.session.mgt.eis.MemorySessionDAO
import org.apache.shiro.session.mgt.eis.SessionDAO
import org.apache.shiro.spring.LifecycleBeanPostProcessor
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import java.util.ArrayList
import java.util.LinkedHashMap

/**
 * @author bootdo 1992lcg@163.com
 */
@Configuration
class ShiroConfig {
    @Value("\${spring.redis.host}")
    private val host: String? = null
    @Value("\${spring.redis.password}")
    private val password: String? = null
    @Value("\${spring.redis.port}")
    private val port: Int = 0
    @Value("\${spring.redis.timeout}")
    private val timeout: Int = 0

    @Value("\${cacheType}")
    private val cacheType: String? = null

    @Value("\${server.session-timeout}")
    private val tomcatTimeout: Int = 0

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean
    fun shiroDialect(): ShiroDialect {
        return ShiroDialect()
    }

    @Bean
    internal fun shiroFilterFactoryBean(securityManager: SecurityManager): ShiroFilterFactoryBean {
        val shiroFilterFactoryBean = ShiroFilterFactoryBean()
        shiroFilterFactoryBean.securityManager = securityManager
        shiroFilterFactoryBean.loginUrl = "/login"
        shiroFilterFactoryBean.successUrl = "/index"
        shiroFilterFactoryBean.unauthorizedUrl = "/403"
        val filterChainDefinitionMap = LinkedHashMap<String, String>()
        filterChainDefinitionMap["/css/**"] = "anon"
        filterChainDefinitionMap["/js/**"] = "anon"
        filterChainDefinitionMap["/fonts/**"] = "anon"
        filterChainDefinitionMap["/img/**"] = "anon"
        filterChainDefinitionMap["/docs/**"] = "anon"
        filterChainDefinitionMap["/druid/**"] = "anon"
        filterChainDefinitionMap["/upload/**"] = "anon"
        filterChainDefinitionMap["/files/**"] = "anon"
        filterChainDefinitionMap["/logout"] = "logout"
        filterChainDefinitionMap["/"] = "anon"
        filterChainDefinitionMap["/blog"] = "anon"
        filterChainDefinitionMap["/blog/open/**"] = "anon"
        filterChainDefinitionMap["/**"] = "authc"
        shiroFilterFactoryBean.filterChainDefinitionMap = filterChainDefinitionMap
        return shiroFilterFactoryBean
    }


    @Bean
    fun securityManager(): SecurityManager {
        val securityManager = DefaultWebSecurityManager()
        //设置realm.
        securityManager.setRealm(userRealm())
        // 自定义缓存实现 使用redis
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            securityManager.cacheManager = cacheManager()
        } else {
            securityManager.cacheManager = ehCacheManager()
        }
        securityManager.sessionManager = sessionManager()
        return securityManager
    }

    @Bean
    internal fun userRealm(): UserRealm {
        return UserRealm()
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    fun authorizationAttributeSourceAdvisor(securityManager: SecurityManager): AuthorizationAttributeSourceAdvisor {
        val authorizationAttributeSourceAdvisor = AuthorizationAttributeSourceAdvisor()
        authorizationAttributeSourceAdvisor.securityManager = securityManager
        return authorizationAttributeSourceAdvisor
    }

    /**
     * 配置shiro redisManager
     * @return
     */
    @Bean
    fun redisManager(): RedisManager {
        val redisManager = RedisManager()
        redisManager.setHost(host)
        redisManager.setPort(port)
        redisManager.setExpire(1800)// 配置缓存过期时间
        //redisManager.setTimeout(1800);
        redisManager.setPassword(password)
        return redisManager
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    fun cacheManager(): RedisCacheManager {
        val redisCacheManager = RedisCacheManager()
        redisCacheManager.setRedisManager(redisManager())
        return redisCacheManager
    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    fun redisSessionDAO(): RedisSessionDAO {
        val redisSessionDAO = RedisSessionDAO()
        redisSessionDAO.setRedisManager(redisManager())
        return redisSessionDAO
    }

    @Bean
    fun sessionDAO(): SessionDAO {
        return if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            redisSessionDAO()
        } else {
            MemorySessionDAO()
        }
    }

    /**
     * shiro session的管理
     */
    @Bean
    fun sessionManager(): DefaultWebSessionManager {
        val sessionManager = DefaultWebSessionManager()
        sessionManager.globalSessionTimeout = (tomcatTimeout * 1000).toLong()
        sessionManager.sessionDAO = sessionDAO()
        val listeners = ArrayList<SessionListener>()
        listeners.add(BDSessionListener())
        sessionManager.sessionListeners = listeners
        return sessionManager
    }

    @Bean
    fun ehCacheManager(): EhCacheManager {
        val em = EhCacheManager()
        em.cacheManagerConfigFile = "classpath:config/ehcache.xml"
        return em
    }

    companion object {

        val lifecycleBeanPostProcessor: LifecycleBeanPostProcessor
            @Bean
            get() = LifecycleBeanPostProcessor()
    }

}
