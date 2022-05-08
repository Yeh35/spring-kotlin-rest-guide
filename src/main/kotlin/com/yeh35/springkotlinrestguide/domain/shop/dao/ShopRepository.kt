package com.yeh35.springkotlinrestguide.domain.shop.dao

import com.yeh35.springkotlinrestguide.domain.shop.domain.Shop
import org.springframework.data.jpa.repository.JpaRepository

interface ShopRepository: JpaRepository<Shop, Long>  {



}