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
//-----------------Reconciliation------------------------------------
//        testResult = MagneticCardsTesterHelper.makeReconciliation()
//        println("Reconciliation, rrn:" + testResult.rrn)
//-----------------D1.01---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = AUTHORISATION, amount = BigDecimal(101.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB, testCard = EMV_3,
            description = "D1.01 Card: EMV_3 Purchase PBT"
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D1.01 Authorisation PBT, rrn:" + testResult.rrn)

//-----------------D1.04---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = ONLINE_PIN, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_3,
            description = "D1.04 Card: EMV_3 Purchase Max Value PBT"
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D1.04 Purchase Max Value PBT, rrn:" + testResult.rrn)

//-----------------D1.07---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(107.04),
            cardHolderVerificationType = ONLINE_PIN, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_3,
            description = "D1.07 Card: EMV_3 Purchase Real Bad Pin 987654 PBT"
        )

        assertEquals(testResult.openwayResponseCode, WRONG_PIN)
        println("D1.07 Purchase Real Bad Pin 987654, rrn:" + testResult.rrn)

//-----------------D1.08---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(108.04),
            cardHolderVerificationType = ONLINE_PIN, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_3,
            description = "D1.08 Card: EMV_3 Purchase, Good PIN 4959 PBT"
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D1.08 Purchase Purchase, Good PIN, rrn:" + testResult.rrn)

//----------------D1.12 Reconciliation------------------------------------
        testResult=MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D1.12 Reconciliation, rrn:" + testResult.rrn)

    }

    @Test
    fun testD2() {
        println("On-Line Processing, Offline PIN (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
//-----------------Reconciliation------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//-----------------D2.01---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = AUTHORISATION, amount = BigDecimal(201.04),
            cardHolderVerificationType = OFFLINE_PIN,cardSlotType = RF, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_10,
            description = "D2.01 Card: EMV_10 Purchase Offline PIN 3706"
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D2.01 Authorisation Offline PIN, rrn:" + testResult.rrn)

//-----------------D2.02---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = OFFLINE_PIN, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_9,
            description = "D2.02 Card: EMV_9 Purchase Offline PIN 2114"
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D2.02 Purchase Offline PIN 4959, rrn:" + testResult.rrn)

//-----------------D2.06---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(107.04),
            cardHolderVerificationType = ONLINE_PIN, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_10,
            description = "D2.06 Card: EMV_10 Purchase Real Bad Pin 987654 OFFLINE PIN"
        )

        assertEquals(testResult.openwayResponseCode, WRONG_PIN)
        println("D2.06 Purchase Real Bad Pin 987654, rrn:" + testResult.rrn)

//-----------------D2.07---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(207.04),
            cardHolderVerificationType = ONLINE_PIN, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_10,
            description = "D2.07 Card: EMV_10 Purchase, Good PIN 3706 OFFLINE PIN"
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D2.07 Purchase, Good PIN, rrn:" + testResult.rrn)


//-----------------D2.08---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(208.04),
            cardHolderVerificationType = ONLINE_PIN, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_9,
            description = "D2.08 Card: EMV_9 Purchase, Real Bad PIN (PIN = 0123456) OFFLINE PIN"
        )

        assertEquals(testResult.openwayResponseCode, WRONG_PIN)
        println("D2.08 Purchase, Real Bad PIN (PIN = 0123456), rrn:" + testResult.rrn)

//-----------------D2.09---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(209.04),
            cardHolderVerificationType = ONLINE_PIN, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_9,
            description = "D2.09 Card: EMV_9 Purchase, Good PIN 2114 OFFLINE PIN"
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D2.09 Purchase, Good PIN, rrn:" + testResult.rrn)


