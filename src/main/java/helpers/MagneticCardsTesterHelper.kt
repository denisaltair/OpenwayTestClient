package helpers

import entities.TestResult
import entities.TransMessage
import enums.OpenwayResponseCode
import enums.OperationType
import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.entities.EntryMode
import kz.multibank.cardposclient.exceptions.TransMessageException
import other.OpenwayCryptoUtils
import other.OpenwayUtils
import other.Utils
import java.math.BigDecimal
import java.util.*


object MagneticCardsTesterHelper {
    private val key = Utils.hexStringToByteArray("5413926DE0296E91C7F413387064FBD0")

    private val pinBlockCard1 = OpenwayCryptoUtils.calcPinBlock(key, Config.CARD1_PIN, Config.CARD1_PAN)
    private val pinBlockCard2 = OpenwayCryptoUtils.calcPinBlock(key, Config.CARD2_PIN, Config.CARD2_PAN)
    private val pinBlockCard6 = OpenwayCryptoUtils.calcPinBlock(key, Config.CARD6_PIN, Config.CARD6_PAN)
    private val pinBlockCard7 = OpenwayCryptoUtils.calcPinBlock(key, Config.CARD7_PIN, Config.CARD7_PAN)
    private val badPinBlockCard = OpenwayCryptoUtils.calcPinBlock(key, "987654", Config.CARD2_PAN)
    private val card1ScvTag="0081610" + Config.CARD1_CVV2
    private val card2ScvTag="0081610" + Config.CARD2_CVV2
    private val card6ScvTag="0081610" + Config.CARD6_CVV2
    private val card7ScvTag="0081610" + Config.CARD7_CVV2


    fun sendRequest(operationType: OperationType, amount:BigDecimal, pan:String, entryMode:EntryMode, tid:String,
                    guid:String=Utils.getGUID(), parentGuid:String="", isRepeat:Boolean=false, currency: Currency?=null,
                    cashBackAmount:BigDecimal?=null) :TestResult {

        try {
            val transMessage = TransMessage()

            var pinBlock = ByteArray(0)
            var scvTag = ""
            var track2 = ""
            var _currency = Currency.RUB
            when (pan) {
                Config.CARD1_PAN -> {
                    pinBlock = pinBlockCard1
                    scvTag = card1ScvTag
                    track2 = Config.CARD1_TRACK2
                    _currency = currency?:Currency.RUB
                }

                Config.CARD2_PAN -> {
                    pinBlock = pinBlockCard2
                    scvTag = card2ScvTag
                    track2 = Config.CARD2_TRACK2
                    _currency = currency?:Currency.USD
                }

                Config.CARD6_PAN -> {
                    pinBlock = pinBlockCard6
                    scvTag = card6ScvTag
                    track2 = Config.CARD6_TRACK2
                    _currency = currency?:Currency.RUB
                }

                Config.CARD7_PAN -> {
                    pinBlock = pinBlockCard7
                    scvTag = card7ScvTag
                    track2 = Config.CARD7_TRACK2
                    _currency = currency?:Currency.USD
                }
            }


            when (entryMode) {
                EntryMode.MAGNET_PBT -> {
                    transMessage.track2 = track2
                    transMessage.pinBlock = pinBlock
                }
                EntryMode.MAGNET_SBT -> {
                    transMessage.track2 = track2
                }
                EntryMode.MANUAL_SBT -> {
                    transMessage.reservedPrivate = scvTag
                }
            }
            transMessage.entryMode = entryMode
            transMessage.guid = guid
            transMessage.pan = pan
            transMessage.cardExpiredDate = OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPIRED_DATE)
            transMessage.tid = tid
            transMessage.currency = _currency
            transMessage.transmissionDate = Date()
            transMessage.amount = amount
            transMessage.cashBackAmount=cashBackAmount



            val response = when (operationType) {
                OperationType.AUTHORISATION -> OpenwayRequests.authorizationRequest(transMessage, isRepeat)
                OperationType.PURCHASE -> OpenwayRequests.purchaseRequest(transMessage, isRepeat)
                OperationType.PURCHASE_WITH_CASH_BACK -> OpenwayRequests.purchaseWithCashBackRequest(transMessage, isRepeat)
                OperationType.AUTHORISATION_CONFIRMATION -> {
                    transMessage.parentGuid = parentGuid
                    transMessage.track2 = null
                    transMessage.tid = null

                    if (currency==null) transMessage.currency = null
                    transMessage.cardExpiredDate = null
                    OpenwayRequests.authConfirmationRequest(transMessage, isRepeat)
                }
                OperationType.REFUND -> {
                    transMessage.parentGuid = parentGuid
                    transMessage.track2 = null
                    transMessage.tid = null
                    if (currency==null) transMessage.currency = null
                    transMessage.cardExpiredDate = null
                    OpenwayRequests.refundRequest(transMessage, isRepeat)
                }

                OperationType.REVERSAL -> {
                    transMessage.parentGuid = parentGuid
                    transMessage.track2 = null
                    transMessage.tid = null
                    if (currency==null) transMessage.currency = null
                    transMessage.cardExpiredDate = null
                    OpenwayRequests.reversalRequest(transMessage, isRepeat)
                }

                OperationType.AUTO_REVERSAL -> {
                    transMessage.parentGuid = parentGuid
                    transMessage.track2 = null
                    transMessage.tid = null
                    if (currency==null) transMessage.currency = null
                    transMessage.cardExpiredDate = null
                    OpenwayRequests.automaticReversalRequest(transMessage, isRepeat)
                }

                OperationType.PURCHASE_RETURN -> {
                    transMessage.parentGuid = parentGuid
                    transMessage.track2 = null
                    transMessage.tid = null
                    if (currency==null) transMessage.currency = null
                    transMessage.cardExpiredDate = null
                    OpenwayRequests.purchaseReturnRequest(transMessage, isRepeat)
                }


                else -> throw TransMessageException(TransMessageException.ErrorCode.UNKNOWN_ERROR)
            }

            return TestResult(response.openwayResponseCode?:OpenwayResponseCode.UNKNOWN_CODE, response.rrn?:"")
        }catch (e:Exception) {
            throw  throw TransMessageException(TransMessageException.ErrorCode.UNKNOWN_ERROR)


        }

    }

    fun makeReconciliation():TestResult {
        var transMessage = TransMessage()
        transMessage.guid = Utils.getGUID()
        transMessage.transmissionDate = Date()
        transMessage.tid = Config.TESTS_TERMINAL_1

        var response = OpenwayRequests.reconciliationRequest(transMessage)
        return TestResult(response.openwayResponseCode?:OpenwayResponseCode.UNKNOWN_CODE, response.rrn?:"")

    }

    fun getReport(): TestResult {
        var transMessage = TransMessage()
        transMessage.guid = Utils.getGUID()
        transMessage.transmissionDate = Date()
        transMessage.tid = Config.TESTS_TERMINAL_1

        var response = OpenwayRequests.getReport(transMessage)
        return TestResult(response.openwayResponseCode?:OpenwayResponseCode.UNKNOWN_CODE, response.rrn?:"")
    }



}