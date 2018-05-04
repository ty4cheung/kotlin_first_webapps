package com.example.firstapp.blog.controller

import com.example.firstapp.blog.service.impl.ContentService
import com.example.firstapp.common.utils.PageUtils
import com.example.firstapp.common.utils.Query
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.beans.factory.annotation.Autowired
import com.example.firstapp.common.utils.DateUtils
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import com.example.firstapp.blog.domain.ContentDO
import java.util.HashMap



@RequestMapping("/blog")
@Controller
class BlogController {
    @Autowired
    var bContentService: ContentService? = null

    @GetMapping()
    fun blog(): String {
        return "blog/index/main"
    }

    @ResponseBody
    @GetMapping("/open/list")
    fun opentList(@RequestParam params: Map<String, Any>): PageUtils {
        val query = Query(params)
        val bContentList = bContentService?.list(query)
        val total = bContentService?.count(query)
        return PageUtils(bContentList, total!!)
    }

    @GetMapping("/open/post/{cid}")
    fun post(@PathVariable("cid") cid: Long, model: Model): String {
        val bContentDO = bContentService!!.get(cid)
        model.addAttribute("bContent", bContentDO)
        model.addAttribute("gtmModified", DateUtils.format(bContentDO.gtmModified!!))
        return "blog/index/post"
    }

    @GetMapping("/open/page/{categories}")
    fun about(@PathVariable("categories") categories: String, model: Model): String {
        val map = HashMap<String, Any>(16)
        map["categories"] = categories
        var bContentDO: ContentDO? = null
        if (bContentService?.list(map)!!.size > 0) {
            bContentDO = bContentService!!.list(map)[0]
        }
        model.addAttribute("bContent", bContentDO!!)
        return "blog/index/post"
    }
}