package com.example.firstapp.system.dao

import com.example.firstapp.system.domain.UserDO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserDao {

    fun get(userId:Long): UserDO;

	fun list(map : Map<String,Object> ):List<UserDO>;
//
//	int count(Map<String,Object> map);
//
//	int save(UserDO user);
//
//	int update(UserDO user);
//
//	int remove(Long userId);
//
//	int batchRemove(Long[] userIds);
//
//	Long[] listAllDept();

}