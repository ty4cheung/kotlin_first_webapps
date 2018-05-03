package com.example.firstapp.system.config

import com.example.firstapp.common.controller.BaseController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController : BaseController() {

    @GetMapping("/","")
    fun welcome() : String {
        return "redirect:/blog";
    }
}