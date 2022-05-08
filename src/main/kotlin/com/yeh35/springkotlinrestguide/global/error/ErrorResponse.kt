package com.yeh35.springkotlinrestguide.global.error

import org.springframework.validation.BindingResult
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

class ErrorResponse {
    val message: String
    val status: Int
    val code: String
    val errors: List<FieldError>

    private constructor(message: String, code: ErrorCode) {
        this.message = message
        this.status = code.status
        this.code = code.code
        this.errors = listOf()
    }
    private constructor(code: ErrorCode, errors: List<FieldError>) {
        this.message = code.message
        this.status = code.status
        this.code = code.code
        this.errors = errors
    }

    private constructor(code: ErrorCode) {
        this.message = code.message
        this.status = code.status
        this.code = code.code
        this.errors = listOf()
    }

    companion object {
        fun of(code: ErrorCode, bindingResult: BindingResult): ErrorResponse {
            return ErrorResponse(code, FieldError.of(bindingResult))
        }

        fun of(code: ErrorCode): ErrorResponse {
            return ErrorResponse(code)
        }

        fun of(code: ErrorCode, errors: List<FieldError>): ErrorResponse {
            return ErrorResponse(code, errors)
        }

        fun of(exception: RuntimeException, code: ErrorCode): ErrorResponse {
            return ErrorResponse(exception.message!!, code)
        }

        fun of(e: MethodArgumentTypeMismatchException): ErrorResponse {
            val value = if (e.value == null) "" else e.value.toString()
            val errors: List<FieldError> =
                FieldError.of(e.name, value, e.errorCode)
            return ErrorResponse(ErrorCode.INVALID_TYPE_VALUE, errors)
        }
    }
}