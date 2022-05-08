package com.yeh35.springkotlinrestguide.domain.shop.domain

import javax.persistence.*


@Entity
@Table(name = "shops")
class Shop {

    @Id
    var id: Long = 0
        private set

    var name: String
        private set

    @Enumerated(EnumType.STRING)
    var type: ShopType
        private set

    @Embedded
    var address: Address

    constructor(name: String, type: ShopType, address: Address) {
        this.name = name
        this.type = type
        this.address = address
    }
}