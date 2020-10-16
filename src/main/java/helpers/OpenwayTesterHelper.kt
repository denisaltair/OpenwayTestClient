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


object OpenwayTesterHelper {
    private val key = Utils.hexStringToByteArray("5413926DE0296E91C7F413387064FBD0")

    private val pinBlockCard1 = OpenwayCryptoUtils.calcPinBlock(key, Config.CARD1_PIN, Config.CARD1_PAN)
    private val pinBlockCard2 = OpenwayCryptoUtils.calcPinBlock(key, Config.CARD2_PIN, Config.CARD2_PAN)
    private val badPinBlockCard = OpenwayCryptoUtils.calcPinBlock(key, "987654", Config.CARD2_PAN)
    private val card1ScvTag="0081610" + Config.CARD1_CVV2
    private val card2ScvTag="0081610" + Config.CARD2_CVV2


    fun sendRequest(operationType: OperationType, amount:BigDecimal, pan:String, entryMode:EntryMode, tid:String,
                    guid:String=Utils.getGUID(), parentGuid:String="", isRepeat:Boolean=false, currency: Currency?=null) :TestResult {

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
            }


            when (entryMode) {
                EntryMode.MAGNET_PBT -> {
                    transMessage.track2 = track2
                    transMessage.pinBlockData = pinBlock
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
            transMessage.expiredDate = OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
            transMessage.tid = tid
            transMessage.currency = _currency
            transMessage.transmissionDate = Date()
            transMessage.amount = amount



            val response = when (operationType) {
                OperationType.AUTHORISATION -> OpenwayRequests.authorizationRequest(transMessage, isRepeat)
                OperationType.PURCHASE -> OpenwayRequests.purchaseRequest(transMessage, isRepeat)
                OperationType.AUTHORISATION_CONFIRMATION -> {
                    transMessage.parentGuid = parentGuid
                    transMessage.track2 = null
                    transMessage.tid = null

                    if (currency==null) transMessage.currency = null
                    transMessage.expiredDate = null
                    OpenwayRequests.authConfirmationRequest(transMessage, isRepeat)
                }
                OperationType.REFUND -> {
                    transMessage.parentGuid = parentGuid
                    transMessage.track2 = null
                    transMessage.tid = null
                    if (currency==null) transMessage.currency = null
                    transMessage.expiredDate = null
                    OpenwayRequests.refundRequest(transMessage, isRepeat)
                }

                OperationType.REVERSAL -> {
                    transMessage.parentGuid = parentGuid
                    transMessage.track2 = null
                    transMessage.tid = null
                    if (currency==null) transMessage.currency = null
                    transMessage.expiredDate = null
                    OpenwayRequests.reversalRequest(transMessage, isRepeat)
                }

                OperationType.PURCHASE_RETURN -> {
                    transMessage.parentGuid = parentGuid
                    transMessage.track2 = null
                    transMessage.tid = null
                    if (currency==null) transMessage.currency = null
                    transMessage.expiredDate = null
                    OpenwayRequests.purchaseReturnRequest(transMessage, isRepeat)
                }


                else -> throw TransMessageException(TransMessageException.ErrorCode.UNKNOWN_ERROR)
            }


            return TestResult(response.openwayResponseCode?:OpenwayResponseCode.UNKNOWN_CODE, response.rrn?:"")
        }catch (e:Exception) {
            throw  throw TransMessageException(TransMessageException.ErrorCode.UNKNOWN_ERROR)


        }

    }



}