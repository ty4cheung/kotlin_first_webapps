package com.example.firstapp.common.redis.shiro

import lombok.extern.slf4j.Slf4j
import org.apache.shiro.session.Session
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO
import java.io.Serializable

@Slf4j
class RedisSessionDAO : AbstractSessionDAO() {
    var redisManager: RedisManager? = null;

    override fun getActiveSessions(): MutableCollection<Session> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun doReadSession(p0: Serializable?): Session {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun doCreate(p0: Session?): Serializable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(p0: Session?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(p0: Session?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}