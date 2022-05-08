package com.yeh35.springkotlinrestguide.domain.shop.domain

import javax.persistence.*

@Entity
@Table(name = "keywords")
class Keyword {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        private set

    var keyword: String
        private set

    constructor(keyword: String) {
        this.keyword = keyword
    }
}
