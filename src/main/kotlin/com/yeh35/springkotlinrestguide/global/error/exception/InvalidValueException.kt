package com.yeh35.springkotlinrestguide.global.error.exception

import com.yeh35.springkotlinrestguide.global.error.ErrorCode

class InvalidValueException : BusinessException {
    constructor(message: String) : super(message, ErrorCode.INVALID_INPUT_VALUE)
    constructor(message: String, errorCode: ErrorCode) : super(message, errorCode)
}