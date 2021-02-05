package helpers

import N5OutputClient
import entities.TestResult
import entities.TransMessage
import enums.*
import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.exceptions.TransMessageException
import other.OpenwayCryptoUtils
import other.Utils
import java.math.BigDecimal
import java.util.*


object EmvCardsTesterHelper {

    private var lastDescription=""



    fun sendRequest(testCard: TestCards=TestCards.MAG_1, operationType: OperationType, amount:BigDecimal= BigDecimal.ZERO, cardHolderVerificationType: CardHolderVerificationType= CardHolderVerificationType.SIGNED,
                    cardSlotType: CardSlotType=CardSlotType.ANYONE, tid:String=Config.TESTS_TERMINAL_1,
                    guid:String=Utils.getGUID(), parentGuid:String="", isRepeat:Boolean=false, currency: Currency?=null,
                    description:String="", cashBackAmount:BigDecimal= BigDecimal.ZERO, isWithMac:Boolean=false, isWithSecureIso:Boolean=false
                    , cvv2:String?=null, testNumber:String="") :TestResult {

        try {
            val transMessage = TransMessage()
            transMessage.operationType=operationType
            transMessage.tid=tid
            transMessage.currency= currency ?: testCard.currency
            transMessage.amount=amount
            transMessage.cardHolderVerificationType=cardHolderVerificationType
            transMessage.cardSlotType=cardSlotType
            transMessage.guid=guid
            transMessage.parentGuid=parentGuid
            lastDescription="$testNumber $description "+testCard.toString()+" "+cardSlotType.toString()+ " "+ testCard.pin
            transMessage.description= lastDescription
            transMessage.cashBackAmount=cashBackAmount
            transMessage.isWithMac=isWithMac
            transMessage.isWithSecureIso=isWithSecureIso
            transMessage.cvv2=testCard.cvv2
            transMessage.pan=testCard.pan
            transMessage.track2=testCard.track2
            transMessage.isRepeat=isRepeat
            transMessage.testNumber=testNumber

            if (cardHolderVerificationType==CardHolderVerificationType.ONLINE_PIN) transMessage.pin=testCard.pin
            transMessage.cardExpiredDate=testCard.expiredDate

            val responseTransMessage=N5OutputClient().send(transMessage)
            val responseCode=responseTransMessage.openwayResponseCode?:OpenwayResponseCode.UNKNOWN_CODE

            return TestResult(
                responseCode, responseTransMessage.rrn?:"", responseTransMessage.authCode?:"",
                resultMessage =  "$lastDescription rrn:${responseTransMessage.rrn} $responseCode")

        }catch (e:Exception) {

            throw e


        }

    }



}