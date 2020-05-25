package com.fdm0506.stocky.userservicev2.exception

import java.lang.Exception

class NotEnoughSharesException(message: String? = "Not enough shares available for given stock") : Exception(message) {}