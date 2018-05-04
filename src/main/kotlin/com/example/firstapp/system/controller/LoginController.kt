package com.example.firstapp.system.config

import com.example.firstapp.common.controller.BaseController
import com.example.firstapp.common.utils.ShiroUtils
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

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
}