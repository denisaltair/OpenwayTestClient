package kz.multibank.cardposclient.exceptions

import java.lang.Exception

class TransMessageException(errorCode:ErrorCode, message:String="", exception:Exception?=null): BaseException(errorCode, message, exception) {
    enum class ErrorCode(val description:String):ErrorCodeEnum  {
        MTI_ERROR("Неопознанный код MTI"),
        UNKNOWN_ERROR("Неопознанная ошибка"),
        UNKNOWN_FIELD("Неопознанное поле");
    }


}