package OpenwayTests

import OpenwayRequests
import entities.OpenwayResponseCode
import entities.TransMessage
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.entities.EntryMode
import org.jpos.iso.ISOMsg
import org.junit.Test
import other.OpenwayUtils
import java.math.BigDecimal
import java.util.*

class MagneticCardTest : TestCase(){
    //Q1. Incorrect MAC (Terminal 1)
    @Test
    fun testQ1() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode:OpenwayResponseCode

        val guid=UUID.randomUUID().toString().substring(0..14)
        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(101.1)
        transMessage.stan="000011"
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q1.01A
        response=OpenwayRequests.authorizationRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(openwayResponseCode, OpenwayResponseCode.WRONG_MAC)
        println("Q1.01A Authorisation, Incorrect MAC Success rrn:"+response.rrn)
        //Q1.01B
        response=OpenwayRequests.authorizationRequest(transMessage,isRepeat = true)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.WRONG_MAC)
        println("Q1.01B Repeat of Q1.01A Success rrn:"+response.rrn)
        return

        //Q1.01C
        transMessage.rrn=response.rrn
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q1.01C Automatic Reversal of Q1.01A Success rrn:"+response.rrn)


        //Q1.02A
        transMessage.pan=Config.CARD2_PAN
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.amount= BigDecimal(102.1)
        transMessage.currency=Currency.USD
        response=OpenwayRequests.purchaseRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(openwayResponseCode, OpenwayResponseCode.WRONG_MAC)
        println("Q1.02A Purchase, Incorrect MAC Success rrn:"+response.rrn)

        //Q1.02B
        response=OpenwayRequests.purchaseRequest(transMessage,isRepeat = true)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(openwayResponseCode, OpenwayResponseCode.WRONG_MAC)
        println("Q1.02B Repeat of Q1.02A Success rrn:"+response.rrn)

        //Q1.02C
        transMessage.rrn=response.rrn
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        //assertEquals(bankResponseCode, BankResponseCode.INCORRECT_RESPONDED_MAC)
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q1.02C Automatic Reversal of Q1.02A Success rrn:"+response.rrn)
    }

    //Q2. Unmatched Card Number (Terminal 1)
    @Test
    fun testQ2() {
        val transMessage= TransMessage()
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(201.1)
        transMessage.stan="000001"
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q2.01A
        var response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_PAN)
        println("Q2.01A Authorisation, Unmatched Card Number rrn:"+response.rrn)

        //Q2.01B
        response=OpenwayRequests.authorizationRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_PAN)
        println("Q2.01B Repeat of Q2.01A Success rrn:"+response.rrn)

        //Q2.01C
        transMessage.rrn=response.rrn
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE


        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q2.01C Automatic Reversal of Q2.01A Success rrn:"+response.rrn)

        //Q2.02A
        transMessage.pan=Config.CARD2_PAN
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.amount= BigDecimal(202.1)
        transMessage.currency=Currency.USD
        response=OpenwayRequests.purchaseRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_PAN)
        println("Q2.02A Purchase, Unmatched Card Number rrn:"+response.rrn)

        //Q2.02B
        response=OpenwayRequests.purchaseRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_PAN)
        println("Q2.02B Repeat of Q2.02A Success rrn:"+response.rrn)

        //Q2.02C
        transMessage.rrn=response.rrn
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q2.02C Automatic Reversal of Q2.02A Success rrn:"+response.rrn)
    }

    //Q3. Unmatched Amount (Terminal 1)
    @Test
    fun testQ3() {
        val transMessage= TransMessage()
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(301.1)
        transMessage.stan="000001"
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q3.01A
        var response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("Q3.01A Auth, Unmatched Amount rrn:"+response.rrn)


        //Q3.01B
        response=OpenwayRequests.authorizationRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("Q3.01B Repeat of Q3.01A Success rrn:"+response.rrn)

        //Q3.01C
        transMessage.rrn=response.rrn
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE


        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q3.01C Automatic Reversal of Q3.01A Success rrn:"+response.rrn)

        //Q3.02A
        transMessage.pan=Config.CARD2_PAN
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.amount= BigDecimal(302.1)
        transMessage.currency=Currency.USD
        response=OpenwayRequests.purchaseRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("Q3.02A Purchase, Unmatched Amount rrn:"+response.rrn)

        //Q3.02B
        response=OpenwayRequests.purchaseRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("Q3.02B Repeat of Q3.02A Success rrn:"+response.rrn)

        //Q3.02C
        transMessage.rrn=response.rrn
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q3.02C Automatic Reversal of Q3.02A Success rrn:"+response.rrn)
    }



}