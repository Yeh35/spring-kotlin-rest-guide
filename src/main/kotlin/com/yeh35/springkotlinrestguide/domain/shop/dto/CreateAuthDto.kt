package com.yeh35.springkotlinrestguide.domain.shop.dto

import com.yeh35.springkotlinrestguide.domain.shop.domain.Auth
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank

data class CreateAuthDto(
    @Max(20) @NotBlank
    val name: String
) {

    fun toAuth(): Auth {
        return Auth(
            name = name
        )
    }
}
