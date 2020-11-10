
import enums.FunctionalCode
import enums.OpenwayResponseCode
import entities.TransMessage

import java.util.*
import java.util.concurrent.TimeUnit

object OpenwayRequests {
    var outputClient=OutputClient()

    fun checkConnect(tid:String=Config.TESTS_TERMINAL_1, stan:String="000001"):TransMessage {
        val transMessage=TransMessage()
        transMessage.mti="800"
        transMessage.processCode="930000"
        transMessage.transmissionDate=Date()
        transMessage.tid=tid
        return outputClient.send(transMessage)
    }

    fun getCryptoKeysRequest(tid:String=Config.TESTS_TERMINAL_1, stan:String="000001"):TransMessage {
        val transMessage=TransMessage()
        transMessage.mti="800"
        transMessage.processCode="960000"
        transMessage.transmissionDate=Date()
        transMessage.tid=tid
        return sendRequest(transMessage)
    }

    fun authorizationRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if (isRepeat) "101" else "100"
        transMessage.processCode="000000"
        transMessage.posConditionalCode="00"

        return sendRequest(transMessage)

    }

    fun sendRequest(transMessage: TransMessage):TransMessage {
        TimeUnit.SECONDS.sleep(Config.SLEEP_BETWEEN_REQUESTS.toLong())
        var responseTransMessage=outputClient.send(transMessage)
        outputClient.close()

        if (responseTransMessage.openwayResponseCode!= OpenwayResponseCode.SERVER_NOT_RESPONDING) return responseTransMessage

        val mti=transMessage.mti.substring(0..1)+'1'
        var i=Config.tryToRepeatIfServerNotResponding
        do {
            println("Server not responding, trying to repeat...")
            TimeUnit.SECONDS.sleep(Config.SLEEP_BETWEEN_REQUESTS.toLong())
            outputClient=OutputClient()
            transMessage.mti=mti
            responseTransMessage=outputClient.send(transMessage)
            outputClient.close()
            i--
        } while (responseTransMessage.openwayResponseCode== OpenwayResponseCode.SERVER_NOT_RESPONDING && i!=0)
        return responseTransMessage

    }

    fun purchaseRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "200" else "201"
        transMessage.processCode="000000"
        transMessage.posConditionalCode="00"
        return sendRequest(transMessage)
    }

    fun purchaseWithCashBackRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "200" else "201"
        transMessage.processCode="090000"
        transMessage.posConditionalCode="00"
        return sendRequest(transMessage)
    }

    fun automaticReversalRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "420" else "421"
        transMessage.processCode="000000"
        transMessage.functionalCode= FunctionalCode.FULL_AUTO_REVERSAL
        return sendRequest(transMessage)
    }

    fun refundRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "200" else "201"
        transMessage.processCode="200000"
        return sendRequest(transMessage)
    }

    fun reversalRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "400" else "401"
        transMessage.processCode="000000"
        return sendRequest(transMessage)
    }



    fun authConfirmationRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "220" else "221"
        transMessage.processCode="000000"
        return sendRequest(transMessage)
    }

    fun purchaseReturnRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "200" else "201"
        transMessage.processCode="250000"
        return sendRequest(transMessage)
    }

    fun reconciliationRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "500" else "501"
        transMessage.processCode="920000"
        return sendRequest(transMessage)
    }






}