package enums
enum class OpenwayResponseCode(val code:String) {
    ACCEPTED("00"),
    INVALID_TRANSACTION("12"),
    WRONG_AMOUNT("13"),
    CUSTOMER_CANCELLATION("17"),
    FORMAT_ERROR ("30"),
    COMPLETED_PARTIALLY("32"),
    WRONG_PIN("55"),
    DECLINED("57"),
    CASH_WITHDRAWAL_LIMIT_EXCEEDED("65"),
    PIN_TRIES_EXCEEDED("75"),
    UNKNOWN_EMITENT("91"),
    FIX_ERROR("95"),
    SYSTEM_DESTROYED("96"),
    UNKNOWN_CODE("A0"),
    UNKNOWN_PROTOCOL_OR_PROTOCOL_VERSION("A1"),
    WRONG_MESSAGE_FORMAT("A2"),
    UNKNOWN_MESSAGE_CLASS("A3"),
    UNKNOWN_OPERATION_TYPE("A4"),
    UNKNOWN_FIELD("A5"),
    WRONG_MAC("A6"),
    WRONG_PAN ("A7"),
    WRONG_STAN ("A8"),
    WRONG_LOCAL_TIME ("A9"),
    WRONG_CARD_EXPIRED_DATE ("B1"),
    WRONG_CARD_SLOT_OR_VERIFICATION_TYPE ("B2"),
    WRONG_RRN ("B4"),
    WRONG_TID ("B5"),
    WRONG_BANK_RESPONSE_CODE("B6"),
    WRONG_GUID("B7"),
    WRONG_CURRENCY_CODE("B8"),
    UNKNOWN_SERVER_ERROR("B9"),
    UNKNOWN_ERROR("C1"),
    SERVER_NOT_RESPONDING("C2"),
    WRONG_RESPONSE_OPERATION("C3");

    companion object {
        fun valueOfFromCode(code: String): OpenwayResponseCode {
            for (openwayResponseCode in values()) {
                if (openwayResponseCode.code == code) return openwayResponseCode
            }
            return UNKNOWN_CODE
        }
    }


}