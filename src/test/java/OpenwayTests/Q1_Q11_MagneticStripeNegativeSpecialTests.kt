package OpenwayTests

import OpenwayRequests
import enums.OpenwayResponseCode
import entities.TransMessage
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.entities.EntryMode
import org.junit.Test
import other.OpenwayUtils
import other.Utils
import java.math.BigDecimal
import java.util.*
import kotlin.test.assertNotEquals

class Q1_Q11_MagneticStripeNegativeSpecialTests : TestCase(){
    //Q1. Incorrect MAC (Terminal 1)
    @Test
    fun testQ1() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode: OpenwayResponseCode

        var guid=Utils.getGUID()
        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(101.1)
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

        //Q1.01C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q1.01C Automatic Reversal of Q1.01A Success rrn:"+response.rrn)


        //Q1.02A
        guid=Utils.getGUID()
        transMessage.guid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.amount= BigDecimal(102.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.USD

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
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q1.02C Automatic Reversal of Q1.02A Success rrn:"+response.rrn)
    }

    //Q2. Unmatched Card Number (Terminal 1)
    @Test
    fun testQ2() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode: OpenwayResponseCode
        var guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(201.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q2.01A
        response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_PAN)
        println("Q2.01A Authorisation, Unmatched Card Number rrn:"+response.rrn)

