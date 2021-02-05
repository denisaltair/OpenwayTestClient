package OpenwayTests.pos_test

import entities.TestResult
import enums.CardHolderVerificationType.*
import enums.CardSlotType.*
import enums.OpenwayResponseCode.*
import enums.OperationType.*
import enums.TestCards.*
import helpers.EmvCardsTesterHelper
import helpers.MagneticCardsTesterHelper
import junit.framework.TestCase
import org.junit.Test
import other.Utils
import java.math.BigDecimal


class D15_D24_EmvFunctionalContactlessTests : TestCase() {
    @Test
    fun testD17() {
        println("D17. On-Line/OffLine Contactless Processing, SBT, Visa PayWave qVSDC Profile  (Terminal 1,2)")

        var testResult: TestResult
//===================================================================
//-------------------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        var guidD1701A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D17.01A",
            testCard = EMV_10,
            operationType = AUTHORISATION,
            amount = BigDecimal(17.04),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation (For Terminal 1 only)",
            cardSlotType = RF,
            guid = guidD1701A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        var guidD1702A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D17.02A",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(17.05),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF,
            guid = guidD1702A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D17.03",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(17.06),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D17.02B",
            testCard = EMV_10,
            operationType = REVERSAL,
            amount = BigDecimal(17.05),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Universal Reversal D17.02A",
            cardSlotType = RF,
            parentGuid = guidD1702A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D17.01B",
            testCard = EMV_10,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(17.04),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation Confirmation D17.01A (For Terminal 1 only)",
            cardSlotType = RF,
            parentGuid = guidD1701A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D17.04",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(0.17),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D17.05 Reconciliation, rrn:" + testResult.rrn)


    }

    @Test
    fun testD18() {
        println("D18. On-Line/Offline Contactless Processing, PBT, Visa PayWave qVSDC Profile  (Terminal 1,2)")

        var testResult: TestResult
//===================================================================
//-------------------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        var guidD1801A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D18.01A",
            testCard = EMV_10,
            operationType = AUTHORISATION,
            amount = BigDecimal(18.04),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation (For Terminal 1 only)",
            cardSlotType = RF,
            guid = guidD1801A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        var guidD1802A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D18.02A",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(18.05),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF,
            guid = guidD1802A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D18.03",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(18.06),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D18.02B",
            testCard = EMV_10,
            operationType = REVERSAL,
            amount = BigDecimal(18.05),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Universal Reversal D18.02A",
            cardSlotType = RF,
            parentGuid = guidD1802A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D18.01B",
            testCard = EMV_10,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(18.04),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation Confirmation D18.01A (For Terminal 1 only)",
            cardSlotType = RF,
            parentGuid = guidD1801A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D18.04",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(0.18),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D18.05 Reconciliation, rrn:" + testResult.rrn)

    }

    @Test
    fun testD19() {
        println("D19. On-Line/Off-Line Contact Processing, PBT, Visa PayWave  (Terminal 1,2)")

        var testResult: TestResult
//===================================================================
//-------------------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        var guidD1901A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D19.01A",
            testCard = EMV_10,
            operationType = AUTHORISATION,
            amount = BigDecimal(19.04),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation (For Terminal 1 only)",
            cardSlotType = ICC,
            guid = guidD1901A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        var guidD1902A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D19.02A",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(19.05),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = ICC,
            guid = guidD1902A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D19.03",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(19.06),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D19.02B",
            testCard = EMV_10,
            operationType = REVERSAL,
            amount = BigDecimal(19.05),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Universal Reversal D19.02A",
            cardSlotType = ICC,
            parentGuid = guidD1902A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D19.01B",
            testCard = EMV_10,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(19.04),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation Confirmation D19.01A (For Terminal 1 only)",
            cardSlotType = ICC,
            parentGuid = guidD1901A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D19.04",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(0.19),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D19.05 Reconciliation, rrn:" + testResult.rrn)
    }


    @Test
    fun testD22() {
        println("D22. On-Line/OffLine Contactless Processing, SBT, MC PayPass MChip Mode (Terminal 1,2)")
        var testResult: TestResult
//===================================================================
//-----------------Reconciliation------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)

        //--------------------------------------------------------------
        var guidD2201A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D22.01A",
            testCard = EMV_9,
            operationType = AUTHORISATION,
            amount = BigDecimal(22.04),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation",
            cardSlotType = RF,
            guid = guidD2201A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        var guidD2202A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D22.02A",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(22.05),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF,
            guid = guidD2202A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D22.03",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(22.06),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D22.02B",
            testCard = EMV_9,
            operationType = REVERSAL,
            amount = BigDecimal(22.05),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Universal Reversal D22.02A",
            cardSlotType = RF,
            parentGuid = guidD2202A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D22.01B",
            testCard = EMV_9,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(22.04),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation Confirmation D22.01A (For Terminal 1 only)",
            cardSlotType = RF,
            parentGuid = guidD2201A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D22.04",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(0.22),
            cardHolderVerificationType = SIGNED,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D22.05 Reconciliation, rrn:" + testResult.rrn)

    }

    @Test
    fun testD23() {
        println("D23. On-Line/Offline Contactless Processing, PBT, MC PayPass MChip Mode  (Terminal 1,2)")
        var testResult: TestResult
//===================================================================
//-----------------Reconciliation------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)

        //--------------------------------------------------------------
        var guidD2301A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D23.01A",
            testCard = EMV_9,
            operationType = AUTHORISATION,
            amount = BigDecimal(23.04),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation",
            cardSlotType = RF,
            guid = guidD2301A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        var guidD2302A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D23.02A",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(23.05),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF,
            guid = guidD2302A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D23.03",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(23.06),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D23.02B",
            testCard = EMV_9,
            operationType = REVERSAL,
            amount = BigDecimal(23.05),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Universal Reversal D23.02A",
            cardSlotType = RF,
            parentGuid = guidD2302A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D23.01B",
            testCard = EMV_9,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(23.04),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation Confirmation D23.01A (For Terminal 1 only)",
            cardSlotType = RF,
            parentGuid = guidD2301A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D23.04",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(0.23),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D23.05 Reconciliation, rrn:" + testResult.rrn)

    }

    @Test
    fun testD24() {
        println("D24. On-Line/Off-Line Contact Processing, PBT, MC PayPass  (Terminal 1,2)")

        var testResult: TestResult
//===================================================================
//-------------------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        var guidD2401A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D24.01A",
            testCard = EMV_9,
            operationType = AUTHORISATION,
            amount = BigDecimal(24.04),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation (For Terminal 1 only)",
            cardSlotType = ICC,
            guid = guidD2401A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        var guidD2402A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D24.02A",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(24.05),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = ICC,
            guid = guidD2402A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D24.03",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(24.06),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D24.02B",
            testCard = EMV_9,
            operationType = REVERSAL,
            amount = BigDecimal(24.05),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Universal Reversal D24.02A",
            cardSlotType = ICC,
            parentGuid = guidD2402A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D24.01B",
            testCard = EMV_9,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(24.04),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Authorisation Confirmation D24.01A (For Terminal 1 only)",
            cardSlotType = ICC,
            parentGuid = guidD2401A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D24.04",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(0.24),
            cardHolderVerificationType = ONLINE_PIN,
            tid = Config.TESTS_TERMINAL_1,
            description = "Purchase",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("D24.05 Reconciliation, rrn:" + testResult.rrn)
    }

}