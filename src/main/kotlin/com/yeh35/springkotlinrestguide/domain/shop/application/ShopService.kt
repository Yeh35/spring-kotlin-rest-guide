package com.yeh35.springkotlinrestguide.domain.shop.application

import com.yeh35.springkotlinrestguide.domain.shop.dao.ReviewRepository
import com.yeh35.springkotlinrestguide.domain.shop.dao.ShopRepository
import com.yeh35.springkotlinrestguide.domain.shop.domain.Review
import com.yeh35.springkotlinrestguide.domain.shop.domain.Shop
import com.yeh35.springkotlinrestguide.domain.shop.dto.CreateShopDto
import org.springframework.stereotype.Service

@Service
class ShopService(
    val shopRepository: ShopRepository,
    val reviewRepository: ReviewRepository
) {

    fun create(createShopDto: CreateShopDto): Shop {
        return shopRepository.save(createShopDto.toShop())
    }

    fun get(id: Long): Shop {
        return shopRepository.getById(id)
    }

    fun getAll(): List<Shop> {
        return shopRepository.findAll()
    }


    fun getReviews(id: Long): List<Review> {
        val shop = this.get(id)
        return reviewRepository.findAllByShop(shop).orElseGet { listOf() }
    }


}