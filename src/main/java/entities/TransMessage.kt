package entities




import com.google.gson.Gson
import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.entities.EntryMode
import java.math.BigDecimal
import java.util.*

class TransMessage {
    var guid:String=""
    lateinit var mti:String
    lateinit var processCode:String //F3
    var pan: String? = null //F2
    var amount: BigDecimal? = null //F4
    var transmissionDate: Date? = null //F7
    var stan:String?=null //F11
    var transactionDate:Date?=null //F12 F13
    var expiredDate:Date?=null //F14
    var entryMode:EntryMode?=null //F22
    var functionalCode: FunctionalCode?=null //F24
    var posConditionalCode:String?=null //F25
    var amountTransactionFee:String?=null //F28
    var track2:String?=null //F35
    var rrn:String?=null //F37
    var authCode:String?=null //F38
    var openwayResponseCode:OpenwayResponseCode?=null //F39
    var tid:String?=null  //F41
    var currency:Currency?=null  //F49
    var advice:String?=null  //F60
    var reservedPrivate:String?=null //F63
    var mac:ByteArray?=null //F64



    fun clone(): TransMessage {
        val stringProject = Gson().toJson(this, TransMessage::class.java)
        return Gson().fromJson<TransMessage>(stringProject, TransMessage::class.java)
    }

}