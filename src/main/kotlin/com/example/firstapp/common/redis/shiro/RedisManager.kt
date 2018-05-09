package com.example.firstapp.common.redis.shiro

import com.sun.org.apache.bcel.internal.generic.IFEQ
import org.springframework.beans.factory.annotation.Value
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

class RedisManager {

    @Value("\${spring.redis.host}")
    var host = "127.0.0.1";

    @Value("\${spring.redis.port}")
    var port = 6379;
    // 0 - never expire
    var expire = 0;

    //timeout for jedis try to connect to redis server, not expire time! In milliseconds
    @Value("\${spring.redis.timeout}")
   var timeout = 0;

    @Value("\${spring.redis.password}")
    var password = "";

   var jedisPool =  JedisPool() ;



    /**
     * 初始化方法
     */
    init {
        if (jedisPool == null) {
            if (password != null && !"".equals(password)) {
                jedisPool = JedisPool( JedisPoolConfig(), host, port, timeout, password);
            } else if (timeout != 0) {
                jedisPool =  JedisPool( JedisPoolConfig(), host, port, timeout);
            } else {
                jedisPool =  JedisPool( JedisPoolConfig(), host, port);
            }
        }
    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */

    fun get(byteArray: ByteArray):ByteArray{
        var value = null;
        var jedis = jedisPool.resource;
        try {
            value = jedis.get(byteArray) as Nothing?
        }finally {
            if (jedis !=null){
                jedis.close();
            }
        }
        return value as ByteArray;
    }


    fun set(key: ByteArray,value:ByteArray):ByteArray {
        var jedis = jedisPool.resource;
        try {
            jedis.set(key,value);
            if (this.expire != 0){
                jedis.expire(key,this.expire)
            }
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */

    fun set(key: ByteArray,value:ByteArray,expire:Int):ByteArray {
        var jedis = jedisPool.resource;
        try {
            jedis.set(key,value);
            if (expire != 0){
                jedis.expire(key,expire)
            }
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }
    /**
     * del
     *
     * @param key
     */
    fun del(key: ByteArray){
        var jedis = jedisPool.resource;
        try {
            jedis.del(key)
        }finally {
            if (jedis != null)
                jedis.close()
        }
    }

    /**
     * flush
     */
    fun flushDB() {
        var jedis = jedisPool.resource;
        try {
            jedis.flushDB()
        }finally {
            if (jedis != null)
                jedis.close()
        }
    }

    /**
     * size
     */
    fun dbSize():Long{
        var dbSize = 0L;
        var jedis = jedisPool.resource
        try {
            dbSize = jedis.dbSize();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return dbSize;
    }

    /**
     * keys
     *
     * @param regex
     * @return
     */
    fun keys(pattern:String) :Set<ByteArray>{
        var keys = null;
        var jedis = jedisPool.resource;
        try {
            keys = jedis.keys(pattern.toByteArray()) as Nothing?
        } finally {
          if (jedis != null){
              jedis.close()
          }
        }
        return keys as Set<ByteArray>;
    }

}
