package com.yeh35.springkotlinrestguide.domain.shop.domain

import javax.persistence.*


@Entity
@Table(name = "auths")
class Auth {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        private set

    var name: String
        private set

    constructor(name: String) {
        this.name = name
    }
}