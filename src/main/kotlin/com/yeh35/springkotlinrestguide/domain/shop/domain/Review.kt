package com.yeh35.springkotlinrestguide.domain.shop.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "reviews")
class Review {

    @Id
    var id: Long = 0
        private set

    @ManyToOne
    var shop: Shop
        private set

    @ManyToOne
    var auth: Auth
        private set

    var review: String
        private set

    @OneToMany
    var keywords: List<Keyword>
        private set

    @CreatedDate
    var createdTime: LocalDateTime

    @LastModifiedDate
    var modifyTime: LocalDateTime

    constructor(shop: Shop, auth: Auth, review: String, keywords: List<Keyword>) {
        this.auth = auth
        this.shop = shop
        this.review = review
        this.keywords = keywords
        this.createdTime = LocalDateTime.now()
        this.modifyTime = LocalDateTime.now()
    }
}