package kz.multibank.cardposclient.exceptions


import kotlin.Exception

abstract class BaseException(var errorCode: ErrorCodeEnum, message: String = "", var exception: Exception?=null) : Exception(message) {
    interface ErrorCodeEnum


    override fun toString():String {
        return this.javaClass.toString()+ " "+ errorCode.toString() + " " +message
    }







}
