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

class S2_AdditionalSecureISOTests : TestCase() {

    //S2. Secure ISO, 3-DES encryption (Terminal 1 with MAC and without MAC)*
    @Test
    fun testV1() {
        println("S2. Secure ISO, 3-DES encryption (Terminal 1 with MAC and without MAC)*")
        var guid = ""
        var testResult: TestResult

//===================================================================
//-----------------S2.01---------------------------------------------
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(201.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("S2.01, rrn:" + testResult.rrn)

//-----------------S2.02---------------------------------------------
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(202.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("S2.02, rrn:" + testResult.rrn)

//-----------------S2.03---------------------------------------------
    testResult = MagneticCardsTesterHelper.sendRequest(
    operationType = OperationType.PURCHASE, amount = BigDecimal(203.08),
    pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
    )

    assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    println("S2.03, rrn:" + testResult.rrn)

//-----------------S2.04---------------------------------------------
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(204.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("S2.04, rrn:" + testResult.rrn)

//-----------------S2.05---------------------------------------------
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(205.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("S2.05, rrn:" + testResult.rrn)

//-----------------S2.06---------------------------------------------
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(206.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("S2.06, rrn:" + testResult.rrn)

//-----------------S2.07---------------------------------------------
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(207.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("S2.07, rrn:" + testResult.rrn)

//-----------------S2.08---------------------------------------------
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(208.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("S2.08, rrn:" + testResult.rrn)

//-----------------S2.09---------------------------------------------
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(209.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("S2.09, rrn:" + testResult.rrn)

//-----------------S2.10---------------------------------------------
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(210.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("S2.10, rrn:" + testResult.rrn)

//-----------------S2.11A---------------------------------------------
        guid=Utils.getGUID()
        Config.tryToRepeatIfServerNotResponding=1
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(211.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, guid=guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.SERVER_NOT_RESPONDING)
        println("S2.11A, rrn:" + testResult.rrn)
        Config.tryToRepeatIfServerNotResponding=10

//-----------------S2.11C---------------------------------------------
        testResult = MagneticCardsTesterHelper.sendRequest(
            operationType = OperationType.AUTO_REVERSAL, amount = BigDecimal(211.08),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("S2.11C, rrn:" + testResult.rrn)
        Config.tryToRepeatIfServerNotResponding=10

    }




}