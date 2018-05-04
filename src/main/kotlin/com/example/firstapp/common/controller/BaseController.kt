package com.example.firstapp.common.controller

import com.example.firstapp.common.utils.ShiroUtils
import com.example.firstapp.system.domain.UserDO



open class BaseController {

    fun getUser(): UserDO {
        return ShiroUtils.getUser()
    }

    fun getUserId(): Long? {
        return getUser().userId
    }

    fun getUsername(): String {
        return getUser().username!!
    }
}