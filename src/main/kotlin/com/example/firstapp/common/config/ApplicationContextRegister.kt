package com.example.firstapp.common.config

import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import lombok.extern.slf4j.Slf4j
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
@Slf4j
class ApplicationContextRegister: ApplicationContextAware {
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        APPLICATION_CONTEXT = applicationContext
    }

    companion object {
        var APPLICATION_CONTEXT: ApplicationContext? = null
        /**
         * 获取容器
         * @return
         */
        @JvmStatic
        fun getApplicationContext(): ApplicationContext {
            return this.APPLICATION_CONTEXT!!
        }

        /**
         * 获取容器对象
         * @param type
         * @param <T>
         * @return
        </T> */
        @JvmStatic
        fun <T> getBean(type: Class<T>): T {
            return APPLICATION_CONTEXT!!.getBean(type)
        }
    }


}