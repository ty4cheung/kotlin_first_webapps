package com.example.firstapp.common.utils


import java.io.Serializable

/**
 * @Author bootdo 1992lcg@163.com
 */
class PageUtils(var rows: List<*>?, var total: Int) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }

}
