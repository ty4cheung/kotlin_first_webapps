package com.example.firstapp.system.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class TestConfig (
        @Value("\${wiki.path}") var wikiPath: String,
        @Value("\${wiki.pageSize}") var defaultPageSize: Int
){
    @Value("server.session-timeout")
   val port = ""

}