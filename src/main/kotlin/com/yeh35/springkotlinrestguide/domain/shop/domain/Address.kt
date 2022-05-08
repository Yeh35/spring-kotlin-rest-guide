package com.yeh35.springkotlinrestguide.domain.shop.domain

import javax.persistence.Embeddable

@Embeddable
class Address(city: String, county: String, roadName: String, buildingNumber: String, detail: String) {

    var city: String = city //시,도
        protected set

    var county: String = county // 시,군,구
        protected set

    var roadName: String = roadName // 도로명
        protected set

    var buildingNumber: String = buildingNumber //건물번호
        protected set

    var detail: String = detail //나머지 주소 (몇동 몇호 등등)
        protected set

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Address

        if (city != other.city) return false
        if (county != other.county) return false
        if (roadName != other.roadName) return false
        if (buildingNumber != other.buildingNumber) return false
        if (detail != other.detail) return false

        return true
    }

    override fun hashCode(): Int {
        var result = city.hashCode()
        result = 31 * result + county.hashCode()
        result = 31 * result + roadName.hashCode()
        result = 31 * result + buildingNumber.hashCode()
        result = 31 * result + detail.hashCode()
        return result
    }
}
