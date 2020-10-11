package kz.multibank.cardposclient.exceptions

class GatewayException(errorCode:ErrorCode, message:String="", e:Exception?=null, resultObject: Any?=null): BaseException(errorCode, message, e) {
    enum class ErrorCode (description:String="") :ErrorCodeEnum {
        BAD_REQUEST("Неправильный запрос"),
        INCORRECT_RESPONDED_MAC("Неправильный запрос"),
        SERVER_READ_TIMEOUT("Сервер не отвечает"),
        UNKNOWN_ERROR("Неизвестная ошшибка"),
        SOCKET_OPEN_TIMEOUT("Ошибка открытия сокета");
    }


}