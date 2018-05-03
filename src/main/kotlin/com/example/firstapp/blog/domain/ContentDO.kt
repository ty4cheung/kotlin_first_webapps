package com.example.firstapp.blog.domain

import java.io.Serializable
import java.util.*


class ContentDO : Serializable {

    var cid: Long? = null
    //标题
    var title: String? = null

    var slug: String? = null
    //创建人id
    var created: Long? = null
    //最近修改人id
    var modified: Long? = null
    //内容
    var content: String? = null
    //类型
    var type: String? = null
    //标签
    var tags: String? = null
    //分类
    var categories: String? = null

    var hits: Int? = null
    //评论数量
    var commentsNum: Int? = null
    //开启评论
    var allowComment: Int? = null
    //允许ping
    var allowPing: Int? = null
    //允许反馈
    var allowFeed: Int? = null
    //状态
    var status: Int? = null
    //作者
    var author: String? = null
    //创建时间
    var gtmCreate: Date? = null
    //修改时间
    var gtmModified: Date? = null

    override fun toString(): String {
        return "ContentDO{" +
                "cid=" + cid +
                ", title='" + title + '\''.toString() +
                ", slug='" + slug + '\''.toString() +
                ", created=" + created +
                ", modified=" + modified +
                ", content='" + content + '\''.toString() +
                ", type='" + type + '\''.toString() +
                ", tags='" + tags + '\''.toString() +
                ", categories='" + categories + '\''.toString() +
                ", hits=" + hits +
                ", commentsNum=" + commentsNum +
                ", allowComment=" + allowComment +
                ", allowPing=" + allowPing +
                ", allowFeed=" + allowFeed +
                ", status=" + status +
                ", author='" + author + '\''.toString() +
                ", gtmCreate=" + gtmCreate +
                ", gtmModified=" + gtmModified +
                '}'.toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}