package com.yeh35.springkotlinrestguide

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder
import org.springframework.web.filter.CharacterEncodingFilter

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)

@Import(End2EndSpringBootTest.TestConfiguration::class)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
annotation class End2EndSpringBootTest {
    
    class TestConfiguration {

        /**
         * 한글이 깨짐으로 UTF-8 추가
         * @see <a href="https://jehuipark.github.io/spring/boot-2-2-x-mock-mvc-encoding-issue">해결책</a>
         */
        
        @Bean
        fun utf8Config(): MockMvcBuilderCustomizer {
            return MockMvcBuilderCustomizer { builder: ConfigurableMockMvcBuilder<*> ->
                builder.addFilters(CharacterEncodingFilter("UTF-8", true))
            }
        }
    }
}
