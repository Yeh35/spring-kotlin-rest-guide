package com.yeh35.springkotlinrestguide.domain.shop.dto

data class WriteReviewDto(
    val authId: Long,
    val review: String,
    val keyword: List<String>
)