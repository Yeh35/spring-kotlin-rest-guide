package com.yeh35.springkotlinrestguide.domain.shop.api

import com.yeh35.springkotlinrestguide.domain.shop.application.AuthService
import com.yeh35.springkotlinrestguide.domain.shop.domain.Auth
import com.yeh35.springkotlinrestguide.domain.shop.dto.CreateAuthDto
import org.springframework.web.bind.annotation.*

@RequestMapping("\${api-prefix}/auths")
@RestController
class AuthApi(
    private val authService: AuthService
) {

    @PostMapping
    fun createAuth(@RequestBody createAuthDto: CreateAuthDto): Auth {
        return authService.create(createAuthDto)
    }

    @GetMapping
    fun getAll(): List<Auth> {
        return authService.getAll()
    }
}