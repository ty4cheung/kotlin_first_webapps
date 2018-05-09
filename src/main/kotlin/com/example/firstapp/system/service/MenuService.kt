package com.example.firstapp.system.service

import com.example.firstapp.system.dao.MenuDao
import com.example.firstapp.system.dao.RoleMenuDao
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true, rollbackFor = arrayOf(Exception::class))
class MenuService{

    @Autowired
    var menuDao: MenuDao? = null;

    @Autowired
    var roleMenuDao: RoleMenuDao? = null

    fun listPerms(userId: Long?): Set<String> {
        val perms = menuDao!!.listUserPerms(userId!!)
        val permsSet = HashSet<String>()
        for (perm in perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(*perm.trim({ it <= ' ' }).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()))
            }
        }
        return permsSet
    }
}