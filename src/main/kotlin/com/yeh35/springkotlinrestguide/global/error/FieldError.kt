package com.yeh35.springkotlinrestguide.global.error

import org.springframework.validation.BindingResult
import java.util.stream.Collectors

class FieldError private constructor(
    val field: String,
    val value: String,
    val reason: String
) {

    companion object {

        fun of(field: String, value: String, reason: String): List<FieldError> {
            val fieldErrors: MutableList<FieldError> = ArrayList()
            fieldErrors.add(FieldError(field = field, value = value, reason = reason))
            return fieldErrors
        }

        internal fun of(bindingResult: BindingResult): List<FieldError> {
            val fieldErrors = bindingResult.fieldErrors
            return fieldErrors.stream().map { error: org.springframework.validation.FieldError ->
                FieldError(
                    field = error.field,
                    value = if (error.rejectedValue == null) "" else error.rejectedValue.toString(),
                    reason = error.defaultMessage ?: ""
                )
            }.collect(Collectors.toList())
        }
    }
}