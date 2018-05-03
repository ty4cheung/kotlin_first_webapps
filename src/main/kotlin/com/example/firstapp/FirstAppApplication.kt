package com.example.firstapp

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@MapperScan("com.example.firstapp.*.dao")
@SpringBootApplication
class FirstAppApplication

fun main(args: Array<String>) {
    runApplication<FirstAppApplication>(*args)
    println("ヾ(◍°∇°◍)ﾉﾞ    启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
            " ______                    _   ______            \n" +
            "|_   _ \\                  / |_|_   _ `.          \n" +
            "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
            "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
            " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
            "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ")
}
