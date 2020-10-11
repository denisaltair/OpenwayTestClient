package other

import org.jpos.iso.ISOMsg
import org.jpos.iso.packager.GenericPackager
import other.Utils
import java.math.BigDecimal
import java.time.Year
import java.util.*


object OpenwayUtils {
    fun getOpenwayPackager():GenericPackager {
        val atfBinIsoXML = Thread.currentThread().contextClassLoader.getResourceAsStream("atf_iso_8583_bin.xml")
        return GenericPackager(atfBinIsoXML)
    }


    fun packOpenwayMessage(isoMsg: ISOMsg): ByteArray {
        isoMsg.packager= getOpenwayPackager()
        return isoMsg.pack()
    }

    fun unpackOpenwayMessage(message: ByteArray):ISOMsg {
        val isoMsg=ISOMsg()
        isoMsg.packager= getOpenwayPackager()
        isoMsg.unpack(message)
        return isoMsg
    }

    fun unpackOpenwayMessage(hexMessage: String):ISOMsg {
        return unpackOpenwayMessage(Utils.hexStringToByteArray(hexMessage))
    }


    fun transmissionDateTimeToDate(value: String): Date =
        DateUtils.getDateFromString(Year.now().toString() + value, "yyyyMMddHHmmss")

    fun dateToTransmissionDate(date: Date): String = DateUtils.dateToFormat(date, "MMddHHmmss")

    fun transactionDateTimeToDate(strDate: String, strTime: String): Date {
        return DateUtils.getDateFromString(Year.now().toString() + strDate + strTime, "yyyyMMddHHmmss")
    }

    fun dateToTransactionalDate(date: Date): String = DateUtils.dateToFormat(date, "MMdd")
    fun dateToTransactionalTime(date: Date): String = DateUtils.dateToFormat(date, "HHmmss")

    fun bigDecimalToIsoAmount(value: BigDecimal): String {
        val amount = value.setScale(2, BigDecimal.ROUND_HALF_UP)
        val amountBDStr = amount.toString()
            .replace(".", "")
            .replace(",", "")
        return Utils.leftPad(amountBDStr, 12, '0')
    }

    fun isoAmountToBigDecimal(value: String): BigDecimal
            = BigDecimal(value.substring(0..9)) + value.substring(10..11).toBigDecimal().divide(BigDecimal(100))


    fun dateToIsoExpirationDate(date: Date) = DateUtils.dateToFormat(date, "yyMM")

    fun isoExpirationDateToDate(value: String): Date = DateUtils.getDateFromString("20"+value, "yyyyMM")




}