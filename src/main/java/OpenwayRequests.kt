
import entities.FunctionalCode
import entities.OpenwayResponseCode
import entities.TransMessage

import other.OpenwayUtils
import java.math.BigDecimal
import java.util.*

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
        val server=OpenwayGateway()
        return server.send(transMessage)
    }

    fun purchaseRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "200" else "201"
        transMessage.processCode="000000"
        transMessage.posConditionalCode="00"
        val server=OpenwayGateway()
        return  server.send(transMessage)
    }

    fun automaticReversalRequest(_transMessage: TransMessage, isRepeat:Boolean=false):TransMessage {
        val transMessage=_transMessage.clone()
        transMessage.mti=if(!isRepeat) "420" else "421"
        transMessage.processCode="000000"
        transMessage.posConditionalCode="00"
        transMessage.functionalCode= FunctionalCode.FULL_AUTO_REVERSAL
        transMessage.openwayResponseCode= OpenwayResponseCode.COMPLETED_PARTIALLY
        transMessage.advice= "0200" + OpenwayUtils.bigDecimalToIsoAmount(transMessage.amount?: BigDecimal.ZERO)

        val server=OpenwayGateway()
        return  server.send(transMessage)
    }



}