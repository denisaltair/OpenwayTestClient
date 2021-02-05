package enums

import helpers.MagneticCardsTesterHelper
import kz.multibank.cardposclient.entities.Currency
import other.OpenwayCryptoUtils
import other.OpenwayUtils
import other.Utils
import java.util.*
import javax.rmi.CORBA.Util

enum class TestCards(val pan:String, val pin:String, val track2:String, val cvv2:String, val expiredDate:Date= OpenwayUtils.isoExpirationDateToDate("4412"), val currency:Currency=Currency.RUB) {
    EMV_3("1000023100000033", "4959", "1000023100000033=44122011003400000481",  "901"),
    EMV_9("1000054116233573", "2114", "1000054116233573=44122211975300000489",  "611"),
    EMV_10("1000065189685200", "3706", "1000065189685200=44122011497157300005",  "445"),
    EMV_13("1000092823304725", "0017", "1000092823304725=4412201122790005210",  "436"),
    MAG_1("1000011200000011", "6739", "1000011200000011=44121011607200000572",  "172"),
    MAG_2("1000011100000020", "62576", "1000011100000020=44121011483300000867",  "711",currency=Currency.USD),
    MAG_6("1000014100000000068", "8355", "1000014100000000068=4412101135580347",  "362"),
    MAG_7("10000131077", "2846", "10000131077=44121011773000000419",  "116");


}