//----------------D2.13 Reconciliation------------------------------------
        testResult=MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D1.13 Reconciliation, rrn:" + testResult.rrn)

    }


    @Test
    fun testD3() {
        println("On-Line Processing, SBT (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
//-----------------Reconciliation------------------------------------
      //  testResult=MagneticCardsTesterHelper.makeReconciliation()
     //   println("Reconciliation, rrn:" + testResult.rrn)
//-----------------D3.01A---------------------------------------------
        val guidD301A= Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = AUTHORISATION, amount = BigDecimal(301.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D3.01A Card: EMV_3 Authorization SBT", guid = guidD301A,testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D3.01 Authorisation (For Terminal 1 only), rrn:" + testResult.rrn)

//-----------------D3.02A---------------------------------------------
        val guidD302A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(302.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D3.02A Card: EMV_3 Purchase SBT", guid= guidD302A, testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D3.02A Purchase, rrn:" + testResult.rrn)

//-----------------D3.03A---------------------------------------------
        val guidD303A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(303.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB, guid=guidD303A,testCard = EMV_3,
            description = "D3.03A Card: EMV_3 Purchase SBT"
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D3.03A Purchase, rrn:" + testResult.rrn)

//-----------------D3.04A---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,testCard = EMV_3,
            description = "D3.04A Card: EMV_3 Purchase Max Value SBT"
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D3.04A Purchase Max Value, rrn:" + testResult.rrn)
////Todo:Сделать Unmatched Universal Reversal
////-----------------D3.06---------------------------------------------
//
//
//-----------------D3.02B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = REVERSAL, amount = BigDecimal(302.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D3.02B Card: EMV_3 Universal Reversal D3.02A SBT", parentGuid= guidD302A,testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D3.02B Universal Reversal D3.02A, rrn:" + testResult.rrn)

//-----------------D3.03B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = REVERSAL, amount = BigDecimal(30.03),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D3.03B Card: EMV_3 Universal Reversal D3.03A SBT", parentGuid= guidD303A,testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D3.03B Universal Reversal D3.03A, rrn:" + testResult.rrn)

////-----------------D3.01B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = AUTHORISATION_CONFIRMATION, amount = BigDecimal(301.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D3.01B Card: EMV_3 Authorisation Confirmation D3.01A SBT", parentGuid= guidD301A,testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D3.01B Authorisation Confirmation D3.01A, rrn:" + testResult.rrn)

//----------------D3.07 Reconciliation------------------------------------
        testResult=MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D3.07 Reconciliation, rrn:" + testResult.rrn)

    }

    @Test
    fun testD7() {
        println("On-Line Fall Back Processing, (Terminals 1,4)")

        var testResult: TestResult
//===================================================================
        val wrongGuid="511be81d-6d58-4fb1-b4c5-4466ac5d3200"
//-----------------Reconciliation------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//-----------------D7.01A---------------------------------------------
        val guidD701A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = AUTHORISATION, amount = BigDecimal(701.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D7.01A Card: EMV_3 Authorization SBT", guid=guidD701A, testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D7.01 Authorisation, rrn:" + testResult.rrn)
//-----------------D7.02A---------------------------------------------
        val guidD702A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(702.04),
            cardHolderVerificationType = ONLINE_PIN, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D7.02A Card: EMV_3 Purchase PBT", guid=guidD702A, testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D7.02A Purchase PBT, rrn:" + testResult.rrn)
//-----------------D7.03A---------------------------------------------
        val guidD703A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(703.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D7.03A Card: EMV_3 Purchase SBT", guid = guidD703A, testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D7.03A Purchase SBT, rrn:" + testResult.rrn)

//-----------------D7.04A---------------------------------------------
        val guidD704A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = PURCHASE, amount = BigDecimal(704.04),
            cardHolderVerificationType = SIGNED, cardSlotType = MANUAL, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D7.04A Card: EMV_9 Purchase Manual", guid = guidD704A, cvv2 = Config.EMV_CARD9_CVV2,testCard = EMV_9
        )

        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D7.04A Purchase Manual, rrn:" + testResult.rrn)


//-----------------D7.06---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = REVERSAL, amount = BigDecimal(706.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D7.06 EMV_3 Unmatched Universal Reversal", parentGuid =wrongGuid, testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)
        println("D7.06 Card: EMV_3 D7.06 Unmatched Universal Reversal, rrn:" + testResult.rrn)

//-----------------D7.02B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = REVERSAL, amount = BigDecimal(702.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D7.02B Card: EMV_3 Universal Reversal D7.02A", parentGuid =guidD702A, testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("D7.02B Card: EMV_3 Universal Reversal D7.02A, rrn:" + testResult.rrn)

//-----------------D7.03B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = REVERSAL, amount = BigDecimal(70.03),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D7.03B Card: EMV_3 Universal Reversal D7.03B", parentGuid =guidD703A, testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("D7.03B Card: EMV_3 Universal Reversal D7.03B, rrn:" + testResult.rrn)

//-----------------D7.04B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = REFUND, amount = BigDecimal(704.04),
            cardHolderVerificationType = SIGNED, cardSlotType = ICC, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D7.04B Card: EMV_9 Refund D704A", parentGuid =guidD704A, testCard = EMV_9
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("D7.04B Card: EMV_9 Refund D704A, rrn:" + testResult.rrn)

//-----------------D7.01B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = AUTHORISATION_CONFIRMATION, amount = BigDecimal(701.04),
            cardHolderVerificationType = SIGNED, tid = Config.TESTS_TERMINAL_1, currency = RUB,
            description = "D7.01B Card: EMV_3 Auth Confirmation D7.01A", parentGuid =guidD701A, testCard = EMV_3
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("D7.01B Card: EMV_3 Auth Confirmation D7.01A, rrn:" + testResult.rrn)

//----------------D7.07 Reconciliation------------------------------------
        testResult=MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("D7.07 Reconciliation, rrn:" + testResult.rrn)
    }


}