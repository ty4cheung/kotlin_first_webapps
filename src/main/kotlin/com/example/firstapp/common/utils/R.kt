package com.example.firstapp.common.utils

class R : HashMap<String, Any>() {

    companion object {
        fun error(): R {
            return error(1, "操作失败")
        }

        fun error(msg: String): R {
            return error(500, msg)
        }

        fun error(code: Int, msg: String): R {
            val r = R()
            r["code"] = code
            r["msg"] = msg
            return r
        }

        fun ok(msg: String): R {
            val r = R()
            r["msg"] = msg
            return r
        }

        fun ok(map: Map<String, Any>): R {
            val r = R()
            r.putAll(map)
            return r
        }

        fun ok(): R {
            return R()
        }


        private const val serialVersionUID = 1L
    }

    init {
        put("code", 0)
        put("msg", "操作成功")
    }

    override fun put(key: String, value: Any): R? {
        super.put(key, value)
        return this
    }

}