package com.example.firstapp.common.utils

import jdk.nashorn.internal.objects.NativeDate.getTime
import lombok.extern.slf4j.Slf4j
import java.text.SimpleDateFormat
import java.util.*

@Slf4j
object DateUtils {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    val DATE_PATTERN = "yyyy-MM-dd"
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"

    fun format(date: Date): String? {
        return format(date, DATE_PATTERN)
    }

    fun format(date: Date?, pattern: String): String? {
        if (date != null) {
            val df = SimpleDateFormat(pattern)
            return df.format(date)
        }
        return null
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    fun getTimeBefore(date: Date): String {
        val now = Date()
        val l = now.getTime() - date.getTime()
        val day = l / (24 * 60 * 60 * 1000)
        val hour = l / (60 * 60 * 1000) - day * 24
        val min = l / (60 * 1000) - day * 24 * 60 - hour * 60
        val s = l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60
        var r :String = ""
        if (day > 0) {
            r += day.toString() + "天"
        } else if (hour > 0) {
            r += hour.toString() + "小时"
        } else if (min > 0) {
            r += min.toString() + "分"
        } else if (s > 0) {
            r += s.toString() + "秒"
        }
        r += "前"
        return r
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    fun getTimeBeforeAccurate(date: Date): String {
        val now = Date()
        val l = now.getTime() - date.getTime()
        val day = l / (24 * 60 * 60 * 1000)
        val hour = l / (60 * 60 * 1000) - day * 24
        val min = l / (60 * 1000) - day * 24 * 60 - hour * 60
        val s = l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60
        var r = ""
        if (day > 0) {
            r += day.toString() + "天"
        }
        if (hour > 0) {
            r += hour.toString() + "小时"
        }
        if (min > 0) {
            r += min.toString() + "分"
        }
        if (s > 0) {
            r += s.toString() + "秒"
        }
        r += "前"
        return r
    }


}
