package com.example.firstapp.common.config

object Constant {
    //演示系统账户
    var DEMO_ACCOUNT = "test"
    //自动去除表前缀
    var AUTO_REOMVE_PRE = "true"
    //停止计划任务
    var STATUS_RUNNING_STOP = "stop"
    //开启计划任务
    var STATUS_RUNNING_START = "start"
    //通知公告阅读状态-未读
    var OA_NOTIFY_READ_NO = "0"
    //通知公告阅读状态-已读
    var OA_NOTIFY_READ_YES = 1
    //部门根节点id
    var DEPT_ROOT_ID: Long? = 0L
    //缓存方式
    var CACHE_TYPE_REDIS = "redis"

    var LOG_ERROR = "error"


}