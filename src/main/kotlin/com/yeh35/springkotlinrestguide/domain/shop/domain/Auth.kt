package com.yeh35.springkotlinrestguide.domain.shop.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "auths")
class Auth {

    @Id
    var id: Long = 0
        private set

    var name: String
        private set

    constructor(name: String) {
        this.name = name
    }
}