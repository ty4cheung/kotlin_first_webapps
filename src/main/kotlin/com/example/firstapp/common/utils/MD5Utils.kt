package com.example.firstapp.common.utils

import org.apache.shiro.crypto.hash.SimpleHash
import org.apache.shiro.util.ByteSource

object MD5Utils{
    private val SALT = "1qazxsw2"

    private val ALGORITH_NAME = "md5"

    private val HASH_ITERATIONS = 2

    fun encrypt(pswd: String): String {
        return SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex()
    }

    fun encrypt(username: String, pswd: String): String {
        return SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex()
    }

    @JvmStatic
    fun main(args: Array<String>) {

        print(MD5Utils.encrypt("admin", "1"));
    }
}