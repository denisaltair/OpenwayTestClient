package helpers



import enums.FunctionalCode
import enums.OpenwayResponseCode
import entities.TransMessage
import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.entities.EntryMode
import kz.multibank.cardposclient.exceptions.TransMessageException
import org.jpos.iso.ISOMsg
import other.DateUtils
import other.Utils
import java.math.BigDecimal
import java.time.Year
import java.util.*



object TransMessageHelper {
    fun decodeISOMessageToTransMessage(isoMessage: ISOMsg): TransMessage {
        val transMessage = TransMessage()

        for (field in 2..isoMessage.maxField) {
            if (!isoMessage.hasField(field)) continue
            when (field) {
                2 -> transMessage.pan = isoMessage.getString(field)
                3 -> transMessage.processCode = isoMessage.getString(field)
                4 -> transMessage.amount = isoAmountToBigDecimal(isoMessage.getString(field))
                7 -> transMessage.transmissionDate = transmissionDateTimeToDate(isoMessage.getString(field))
                11-> transMessage.stan = isoMessage.getString(field)
                12, 13 -> transMessage.transactionDate = transactionDateTimeToDate(isoMessage.getString(13), isoMessage.getString(12))
                14 -> transMessage.expiredDate = isoExpirationDateToDate(isoMessage.getString(field))
                22 -> transMessage.entryMode = EntryMode.valueOfFromOpenwayCode(isoMessage.getString(field))
                24 -> transMessage.functionalCode = FunctionalCode.valueOfFromOpenwayCode(isoMessage.getString(field))
                25 -> transMessage.posConditionalCode = isoMessage.getString(field)
                28 -> transMessage.amountTransactionFee = isoMessage.getString(field)
                35 -> transMessage.track2 = String(isoMessage.getBytes(field))
                37 -> transMessage.rrn = isoMessage.getString(field)
                38 -> transMessage.authCode = isoMessage.getString(field)
                39 -> transMessage.openwayResponseCode = OpenwayResponseCode.valueOfFromCode(String(isoMessage.getBytes(field)))
                41 -> transMessage.tid = String(isoMessage.getBytes(field))
                49 -> transMessage.currency = Currency.valueOfFromCode(isoMessage.getString(field))
                60 -> transMessage.advice = String(isoMessage.getBytes(field))
                63 -> transMessage.reservedPrivate = String(isoMessage.getBytes(field))
                64 -> transMessage.mac = isoMessage.getBytes(64)
                65 -> transMessage.guid = isoMessage.getString(65)
                66 -> transMessage.parentGuid = isoMessage.getString(66)
                else -> throw TransMessageException(TransMessageException.ErrorCode.UNKNOWN_FIELD, "Unknown field $field")
            }
        }

        transMessage.mti = isoMessage.mti
        return transMessage
    }

    fun encodeTransMessageToISOMessage(transMessage: TransMessage): ISOMsg {
        val isoMsg=ISOMsg()
        isoMsg.mti=transMessage.mti
        isoMsg.set(3, transMessage.processCode)

        if (transMessage.pan != null) isoMsg.set(2, transMessage.pan!!)
        if (transMessage.amount != null) isoMsg.set(4, bigDecimalToIsoAmount(transMessage.amount!!))
        if (transMessage.transmissionDate != null) isoMsg.set(7, dateToTransmissionDate(transMessage.transmissionDate!!))
        if (transMessage.stan != null) isoMsg.set(11, transMessage.stan!!)
        if (transMessage.transactionDate != null) {
            isoMsg.set(12, dateToTransactionalTime(transMessage.transactionDate!!))
            isoMsg.set(13, dateToTransactionalDate(transMessage.transactionDate!!))
        }
        if (transMessage.expiredDate != null) isoMsg.set(14, dateToIsoExpirationDate(transMessage.expiredDate!!))
        if (transMessage.entryMode != null) isoMsg.set(22, transMessage.entryMode!!.openWayCode)
        if (transMessage.functionalCode != null) isoMsg.set(24, transMessage.functionalCode!!.openWayCode)
        if (transMessage.posConditionalCode != null) isoMsg.set(25, transMessage.posConditionalCode!!)
        if (transMessage.amountTransactionFee != null) isoMsg.set(28, transMessage.amountTransactionFee!!)
        if (transMessage.track2 != null) isoMsg.set(35, transMessage.track2!!)
        if (transMessage.rrn != null) isoMsg.set(37, transMessage.rrn!!)
        if (transMessage.authCode != null) isoMsg.set(38, transMessage.authCode!!)
        if (transMessage.openwayResponseCode != null) isoMsg.set(39, transMessage.openwayResponseCode!!.code)
        if (transMessage.tid != null) isoMsg.set(41, transMessage.tid!!)
        if (transMessage.currency != null) isoMsg.set(49, transMessage.currency!!.code)
        if (transMessage.pinBlockData != null) isoMsg.set(52, transMessage.pinBlockData)
        if (transMessage.advice != null) isoMsg.set(60, transMessage.advice!!)
        if (transMessage.reservedPrivate != null) isoMsg.set(63, transMessage.reservedPrivate!!)
        if (transMessage.mac != null) isoMsg.set(64, transMessage.mac!!)
        if (transMessage.guid != null) isoMsg.set(65, transMessage.guid!!)
        if (transMessage.parentGuid != null) isoMsg.set(66, transMessage.parentGuid!!)
        if (transMessage.cashBackAmount!=null) {
            if (transMessage.reservedPrivate==null) transMessage.reservedPrivate=""
            transMessage.reservedPrivate+="01441"+ bigDecimalToIsoAmount(transMessage.cashBackAmount!!)
            isoMsg.set(63,transMessage.reservedPrivate)
        }
        return isoMsg
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