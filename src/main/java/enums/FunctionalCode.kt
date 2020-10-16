package enums

import java.lang.IllegalArgumentException

enum class FunctionalCode (var openWayCode:String, var description:String){
    FULL_MERCHANT_INITIATED_REVERSAL ("400","full merchant-initiated reversals advice"),
    PARTIAL_MERCHANT_INITIATED_REVERSAL ("401","partial merchant-initiated reversals advice"),
    FULL_AUTO_REVERSAL ("402","full automatically-generated reversal advice"),
    AUTH_CONFIRMATION ("202","authorization confirmation");


    companion object {
        fun valueOfFromOpenwayCode(openWayCode: String): FunctionalCode {
            for (value in FunctionalCode.values()) {
                if (value.openWayCode == openWayCode) return value
            }
            throw IllegalArgumentException()
        }
    }

}