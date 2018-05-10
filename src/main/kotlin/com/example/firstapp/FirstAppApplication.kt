package com.example.firstapp

import com.example.firstapp.system.config.ShiroConfig
import com.example.firstapp.system.config.TestConfig
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@MapperScan("com.example.firstapp.*.dao")
@SpringBootApplication
class FirstAppApplication

@Autowired
var testConfig: TestConfig? = null

fun main(args: Array<String>) {
    runApplication<FirstAppApplication>(*args)
    println("ヾ(◍°∇°◍)ﾉﾞ    启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
            " ______                    _   ______            \n" +
            "|_   _ \\                  / |_|_   _ `.          \n" +
            "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
            "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
            " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
            "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ")

    print(testConfig)
}
