package com.yeh35.springkotlinrestguide.domain.shop.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.yeh35.springkotlinrestguide.End2EndSpringBootTest
import com.yeh35.springkotlinrestguide.domain.shop.dao.AuthRepository
import com.yeh35.springkotlinrestguide.domain.shop.domain.Auth
import com.yeh35.springkotlinrestguide.domain.shop.dto.CreateAuthDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@End2EndSpringBootTest
internal class AuthApiTest {
    
    @Autowired
    private lateinit var mockMvc: MockMvc
    
    @Autowired
    private lateinit var authRepository: AuthRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun createAuth() {
        val authName = "김삿갓"
        val dto = CreateAuthDto(name = authName)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/auths")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect { result ->
                val json = result.response.contentAsString
                assertTrue(json.isNotEmpty())
                val createdAuth = objectMapper.readValue(json, Auth::class.java)

                assertNotEquals(0, createdAuth.id)
                assertEquals(authName, createdAuth.name)
            }
    }

    @Test
    fun getAll() {
        for (i in 1 .. 10) {
            val auth = Auth(name = "김삿갓-$i")
            authRepository.save(auth)
        }

        mockMvc.perform(MockMvcRequestBuilders.get("/api/auths"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect { result ->
                val resultShopList = objectMapper.readValue(result.response.contentAsString, List::class.java)
                assertTrue(resultShopList.isNotEmpty())
                assertTrue(resultShopList.size >= 10)
            }
    }
}