package com.yeh35.springkotlinrestguide.domain.shop.dao

import com.yeh35.springkotlinrestguide.domain.shop.domain.Keyword
import org.springframework.data.jpa.repository.JpaRepository

interface KeywordRepository: JpaRepository<Keyword, Long>  {}