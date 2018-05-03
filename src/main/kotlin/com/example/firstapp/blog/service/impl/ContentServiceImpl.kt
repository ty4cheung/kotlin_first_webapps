package com.example.firstapp.blog.service.impl

import com.example.firstapp.blog.domain.ContentDO
import com.example.firstapp.blog.dao.ContentDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ContentServiceImpl : ContentService {


    @Autowired
    private val bContentMapper: ContentDao? = null

    override fun remove(cid: Long): Int {
      return bContentMapper?.remove(cid)!!
    }

    override fun batchRemove(cids: Array<Long>): Int? {
      return bContentMapper?.batchRemove(cids);
    }

    override fun get(cid: Long): ContentDO {
        return bContentMapper?.get(cid)!!;
    }

    override fun list(map: Map<String, Any>): List<ContentDO> {
        return bContentMapper!!.list(map)
    }

    override fun count(map: Map<String, Any>): Int {
        return bContentMapper!!.count(map)
    }

    override fun save(bContent: ContentDO): Int {
        return bContentMapper!!.save(bContent)
    }

    override fun update(bContent: ContentDO): Int {
        return bContentMapper!!.update(bContent)
    }


}