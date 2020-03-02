package com.fdm0506.stocky.userservicev2.application.port.common

import javax.validation.*

abstract class SelfValidating<T> {

    private val validator : Validator
    private val validatorFactory : ValidatorFactory = Validation.buildDefaultValidatorFactory()

    init {
        validator = validatorFactory.validator
    }

    @Throws(ConstraintViolationException::class)
    protected fun validateSelf() {
        val violations: Set<ConstraintViolation<T>>? = validator.validate(this as? T)
        if (violations != null) {
            if (violations.isNotEmpty()) {
                throw ConstraintViolationException(violations)
            }
        }
    }
}