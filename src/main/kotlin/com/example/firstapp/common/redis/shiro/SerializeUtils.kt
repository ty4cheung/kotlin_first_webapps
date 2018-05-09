package com.example.firstapp.common.redis.shiro

import org.slf4j.LoggerFactory
import java.io.*

object SerializeUtils {
    private val logger = LoggerFactory.getLogger(SerializeUtils::class.java)

    fun deserialize(bytes: ByteArray): Any? {

        var result: Any? = null

        if (isEmpty(bytes)) {
            return null
        }

        try {
            val byteStream = ByteArrayInputStream(bytes)
            try {
                val objectInputStream = ObjectInputStream(byteStream)
                try {
                    result = objectInputStream.readObject()
                } catch (ex: ClassNotFoundException) {
                    throw Exception("Failed to deserialize object type", ex)
                }

            } catch (ex: Throwable) {
                throw Exception("Failed to deserialize", ex)
            }

        } catch (e: Exception) {
            logger.error("Failed to deserialize", e)
        }

        return result
    }

    fun isEmpty(data: ByteArray?): Boolean {
        return data == null || data.size == 0
    }

    /**
     * 序列化
     * @param object
     * @return
     */
    fun serialize(`object`: Any?): ByteArray? {

        var result: ByteArray? = null

        if (`object` == null) {
            return ByteArray(0)
        }
        try {
            val byteStream = ByteArrayOutputStream(128)
            try {
                if (`object` !is Serializable) {
                    throw IllegalArgumentException(SerializeUtils::class.java.simpleName + " requires a Serializable payload " +
                            "but received an object of type [" + `object`.javaClass.name + "]")
                }
                val objectOutputStream = ObjectOutputStream(byteStream)
                objectOutputStream.writeObject(`object`)
                objectOutputStream.flush()
                result = byteStream.toByteArray()
            } catch (ex: Throwable) {
                throw Exception("Failed to serialize", ex)
            }

        } catch (ex: Exception) {
            logger.error("Failed to serialize", ex)
        }

        return result
    }
}