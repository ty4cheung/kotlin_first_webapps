package com.example.firstapp.common.utils

import com.example.firstapp.system.domain.UserDO
import org.apache.shiro.session.mgt.eis.SessionDAO
import org.springframework.beans.factory.annotation.Autowired
import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import java.security.Principal


object ShiroUtils {

    @Autowired
    private val sessionDAO: SessionDAO? = null

    fun getSubjct(): Subject {
        return SecurityUtils.getSubject()
    }

    fun getUser(): UserDO {
        val `object` = getSubjct().getPrincipal()
        return `object` as UserDO
    }

    fun getUserId(): Long? {
        return getUser().userId
    }

    fun logout() {
        getSubjct().logout()
    }

    fun getPrinciples(): List<Principal>? {
        val principals: List<Principal>? = null
        val sessions = sessionDAO!!.getActiveSessions()
        return principals
    }
}