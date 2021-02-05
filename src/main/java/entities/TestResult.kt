package entities

import enums.OpenwayResponseCode

class TestResult(var openwayResponseCode: OpenwayResponseCode,
                 var rrn: String="", var authCode:String="", var resultMessage:String="" ) {

}