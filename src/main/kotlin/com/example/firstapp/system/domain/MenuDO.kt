package com.example.firstapp.system.domain

import java.io.Serializable
import java.util.*

class MenuDO :Serializable{
    companion object {
        private val serialVersionUid: Long = 1
    }
    var menuId: Long? = null
    // 父菜单ID，一级菜单为0
    var parentId: Long? = null
    // 菜单名称
    var name: String? = null
    // 菜单URL
    var url: String? = null
    // 授权(多个用逗号分隔，如：user:list,user:create)
    var perms: String? = null
    // 类型 0：目录 1：菜单 2：按钮
    var type: Int? = null
    // 菜单图标
    var icon: String? = null
    // 排序
    var orderNum: Int? = null
    // 创建时间
    var gmtCreate: Date? = null
    // 修改时间
    var gmtModified: Date? = null
}