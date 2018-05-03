package com.example.firstapp.blog.service.impl

import com.example.firstapp.blog.domain.ContentDO

open interface ContentService {

	fun get(cid :Long): ContentDO;
	fun list(map:Map<String,Any>):List<ContentDO>
	fun count(map: Map<String, Any>):Int
	fun save(contentDO: ContentDO):Int
	fun update(contentDO: ContentDO):Int
	fun remove(cid: Long):Int;
	fun batchRemove(cids:Array<Long>): Int?;
}