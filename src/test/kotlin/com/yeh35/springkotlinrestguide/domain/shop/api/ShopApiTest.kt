package com.yeh35.springkotlinrestguide.domain.shop.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.yeh35.springkotlinrestguide.End2EndSpringBootTest
import com.yeh35.springkotlinrestguide.domain.shop.dao.ShopRepository
import com.yeh35.springkotlinrestguide.domain.shop.domain.Address
import com.yeh35.springkotlinrestguide.domain.shop.domain.Shop
import com.yeh35.springkotlinrestguide.domain.shop.domain.ShopType
import com.yeh35.springkotlinrestguide.domain.shop.dto.CreateShopDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@End2EndSpringBootTest
internal class ShopApiTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var shopRepeatable: ShopRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun getShops() {
        val address = Address("서울특별시", "서대문구", "대충 어느길", "123", "여기에유")
        val shop = Shop("식당 어딘", ShopType.CAFE, address)
        shopRepeatable.save(shop)

        mockMvc.perform(get("/api/shops"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect { result ->
                val resultShopList = objectMapper.readValue(result.response.contentAsString, List::class.java)
                assertNotEquals(0, resultShopList.size)

            }
    }

    @Test
    fun creatShop() {
        val address = Address("서울특별시", "서대문구", "대충 어느길", "123", "여기에유")
        val createShopDto = CreateShopDto(name = "test cafe", type = ShopType.CAFE, address = address)
        mockMvc.perform(
            post("/api/shops")
                .content(objectMapper.writeValueAsString(createShopDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect { result ->
                assertTrue(result.response.contentAsString.isNotEmpty())

                assertTrue { shopRepeatable.findAll().size > 0 }
            }
    }

    @Test
    fun getShop() {
        val address = Address("서울특별시", "서대문구", "대충 어느길", "123", "여기에유")
        val shop = Shop("식당 어딘", ShopType.CAFE, address)
        shopRepeatable.save(shop)

        mockMvc.perform(
            get("/api/shops/${shop.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect { result ->
                assertTrue(result.response.contentAsString.isNotEmpty())

                val readShop = objectMapper.readValue(result.response.contentAsString, Shop::class.java)
                assertEquals(shop.id, readShop.id)
                assertEquals(shop.name, readShop.name)
                assertEquals(shop.address, readShop.address)
            }
    }

    @Test
    fun getShopReview() {
        val address = Address("서울특별시", "서대문구", "대충 어느길", "123", "여기에유")
        val shop = Shop("식당 어딘", ShopType.CAFE, address)
        shopRepeatable.save(shop)

        mockMvc.perform(get("/api/shops/${shop.id}/reviews"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect { result ->
                assertTrue(result.response.contentAsString.isNotEmpty())
            }
    }

}