package com.yeh35.springkotlinrestguide.global.error.exception

import com.yeh35.springkotlinrestguide.global.error.ErrorCode

open class EntityNotFoundException(message: String) : BusinessException(message, ErrorCode.ENTITY_NOT_FOUND)