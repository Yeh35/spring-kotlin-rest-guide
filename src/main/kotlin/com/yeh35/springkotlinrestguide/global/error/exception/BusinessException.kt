package com.yeh35.springkotlinrestguide.global.error.exception

import com.yeh35.springkotlinrestguide.global.error.ErrorCode

open class BusinessException : RuntimeException {
    var errorCode: ErrorCode
        private set

    constructor(message: String, errorCode: ErrorCode) : super(message) {
        this.errorCode = errorCode
    }

    constructor(errorCode: ErrorCode) : super(errorCode.message) {
        this.errorCode = errorCode
    }
}