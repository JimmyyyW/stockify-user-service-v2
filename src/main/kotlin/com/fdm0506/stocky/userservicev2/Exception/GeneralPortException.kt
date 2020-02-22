package com.fdm0506.stocky.userservicev2.Exception

import java.lang.Exception

class GeneralPortException(message: String? = "Unable to invoke port") : Exception(message) {
}