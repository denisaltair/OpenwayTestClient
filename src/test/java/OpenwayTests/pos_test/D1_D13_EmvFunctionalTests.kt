package OpenwayTests.pos_test

import entities.TestResult
import enums.CardHolderVerificationType.*
import enums.CardSlotType.*
import enums.OpenwayResponseCode
import enums.OpenwayResponseCode.*
import enums.OperationType.*
import enums.TestCards.*
import helpers.EmvCardsTesterHelper
import helpers.MagneticCardsTesterHelper
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency.*
import org.junit.Test
import other.Utils
import java.math.BigDecimal


class D1_D13_EmvFunctionalTests : TestCase() {
    @Test
    fun testD1() {
        println("D1. On-Line Processing, On-Line PIN (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//-----------------D1.01---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D1.01",
            operationType = AUTHORISATION,
            testCard = EMV_3,
            amount = BigDecimal(101.04),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase PBT "
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------D1.04---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D1.04",
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_3,
            description = "Purchase Max Value PBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------D1.07---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D1.07",
            operationType = PURCHASE,
            amount = BigDecimal(107.04),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_3,
            description = "Purchase Real Bad Pin 987654 PBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_PIN)

//-----------------D1.08---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D1.08",
            operationType = PURCHASE,
            amount = BigDecimal(108.04),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_3,
            description = "Purchase, Good PIN 4959 PBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "D1.12",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }

    @Test
    fun testD2() {
        println("On-Line Processing, Offline PIN (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//-----------------D2.01---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D2.01",
            operationType = AUTHORISATION,
            amount = BigDecimal(201.04),
            cardHolderVerificationType = OFFLINE_PIN,
            cardSlotType = ICC,
            testCard = EMV_10,
            description = "Purchase Offline PIN 3706"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------D2.02---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D2.02",
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = OFFLINE_PIN,
            testCard = EMV_9,
            description = "Purchase Offline PIN 2114"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------D2.06---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D2.06",
            operationType = PURCHASE,
            amount = BigDecimal(107.04),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_10,
            description = "Purchase Real Bad Pin 987654"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_PIN)

//-----------------D2.07---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D2.07",
            operationType = PURCHASE,
            amount = BigDecimal(207.04),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_10,
            description = "Purchase, Good PIN 3706 OFFLINE PIN"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//-----------------D2.08---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D2.08",
            operationType = PURCHASE,
            amount = BigDecimal(208.04),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_9,
            description = "Purchase, Real Bad PIN (PIN = 0123456) OFFLINE PIN"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_PIN)

//-----------------D2.09---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D2.09",
            operationType = PURCHASE,
            amount = BigDecimal(209.04),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_9,
            description = "Purchase, Good PIN 2114 OFFLINE PIN"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "D2.13",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }


    @Test
    fun testD3() {
        println("On-Line Processing, SBT (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//-----------------D3.01A---------------------------------------------
        val guidD301A= Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D3.01A",
            operationType = AUTHORISATION,
            amount = BigDecimal(301.04),
            cardHolderVerificationType = SIGNED,
            description = "Authorization SBT",
            guid = guidD301A,
            testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------D3.02A---------------------------------------------
        val guidD302A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D3.02A",
            operationType = PURCHASE, amount = BigDecimal(302.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D3.02A Card: EMV_3 Purchase SBT", guid= guidD302A, testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------D3.03A---------------------------------------------
        val guidD303A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D3.03A",
            operationType = PURCHASE, amount = BigDecimal(303.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB, guid=guidD303A,testCard = EMV_3,
            description = "D3.03A Card: EMV_3 Purchase SBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------D3.04---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D3.04",
            operationType = PURCHASE, amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_3,
            description = "D3.04A Card: EMV_3 Purchase Max Value SBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------D3.06---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D3.06",
            operationType = REVERSAL, amount = BigDecimal(306.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "Unmatched Universal Reversal MAC Mandatory", parentGuid= guidD302A,testCard = EMV_3,
            isWithMac = true
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_AMOUNT)

//-----------------D3.02B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D3.02B",
            operationType = REVERSAL, amount = BigDecimal(302.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D3.02B Card: EMV_3 Universal Reversal D3.02A SBT", parentGuid= guidD302A,testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------D3.03B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D3.03B",
            operationType = REVERSAL, amount = BigDecimal(30.03),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D3.03B Card: EMV_3 Universal Reversal D3.03A SBT", parentGuid= guidD303A,testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

////-----------------D3.01B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D3.02B",
            operationType = AUTHORISATION_CONFIRMATION, amount = BigDecimal(301.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D3.01B Card: EMV_3 Authorisation Confirmation D3.01A SBT", parentGuid= guidD301A,testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "D3.07",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

    }

    @Test
    fun testD4() {
        println("D4 On-Line Processing, SBT")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        val guidD401A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D4.01A",
            operationType = PURCHASE,
            amount = BigDecimal(401.04),
            cardHolderVerificationType = SIGNED,
            description = "Purchase SBT",
            guid = guidD401A,
            testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidD402A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D4.02A",
            operationType = PURCHASE,
            amount = BigDecimal(402.04),
            cardHolderVerificationType = SIGNED,
            description = "Purchase SBT",
            guid = guidD402A,
            testCard = EMV_9
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D4.01B",
            operationType = REVERSAL,
            amount = BigDecimal(401.04),
            description = "Universal Reversal D4.01A",
            parentGuid = guidD401A,
            testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D4.02B",
            operationType = REVERSAL,
            amount = BigDecimal(40.20),
            description = "Universal Reversal D4.02A",
            parentGuid = guidD402A,
            testCard = EMV_9
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "D4.03",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }

    @Test
    fun testD7() {
        println("On-Line Fall Back Processing, (Terminals 1,4)")

        var testResult: TestResult
//===================================================================
        val wrongGuid=Config.WRONG_GUID
//---------------------------------------------------------------------
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//-----------------D7.01A---------------------------------------------
        val guidD701A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.01A",
            operationType = AUTHORISATION, amount = BigDecimal(701.04),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Authorization SBT", guid=guidD701A, testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//-----------------D7.02A---------------------------------------------
        val guidD702A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.02A",
            operationType = PURCHASE,
            amount = BigDecimal(702.04),
            cardHolderVerificationType = ONLINE_PIN,
            description = "FALLBACK! Purchase PBT",
            guid=guidD702A,
            testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//-----------------D7.03A---------------------------------------------
        val guidD703A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.03A",
            operationType = PURCHASE,
            amount = BigDecimal(703.04),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Purchase SBT", guid = guidD703A, testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------D7.04A---------------------------------------------
        val guidD704A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.04A",
            operationType = PURCHASE, amount = BigDecimal(704.04),
            cardHolderVerificationType = SIGNED, cardSlotType = MANUAL,
            description = "Purchase Manual", guid = guidD704A, cvv2 = Config.EMV_CARD9_CVV2,testCard = EMV_9
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//-----------------D7.06---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.06",
            operationType = REVERSAL, amount = BigDecimal(706.04),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Unmatched Universal Reversal", parentGuid =wrongGuid, testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_AMOUNT)


//-----------------D7.02B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.02B",
            operationType = REVERSAL, amount = BigDecimal(702.04),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Universal Reversal D7.02A", parentGuid =guidD702A, testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//-----------------D7.03B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.03B",
            operationType = REVERSAL, amount = BigDecimal(70.03),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Universal Reversal D7.03B", parentGuid =guidD703A, testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//-----------------D7.04B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.04B",
            operationType = REFUND, amount = BigDecimal(704.04),
            cardHolderVerificationType = SIGNED, cardSlotType = ICC,
            description = "FALLBACK! Refund D704A", parentGuid =guidD704A, testCard = EMV_9
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//-----------------D7.01B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.01B",
            operationType = AUTHORISATION_CONFIRMATION, amount = BigDecimal(701.04),
            cardHolderVerificationType = SIGNED,
            description = "Auth Confirmation D7.01A", parentGuid =guidD701A, testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "D7.07",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }


    @Test
    fun testD8() {
        println("D8 On-Line Processing Manual")

        var testResult: TestResult
//---------------------------------------------------------------------
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//-----------------D8.01A---------------------------------------------
        val guidD801A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D8.01A",
            operationType = PURCHASE,
            amount = BigDecimal(801.04),
            cardSlotType = MANUAL,
            description = "Purchase", guid=guidD801A, testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        val guidD802A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D8.02A",
            operationType = PURCHASE,
            amount = BigDecimal(802.04),
            cardSlotType = MANUAL,
            description = "Purchase", guid=guidD802A, testCard = EMV_9
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D8.01B",
            operationType = REVERSAL,
            amount = BigDecimal(801.04),
            cardSlotType = MANUAL,
            description = "Universal Reversal D8.01A", parentGuid = guidD801A, testCard = EMV_3
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D8.02B",
            operationType = REVERSAL,
            amount = BigDecimal(80.20),
            cardSlotType = MANUAL,
            description = "Universal Reversal D8.02A", parentGuid = guidD802A, testCard = EMV_9
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "D8.03",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }




}