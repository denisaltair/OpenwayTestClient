
import enums.FunctionalCode
import enums.OpenwayResponseCode
import entities.TransMessage

import java.util.*
import java.util.concurrent.TimeUnit

object OpenwayRequests {
    fun checkConnect(tid:String=Config.TESTS_TERMINAL_1, stan:String="000001"):TransMessage {
        val transMessage=TransMessage()
        transMessage.mti="800"
        transMessage.processCode="930000"
        transMessage.transmissionDate=Date()
        transMessage.stan=stan
        transMessage.tid=tid
        val server=OpenwayGateway()
        return  server.send(transMessage)
    }

    fun authorizationRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if (isRepeat) "101" else "100"
        transMessage.processCode="000000"
        transMessage.posConditionalCode="00"

        return sendRequest(transMessage)

    }

    fun sendRequest(transMessage: TransMessage):TransMessage {
        var server=OpenwayGateway()

        TimeUnit.SECONDS.sleep(Config.SLEEP_BETWEEN_REQUESTS.toLong())
        var responseTransMessage=server.send(transMessage)
        server.close()

        if (responseTransMessage.openwayResponseCode!= OpenwayResponseCode.SERVER_NOT_RESPONDING) return responseTransMessage

        val mti=transMessage.mti.substring(0..1)+'1'
        var i=Config.TRY_TO_REPEAT_IF_SERVER_NOT_RESPONDING
        do {
            println("Server not responding, trying to repeat...")
            TimeUnit.SECONDS.sleep(Config.SLEEP_BETWEEN_REQUESTS.toLong())
            server=OpenwayGateway()
            transMessage.mti=mti
            responseTransMessage=server.send(transMessage)
            server.close()
            i--
        } while (responseTransMessage.openwayResponseCode== OpenwayResponseCode.SERVER_NOT_RESPONDING && i!=0)
        return responseTransMessage

    }

    fun purchaseRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "200" else "201"
        transMessage.processCode="000000"
        transMessage.posConditionalCode="00"
        val server=OpenwayGateway()
        return sendRequest(transMessage)
    }

    fun automaticReversalRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "420" else "421"
        transMessage.processCode="000000"
        transMessage.functionalCode= FunctionalCode.FULL_AUTO_REVERSAL
        val server=OpenwayGateway()
        return sendRequest(transMessage)
    }

    fun refundRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "200" else "201"
        transMessage.processCode="200000"
        val server=OpenwayGateway()
        return sendRequest(transMessage)
    }

    fun reversalRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "400" else "401"
        transMessage.processCode="000000"
        val server=OpenwayGateway()
        return sendRequest(transMessage)
    }



    fun authConfirmationRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "220" else "221"
        transMessage.processCode="000000"
        val server=OpenwayGateway()
        return sendRequest(transMessage)
    }

    fun purchaseReturnRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "200" else "201"
        transMessage.processCode="250000"
        val server=OpenwayGateway()
        return sendRequest(transMessage)
    }




}