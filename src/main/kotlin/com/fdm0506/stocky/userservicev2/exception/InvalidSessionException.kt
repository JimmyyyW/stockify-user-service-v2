package com.fdm0506.stocky.userservicev2.exception;

import java.lang.Exception

class InvalidSessionException(message: String? = "Could not match user to persistence entity") : Exception(message) {}
