package com.yeh35.springkotlinrestguide.domain.shop.application

import com.yeh35.springkotlinrestguide.domain.shop.dao.AuthRepository
import com.yeh35.springkotlinrestguide.domain.shop.dao.KeywordRepository
import com.yeh35.springkotlinrestguide.domain.shop.dao.ReviewRepository
import com.yeh35.springkotlinrestguide.domain.shop.dao.ShopRepository
import com.yeh35.springkotlinrestguide.domain.shop.domain.Keyword
import com.yeh35.springkotlinrestguide.domain.shop.domain.Review
import com.yeh35.springkotlinrestguide.domain.shop.domain.Shop
import com.yeh35.springkotlinrestguide.domain.shop.dto.CreateShopDto
import com.yeh35.springkotlinrestguide.domain.shop.dto.WriteReviewDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors
import javax.persistence.EntityNotFoundException

@Transactional(readOnly = true)
@Service
class ShopService(
    val shopRepository: ShopRepository,
    val authRepository: AuthRepository,
    val keywordRepository: KeywordRepository,
    val reviewRepository: ReviewRepository
) {

    @Transactional
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

    @Transactional
    fun writeReview(shopId: Long, writeReviewDto: WriteReviewDto): Review {
        val shop = shopRepository.getById(shopId) ?: throw EntityNotFoundException("존재하지 않는 Shop입니다.")
        val auth = authRepository.getById(writeReviewDto.authId) ?: throw EntityNotFoundException("존재하지 않는 Auth입니다.")

        val keywords = writeReviewDto.keyword.stream().map { keywordText ->
            val keyword = Keyword(keyword = keywordText)
            keywordRepository.save(keyword)
        }.collect(Collectors.toList())

        val review = Review(
            shop = shop,
            auth = auth,
            review = writeReviewDto.review,
            keywords = keywords
        )

        return reviewRepository.save(review)
    }
}