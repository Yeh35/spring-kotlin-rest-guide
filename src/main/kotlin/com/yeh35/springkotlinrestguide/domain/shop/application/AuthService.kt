package com.yeh35.springkotlinrestguide.domain.shop.application

import com.yeh35.springkotlinrestguide.domain.shop.dao.AuthRepository
import com.yeh35.springkotlinrestguide.domain.shop.domain.Auth
import com.yeh35.springkotlinrestguide.domain.shop.dto.CreateAuthDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class AuthService(
    private val authRepository: AuthRepository
) {

    @Transactional
    fun create(createAuthDto: CreateAuthDto): Auth {
        return authRepository.save(createAuthDto.toAuth())
    }

    fun getAll(): List<Auth> {
        return authRepository.findAll()
    }

}
