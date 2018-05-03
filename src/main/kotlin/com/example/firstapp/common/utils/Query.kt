package com.example.firstapp.common.utils

import java.util.LinkedHashMap


class Query(params: Map<String, Any>) : LinkedHashMap<String, Any>() {
    //
    private val offset: Int
    // 每页条数
    var limit: Int = 0

    init {
        this.putAll(params)
        // 分页参数
        this.offset = Integer.parseInt(params["offset"].toString())
        this.limit = Integer.parseInt(params["limit"].toString())
        this["offset"] = offset
        this["page"] = offset / limit + 1
        this["limit"] = limit
    }

    fun getOffset(): Int {
        return offset
    }

    fun setOffset(offset: Int) {
        this["offset"] = offset
    }

    companion object {
        private val serialVersionUID = 1L
    }
}
