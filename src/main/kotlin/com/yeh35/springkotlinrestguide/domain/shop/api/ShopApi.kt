package com.yeh35.springkotlinrestguide.domain.shop.api

import com.yeh35.springkotlinrestguide.domain.shop.application.ShopService
import com.yeh35.springkotlinrestguide.domain.shop.domain.Review
import com.yeh35.springkotlinrestguide.domain.shop.domain.Shop
import com.yeh35.springkotlinrestguide.domain.shop.dto.CreateShopDto
import org.springframework.web.bind.annotation.*

@RequestMapping("\${api-prefix}/shops")
@RestController
class ShopApi(
    val shopService: ShopService
) {

    @GetMapping
    fun getShops(): List<Shop> {
        return shopService.getAll()
    }

    @PostMapping
    fun creatShop(@RequestBody createShopDto: CreateShopDto): Shop {
        return shopService.create(createShopDto)
    }


    @GetMapping("/{id}")
    fun getShop(@PathVariable id: Long): Shop {
        return shopService.get(id)
    }

    @GetMapping("/{id}/reviews")
    fun getShopReview(@PathVariable id: Long): List<Review> {
        return shopService.getReviews(id)
    }


}