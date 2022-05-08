package com.yeh35.springkotlinrestguide.domain.shop.dto

import com.yeh35.springkotlinrestguide.domain.shop.domain.Address
import com.yeh35.springkotlinrestguide.domain.shop.domain.Shop
import com.yeh35.springkotlinrestguide.domain.shop.domain.ShopType
import javax.validation.constraints.NotBlank

data class CreateShopDto(
    @NotBlank
    val name: String,
    val type: ShopType,
    val address: Address
) {

    fun toShop(): Shop {
        return Shop(
            name = name,
            type = type,
            address = address
        )
    }
}
