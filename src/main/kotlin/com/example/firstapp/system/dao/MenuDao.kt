package com.example.firstapp.system.dao

import com.example.firstapp.system.domain.MenuDO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MenuDao  {
    fun get(menuId:Long): MenuDO;

	fun list(map:Map<String,Object>):List<MenuDO>;

	fun count(map:Map<String,Object> ):Int;

	fun save(menu:MenuDO):Int;

	fun update(menu:MenuDO):Int;

	fun remove(menuId:Long):Int;

	fun batchRemove(menuIds:LongArray):Int;

	fun listMenuByUserId(id:Long):List<MenuDO>;

	fun listUserPerms(id:Long):List<String>;
}