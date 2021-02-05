package OpenwayTests.magnetic_cards_tests

import entities.TestResult
import enums.OpenwayResponseCode
import enums.OperationType
import helpers.MagneticCardsTesterHelper
import junit.framework.TestCase

import kz.multibank.cardposclient.entities.EntryMode
import org.junit.Test
import other.Utils
import java.math.BigDecimal


class T1_T14_MagneticStripeTimeoutTests : TestCase() {

    @Test
    fun testT1() {
        println("T1. Authorisation Timeout (Terminal 1)")
        var guid = ""
        var testResult: TestResult
//===================================================================
//-----------------T1.01A---------------------------------------------
        val tryToRepeatOld=Config.tryToRepeatIfServerNotResponding
        Config.tryToRepeatIfServerNotResponding=1
        guid= Utils.getGUID()
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(101.03),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1,
            guid=guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.SERVER_NOT_RESPONDING)
        println("T1.01A Authorisation, rrn:" + testResult.rrn)

//-----------------T1.01C---------------------------------------------
        Config.tryToRepeatIfServerNotResponding=tryToRepeatOld
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.AUTO_REVERSAL, amount = BigDecimal(101.03),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("T1.01C Automatic Reversal of T1.01A, rrn:" + testResult.rrn)

    }

    @Test
    fun testT2() {
        println("T2. Purchase Timeout (Terminal 1)*")
        var guid = ""
        var testResult: TestResult
//===================================================================
//-----------------T2.01---------------------------------------------
        var tryToRepeatOld=Config.tryToRepeatIfServerNotResponding
        Config.tryToRepeatIfServerNotResponding=0
        guid= Utils.getGUID()
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(201.03),
            pan = Config.CARD7_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.TIMEOUT_AT_ISSUER_SYSTEM_BAD_CVV_VISA)
        println("T2.01A Network Timeout, rrn:" + testResult.rrn)

//-----------------T2.02A---------------------------------------------
        Config.tryToRepeatIfServerNotResponding=2
        guid= Utils.getGUID()
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(202.03),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1,
            guid=guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("T2.02A Purchase, rrn:" + testResult.rrn)

//-----------------T2.02D---------------------------------------------
        Config.tryToRepeatIfServerNotResponding=tryToRepeatOld
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.AUTO_REVERSAL, amount = BigDecimal(202.03),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("T2.02D Automatic Reversal of T2.02A, rrn:" + testResult.rrn)

    }


    @Test
    fun testT5() {
        println("T5. Retail Authorisation Confirmation Timeout (Terminal 1) ")
        var guid = ""
        var testResult: TestResult
//===================================================================
//-----------------T5.01A---------------------------------------------
        val tryToRepeatOld=Config.tryToRepeatIfServerNotResponding
        guid= Utils.getGUID()
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(501.03),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1,
            guid=guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("T5.01A Authorisation, rrn:" + testResult.rrn)

//-----------------T5.01D---------------------------------------------
        Config.tryToRepeatIfServerNotResponding=3
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION_CONFIRMATION, amount = BigDecimal(501.03),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("T5.01C Confirmation T5.01A, rrn:" + testResult.rrn)

    }

    @Test
    fun testT7() {
        println("T7. Retail Refund Timeout (Terminal 1)*")
        var guid = ""
        var testResult: TestResult
//===================================================================
//-----------------T7.01A---------------------------------------------
        val tryToRepeatOld=Config.tryToRepeatIfServerNotResponding
        guid= Utils.getGUID()
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(701.03),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1,
            guid=guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("T7.01A Purchase, rrn:" + testResult.rrn)

//-----------------T7.01B---------------------------------------------
        Config.tryToRepeatIfServerNotResponding=3
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.REFUND, amount = BigDecimal(701.03),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("T7.01B Refund T7.01A, rrn:" + testResult.rrn)

    }



}