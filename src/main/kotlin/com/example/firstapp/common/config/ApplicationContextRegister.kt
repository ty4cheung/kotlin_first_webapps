package com.example.firstapp.common.config

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class ApplicationContextRegister: ApplicationContextAware {
    private val logger = LoggerFactory.getLogger(ApplicationContextRegister::class.java)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        logger.debug("ApplicationContext registed-->{}", applicationContext)
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