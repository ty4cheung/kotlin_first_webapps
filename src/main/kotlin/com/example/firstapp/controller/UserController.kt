package com.example.firstapp.controller

import com.example.firstapp.domain.User
import com.example.firstapp.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Controller
class UserController {

    @Autowired
     var userRepository :UserRepository?=null;

    @GetMapping("/create")
    @ResponseBody
    fun saveUser(@RequestParam name:String) :User?{
        var newUser = User();
        newUser.name = name;
        newUser.age = Random().nextInt();
        userRepository?.save(newUser);
        return newUser;
    }

    @GetMapping("/get")
    @ResponseBody
    fun getUser(@RequestParam id:Int):User? {
        val user = userRepository?.get(id);
        return user;
    }

    @GetMapping("/")
    fun index():String{
        return "/index"
    }
}