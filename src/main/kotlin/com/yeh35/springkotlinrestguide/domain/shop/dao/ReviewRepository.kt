package com.yeh35.springkotlinrestguide.domain.shop.dao

import com.yeh35.springkotlinrestguide.domain.shop.domain.Review
import com.yeh35.springkotlinrestguide.domain.shop.domain.Shop
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ReviewRepository: JpaRepository<Review, Long>  {

    fun findAllByShop(shop: Shop): Optional<List<Review>>

}