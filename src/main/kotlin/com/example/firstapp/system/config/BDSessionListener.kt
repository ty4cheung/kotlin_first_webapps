package com.example.firstapp.system.config

import org.apache.shiro.session.Session
import org.apache.shiro.session.SessionListener
import java.util.concurrent.atomic.AtomicInteger

class BDSessionListener: SessionListener {

    private val sessionCount = AtomicInteger(0)
    override fun onExpiration(p0: Session?) {
        sessionCount.decrementAndGet()
    }

    override fun onStart(p0: Session?) {
        sessionCount.incrementAndGet()
    }

    override fun onStop(p0: Session?) {
        sessionCount.decrementAndGet()
    }
}