package com.example.firstapp.system.config

import com.example.firstapp.common.controller.BaseController
import com.example.firstapp.common.utils.MD5Utils
import com.example.firstapp.common.utils.R
import com.example.firstapp.common.utils.ShiroUtils
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.security.Security

@Controller
class LoginController : BaseController() {

    @GetMapping("/","")
    fun welcome() : String {
        return "redirect:/blog";
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/logout")
    fun logout(): String {
        ShiroUtils.logout()
        return "redirect:/login"
    }

    @GetMapping("/main")
    fun main(): String {
        return "main"
    }

    @PostMapping("login")
    @ResponseBody
    fun ajsxLogin(username:String, password:String): R {
        val encrypt = MD5Utils.encrypt(username, password);
        val token:UsernamePasswordToken = UsernamePasswordToken(username,encrypt)
        val subject = SecurityUtils.getSubject()
        try {
            subject.login(token)
            return R.ok();
        }catch (e : AuthenticationException){
            return R.error("用户或密码错误")
        }
    }

}