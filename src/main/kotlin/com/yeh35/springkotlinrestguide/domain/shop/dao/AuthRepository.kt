package com.yeh35.springkotlinrestguide.domain.shop.dao

import com.yeh35.springkotlinrestguide.domain.shop.domain.Auth
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository: JpaRepository<Auth, Long>  {}