package com.example.firstapp.repository

import com.example.firstapp.domain.User
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import javax.annotation.Resource

@Repository
public class UserRepository {

    private val repository = ConcurrentHashMap<Int,Any>() ;

    val idGenerator = AtomicInteger();

    /**
     * 保存用户对象
     */
    fun save(user: User):Boolean {
        var success = false;
        /** 从1 开始*/

        var id = idGenerator.incrementAndGet();
        user.id = id;
        repository.put(id,user);
        return true;
    }

    fun get(id: Int):User? {
        val any = repository.get(id);
        return any as User?
    }

}