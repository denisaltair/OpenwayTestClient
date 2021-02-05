package entities




import com.google.gson.Gson
import enums.*
import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.entities.EntryMode
import java.math.BigDecimal
import java.util.*

class  TransMessage {

    lateinit var mti:String
    lateinit var processCode:String //F3
    var operationType:OperationType?=null //Используется для JSON запросов
    var isRepeat=false //Используется для JSON запросов
    var pin:String?=null
    var cardHolderVerificationType:CardHolderVerificationType=CardHolderVerificationType.SIGNED
    var cardSlotType:CardSlotType=CardSlotType.ANYONE
    var cvv2:String?=null
    var pan: String? = null //F2
    var amount: BigDecimal? = null //F4
    var cashBackAmount: BigDecimal?=null
    var transmissionDate: Date? = null //F7
    var stan:String?=null //F11
    var transactionDate:Date?=null //F12 F13
    var cardExpiredDate:Date?=null //F14
    var entryMode:EntryMode?=null //F22
    var cardSequenceNumber:String?=null
    var functionalCode: FunctionalCode?=null //F24
    var posConditionalCode:String?=null //F25
    var amountTransactionFee:String?=null //F28
    var track2:String?=null //F35
    var rrn:String?=null //F37
    var authCode:String?=null //F38
    var openwayResponseCode: OpenwayResponseCode?=null //F39
    var tid:String?=null  //F41
    var currency:Currency?=null  //F49
    var pinBlock:ByteArray?=null  //F52
    var advice:String?=null  //F60
    var reservedPrivate:String?=null //F63
    var mac:ByteArray?=null //F64
    var guid:String="" //F65
    var parentGuid:String?=null //F66
    var description:String?=null
    var isWithMac=false //F67
    var isWithSecureIso=false //F68
    var testNumber:String?=null //F69
    var bankResponse:ByteArray?=null //F70



    fun clone(): TransMessage {
        val stringProject = Gson().toJson(this, TransMessage::class.java)
        return Gson().fromJson<TransMessage>(stringProject, TransMessage::class.java)
    }

}