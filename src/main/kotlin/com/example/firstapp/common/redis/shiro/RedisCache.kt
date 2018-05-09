package com.example.firstapp.common.redis.shiro

import org.apache.shiro.cache.Cache
import org.apache.shiro.cache.CacheException
import org.apache.shiro.util.CollectionUtils
import org.slf4j.LoggerFactory
import java.util.*


class RedisCache<K, V>
/**
 * 通过一个JedisManager实例构造RedisCache
 */
(
        /**
         * The wrapped Jedis instance.
         */
        private val cache: RedisManager?) : Cache<K, V> {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * The Redis key prefix for the sessions
     */
    /**
     * Returns the Redis session keys
     * prefix.
     * @return The prefix
     */
    /**
     * Sets the Redis sessions key
     * prefix.
     * @param keyPrefix The prefix
     */
    var keyPrefix = "shiro_redis_session:"

    init {
        if (cache == null) {
            throw IllegalArgumentException("Cache argument cannot be null.")
        }
    }

    /**
     * Constructs a cache instance with the specified
     * Redis manager and using a custom key prefix.
     * @param cache The cache manager instance
     * @param prefix The Redis key prefix
     */
    constructor(cache: RedisManager,
                prefix: String) : this(cache) {

        // set the prefix
        this.keyPrefix = prefix
    }

    /**
     * 获得byte[]型的key
     * @param key
     * @return
     */
    private fun getByteKey(key: K): ByteArray {
        if (key is String) {
            val preKey = this.keyPrefix + key
            return preKey.toByteArray()
        } else {
            return SerializeUtils.serialize(key as ByteArray)!!
        }
    }

    @Throws(CacheException::class)
    override fun get(key: K?): V? {
        logger.debug("根据key从Redis中获取对象 key [$key]")
        try {
            if (key == null) {
                return null
            } else {
                val rawValue = cache!!.get(getByteKey(key))
                return SerializeUtils.deserialize(rawValue) as V?
            }
        } catch (t: Throwable) {
            throw CacheException(t)
        }

    }

    @Throws(CacheException::class)
    override fun put(key: K, value: V): V {
        logger.debug("根据key从存储 key [$key]")
        try {
            cache?.set(getByteKey(key), SerializeUtils.serialize(value)!!)
            return value
        } catch (t: Throwable) {
            throw CacheException(t)
        }

    }

    @Throws(CacheException::class)
    override fun remove(key: K): V? {
        logger.debug("从redis中删除 key [$key]")
        try {
            val previous = get(key)
            cache?.del(getByteKey(key))
            return previous
        } catch (t: Throwable) {
            throw CacheException(t)
        }

    }

    @Throws(CacheException::class)
    override fun clear() {
        logger.debug("从redis中删除所有元素")
        try {
            cache?.flushDB()
        } catch (t: Throwable) {
            throw CacheException(t)
        }

    }

    override fun size(): Int {
        try {
            val longSize = cache?.dbSize()
            return longSize?.toInt()!!
        } catch (t: Throwable) {
            throw CacheException(t)
        }

    }

    override fun keys(): Set<K> {
        try {
            val keys = cache?.keys(this.keyPrefix + "*")
            if (CollectionUtils.isEmpty(keys)) {
                return emptySet()
            } else {
                val newKeys = HashSet<K>()
                for (key in keys!!) {
                    newKeys.add(key as K)
                }
                return newKeys
            }
        } catch (t: Throwable) {
            throw CacheException(t)
        }

    }

    override fun values(): Collection<V> {
        try {
            val keys = cache?.keys(this.keyPrefix + "*")
            if (!CollectionUtils.isEmpty(keys)) {
                val values = ArrayList<V>(keys?.size!!)
                for (key in keys!!) {
                    val value = get(key as K)
                    if (value != null) {
                        values.add(value)
                    }
                }
                return Collections.unmodifiableList(values)
            } else {
                return emptyList()
            }
        } catch (t: Throwable) {
            throw CacheException(t)
        }

    }

}
