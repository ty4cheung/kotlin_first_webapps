package com.example.firstapp.blog.dao

import com.example.firstapp.blog.domain.ContentDO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ContentDao {

	fun get(cid:Long):ContentDO

	fun list(map: Map<String,Any>):List<ContentDO>

	fun count(map:Map<String,Any>):Int;

	fun save(contentDo:ContentDO):Int;

	fun update(contentDo: ContentDO):Int;

	fun remove(cid: Long):Int

	fun batchRemove(cids: Array<Long>):Int;
}