        //Q2.01B
        response=OpenwayRequests.authorizationRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_PAN)
        println("Q2.01B Repeat of Q2.01A Success rrn:"+response.rrn)

        //Q2.01C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q2.01C Automatic Reversal of Q2.01A Success rrn:"+response.rrn)


        //Q2.02A
        transMessage= TransMessage()
        guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.amount= BigDecimal(202.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_PAN)
        println("Q2.02A Purchase, Unmatched Card Number rrn:"+response.rrn)

        //Q2.02B
        response=OpenwayRequests.purchaseRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_PAN)
        println("Q2.02B Repeat of Q2.02A Success rrn:"+response.rrn)

        //Q2.02C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q2.02C Automatic Reversal of Q2.02A Success rrn:"+response.rrn)
    }

    //Q3. Unmatched Amount (Terminal 1)
    @Test
    fun testQ3() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode: OpenwayResponseCode
        var guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(301.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q3.01A
        response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("Q3.01A Authorisation, Unmatched Amount rrn:"+response.rrn)

        //Q3.01B
        response=OpenwayRequests.authorizationRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("Q3.01B Repeat of Q3.01A Success rrn:"+response.rrn)

        //Q3.01C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q3.01C Automatic Reversal of Q3.01A Success rrn:"+response.rrn)


        //Q3.02A
        transMessage= TransMessage()
        guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.amount= BigDecimal(302.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("Q3.02A Purchase, Unmatched amount rrn:"+response.rrn)

        //Q3.02B
        response=OpenwayRequests.purchaseRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("Q3.02B Repeat of Q3.02A Success rrn:"+response.rrn)

        //Q3.02C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q3.02C Automatic Reversal of Q3.02A Success rrn:"+response.rrn)
    }

    //Q4. Unusual Auth Code (Terminal 1)
    @Test
    fun testQ4() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode: OpenwayResponseCode
        var guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(401.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q4.01
        response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q4.01 Authorisation, Unusual Auth Code rrn:"+response.rrn)


        //Q4.02A
        transMessage= TransMessage()
        guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.amount= BigDecimal(402.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q4.02 Purchase, Unusual Auth Code rrn:"+response.rrn)

    }

    //Q6. Incorrect Terminal ID (Terminal 1)
    @Test
    fun testQ6() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode: OpenwayResponseCode
        var guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(601.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q6.01A
        response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_TID)
        println("Q6.01A Incorrect Terminal ID (Terminal 1) rrn:"+response.rrn)

        //Q6.01B
        response=OpenwayRequests.authorizationRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_TID)
        println("Q6.01B Repeat of Q3.01A Success rrn:"+response.rrn)

        //Q6.01C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q6.01C Automatic Reversal of Q6.01A Success rrn:"+response.rrn)


        //Q6.02A
        transMessage= TransMessage()
        guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.amount= BigDecimal(602.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_TID)
        println("Q6.02A Purchase, Incorrect Terminal ID rrn:"+response.rrn)

        //Q6.02B
        response=OpenwayRequests.purchaseRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_TID)
        println("Q6.02B Repeat of Q6.02A Success rrn:"+response.rrn)

        //Q6.02C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q6.02C Automatic Reversal of Q6.02A Success rrn:"+response.rrn)
    }

    //Q7. Alphanumeric RC (Terminal 1)
    @Test
    fun testQ7() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode: OpenwayResponseCode
        var guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(701.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q7.01
        response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.UNKNOWN_CODE)
        println("Q7.01 Authorisation, Alphanumeric RC rrn:"+response.rrn)


        //Q7.02
        transMessage= TransMessage()
        guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.amount= BigDecimal(402.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        assertEquals(bankResponseCode, OpenwayResponseCode.UNKNOWN_CODE)
        println("Q7.02 Purchase, Alphanumeric RC rrn:"+response.rrn)

    }

    //Q8. Unmatched MTID (Terminal 1)
    @Test
    fun testQ8() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode: OpenwayResponseCode
        var guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(801.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q8.01A
        response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_RESPONSE_OPERATION)
        println("Q8.01A Unmatched MTID rrn:"+response.rrn)



        //Q8.01B
        response=OpenwayRequests.authorizationRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_RESPONSE_OPERATION)
        println("Q8.01B Repeat of Q8.01A Success rrn:"+response.rrn)

        //Q8.01C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q8.01C Automatic Reversal of Q8.01A Success rrn:"+response.rrn)


        //Q8.02A
        transMessage= TransMessage()
        guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.amount= BigDecimal(802.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_RESPONSE_OPERATION)
        println("Q8.02A Purchase, Unmatched MTID rrn:"+response.rrn)

        //Q8.02B
        response=OpenwayRequests.purchaseRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertNotEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q8.02B Repeat of Q8.02A Success rrn:"+response.rrn)

        //Q8.02C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q8.02C Automatic Reversal of Q8.02A Success rrn:"+response.rrn)
    }

    //Q9. Unmatched Processing Code (Terminal 1)
    @Test
    fun testQ9() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode: OpenwayResponseCode
        var guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(901.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q9.01A
        response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_MESSAGE_FORMAT)
        println("Q8.01A Unmatched Processing Code rrn:"+response.rrn)



        //Q9.01B
        response=OpenwayRequests.authorizationRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_MESSAGE_FORMAT)
        println("Q9.01B Repeat of Q9.01A Success rrn:"+response.rrn)

        //Q9.01C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q9.01C Automatic Reversal of Q9.01A Success rrn:"+response.rrn)


        //Q9.02A
        transMessage= TransMessage()
        guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.amount= BigDecimal(902.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_MESSAGE_FORMAT)
        println("Q9.02A Purchase, Unmatched Processing Code rrn:"+response.rrn)

        //Q9.02B
        response=OpenwayRequests.purchaseRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_MESSAGE_FORMAT)
        println("Q9.02B Repeat of Q9.02A Success rrn:"+response.rrn)

        //Q8.02C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q9.02C Automatic Reversal of Q9.02A Success rrn:"+response.rrn)
    }

    //Q10. Unmatched STAN (Terminal 1)
    @Test
    fun testQ10() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode: OpenwayResponseCode
        var guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(1001.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q10.01A
        response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_STAN)
        println("Q10.01A Unmatched STAN rrn:"+response.rrn)



        //Q10.01B
        response=OpenwayRequests.authorizationRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_STAN)
        println("Q10.01B Repeat of Q10.01A Success rrn:"+response.rrn)

        //Q10.01C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q10.01C Automatic Reversal of Q10.01A Success rrn:"+response.rrn)


        //Q10.02A
        transMessage= TransMessage()
        guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.amount= BigDecimal(1002.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_STAN)
        println("Q10.02A Purchase, Wrong STAN rrn:"+response.rrn)

        //Q10.02B
        response=OpenwayRequests.purchaseRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_STAN)
        println("Q10.02B Repeat of Q10.02A Success rrn:"+response.rrn)

        //Q10.02C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q10.02C Automatic Reversal of Q10.02A Success rrn:"+response.rrn)
    }

    //Q11. Unmatched Currency (Terminal 1)
    @Test
    fun testQ11() {
        var transMessage= TransMessage()
        var response:TransMessage
        var openwayResponseCode: OpenwayResponseCode
        var guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.amount= BigDecimal(1101.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        //Q11.01A
        response=OpenwayRequests.authorizationRequest(transMessage)
        var bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_CURRENCY_CODE)
        println("Q11.01A Unmatched Currency rrn:"+response.rrn)



        //Q11.01B
        response=OpenwayRequests.authorizationRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_CURRENCY_CODE)
        println("Q11.01B Repeat of Q11.01A Success rrn:"+response.rrn)

        //Q11.01C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD1_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        openwayResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q11.01C Automatic Reversal of Q11.01A Success rrn:"+response.rrn)


        //Q11.02A
        transMessage= TransMessage()
        guid=Utils.getGUID()

        transMessage.guid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.amount= BigDecimal(1102.1)
        transMessage.transmissionDate= Date()
        transMessage.expiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD2_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_CURRENCY_CODE)
        println("Q11.02A Purchase, Unmatched Currency rrn:"+response.rrn)

        //Q11.02B
        response=OpenwayRequests.purchaseRequest(transMessage,isRepeat = true)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.WRONG_CURRENCY_CODE)
        println("Q11.02B Repeat of Q11.02A Success rrn:"+response.rrn)

        //Q11.02C
        transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid=guid
        transMessage.pan=Config.CARD2_PAN
        transMessage.transmissionDate= Date()
        response=OpenwayRequests.automaticReversalRequest(transMessage)
        bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE

        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
        println("Q11.02C Automatic Reversal of Q11.02A Success rrn:"+response.rrn)
    }





}