package com.example.firstapp.system.domain

import lombok.Data
import java.io.Serializable
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

@Data
class UserDO : Serializable {

    companion object {
        private val serialVersionUid: Long = 1
    }
    //
    val userId: Long? = null
    // 用户名
    val username: String? = null
    // 用户真实姓名
    val name: String? = null
    // 密码
    val password: String? = null
    // 部门
    val deptId: Long? = null
    val deptName: String? = null
    // 邮箱
    val email: String? = null
    // 手机号
    val mobile: String? = null
    // 状态 0:禁用，1:正常
    val status: Int? = null
    // 创建用户id
    private val userIdCreate: Long? = null
    // 创建时间
    private val gmtCreate: Date? = null
    // 修改时间
    private val gmtModified: Date? = null
    //角色
    private val roleIds: List<Long>? = null
    //性别
    private val sex: Long? = null
    //出身日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private val birth: Date? = null
    //图片ID
    private val picId: Long? = null
    //现居住地
    private val liveAddress: String? = null
    //爱好
    private val hobby: String? = null
    //省份
    private val province: String? = null
    //所在城市
    private val city: String? = null
    //所在地区
    private val district: String? = null

}