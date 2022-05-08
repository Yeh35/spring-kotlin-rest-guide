package com.yeh35.springkotlinrestguide.domain.shop.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "keywords")
class Keyword {

    @Id
    var id: Long = 0
        private set

    var keyword: String
        private set

    constructor(keyword: String) {
        this.keyword = keyword
    }
}
