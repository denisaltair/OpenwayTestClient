package exceptions

import kz.multibank.cardposclient.exceptions.BaseException
import java.lang.Exception


class CryptoException(errorCode: ErrorCode, message:String="", exception: Exception?=null): BaseException(errorCode, message, exception) {
    enum class ErrorCode(val description:String): ErrorCodeEnum {
        KCV_EXCEPTION("Ошибка при генерации KCV"),
        ENCRYPT_3DES("Ошибка при генерации 3DES"),
        UNKNOWN_WORK_KEY_INDEX("Неопознанный номер слота"),
        UNKNOWN_KEY_TYPE("Неопознанный тип ключа"),
        INVALID_HASH_MAP("Некорректный HASH MAP"),
        UNKNOWN_ERROR("Неопознанная ошибка");
    }


}