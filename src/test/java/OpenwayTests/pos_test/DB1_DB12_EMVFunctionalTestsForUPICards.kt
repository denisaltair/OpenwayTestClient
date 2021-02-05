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
import kz.multibank.cardposclient.entities.Currency.*
import org.junit.Test
import other.Utils
import java.math.BigDecimal


class DB1_DB12_EMVFunctionalTestsForUPICards : TestCase() {
    @Test
    fun testDB1() {
        println("DB1. On-Line Processing, On-Line PIN (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
       testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        val guidDB101=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB1.01",
            testCard = EMV_13,
            operationType = AUTHORISATION,
            amount = BigDecimal(101.82),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Authorisation (For Terminal 1 only)",
            cardSlotType = ICC,
            guid=guidDB101,
            tid = Config.TESTS_TERMINAL_2
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDB104=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB1.04",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase",
            cardSlotType = ICC,
            guid=guidDB104
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB1.07",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(107.82),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase, Real Bad PIN 987654",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_PIN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB1.08",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(108.82),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase, Good PIN",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("DB1.12 Reconciliation, rrn:" + testResult.rrn)

    }

    @Test
    fun testDB2() {
        println("DB2. On-Line Processing, Offline PIN (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
           testResult = MagneticCardsTesterHelper.makeReconciliation()
           println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        val guidDB201=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB2.01",
            testCard = EMV_13,
            operationType = AUTHORISATION,
            amount = BigDecimal(201.82),
            cardHolderVerificationType = OFFLINE_PIN,
            description = "Authorisation (For Terminal 1 only)",
            cardSlotType = ICC,
            guid=guidDB201
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDB202=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB2.02",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = OFFLINE_PIN,
            description = "Purchase",
            cardSlotType = ICC,
            guid=guidDB202
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB2.06",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(206.82),
            cardHolderVerificationType = OFFLINE_PIN,
            description = "Purchase, Real Bad PIN 987654",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB2.07",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(207.82),
            cardHolderVerificationType = OFFLINE_PIN,
            description = "Purchase, Good PIN",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("DB2.10 Reconciliation, rrn:" + testResult.rrn)

    }

    @Test
    fun testDB3() {
        println("DB3. On-Line Processing, SBT (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
           testResult = MagneticCardsTesterHelper.makeReconciliation()
            println("Reconciliation, rrn:" + testResult.rrn)


//--------------------------------------------------------------
        val guidDB301A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB3.01A",
            testCard = EMV_13,
            operationType = AUTHORISATION,
            amount = BigDecimal(301.82),
            cardHolderVerificationType = SIGNED,
            description = "Authorisation (For Terminal 1 only)",
            cardSlotType =ICC,
            guid=guidDB301A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        val guidDB302A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB3.02A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(302.82),
            cardHolderVerificationType = SIGNED,
            description = "Purchase",
            cardSlotType = ICC,
            guid=guidDB302A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDB303A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB3.03A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(303.82),
            cardHolderVerificationType = SIGNED,
            description = "Purchase",
            cardSlotType = ICC,
            guid=guidDB303A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB3.04",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = SIGNED,
            description = "Purchase",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB3.06",
            testCard = EMV_13,
            operationType = REVERSAL,
            amount = BigDecimal(306.82),
            cardHolderVerificationType = SIGNED,
            description = "Unmatched Universal Reversal, MAC Mandatory (For Terminal 2 only)",
            cardSlotType = ICC,
            parentGuid = guidDB301A,
            tid = Config.TESTS_TERMINAL_2,
            isWithMac = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_AMOUNT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB3.02B",
            testCard = EMV_13,
            operationType = REVERSAL,
            amount = BigDecimal(302.82),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DB3.02A",
            cardSlotType = ICC,
            parentGuid = guidDB302A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB3.03B",
            testCard = EMV_13,
            operationType = REVERSAL,
            amount = BigDecimal(30.30),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DB3.03A",
            cardSlotType = ICC,
            parentGuid = guidDB303A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB3.01B",
            testCard = EMV_13,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(301.82),
            cardHolderVerificationType = SIGNED,
            description = "Authorisation Confirmation DB3.01A (For Terminal 1 only)",
            cardSlotType = ICC,
            parentGuid = guidDB301A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("DB3.07 Reconciliation, rrn:" + testResult.rrn)
    }

    @Test
    fun testDB4() {
        println("DB4. On-Line Processing, SBT (Terminals 3)")

        var testResult: TestResult
//===================================================================
//        testResult = MagneticCardsTesterHelper.makeReconciliation()
//        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        val guidDB401A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB4.01A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(401.82),
            cardHolderVerificationType = SIGNED,
            description = "Purchase (Terminal 3)",
            cardSlotType = ICC,
            guid=guidDB401A,
            tid=Config.TESTS_TERMINAL_3
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        return
//--------------------------------------------------------------
        val guidDB402A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB4.02A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(402.82),
            cardHolderVerificationType = SIGNED,
            description = "Purchase (Terminal 3)",
            cardSlotType = ICC,
            guid=guidDB402A,
            tid=Config.TESTS_TERMINAL_3
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB4.01B",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(401.82),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DB4.01A",
            cardSlotType = ICC,
            guid=guidDB401A,
            tid=Config.TESTS_TERMINAL_3
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB4.02B",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(401.20),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DB4.02A",
            cardSlotType = ICC,
            guid=guidDB402A,
            tid=Config.TESTS_TERMINAL_3
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("DB4.03 Reconciliation, rrn:" + testResult.rrn)
    }

    @Test
    fun testDB7() {
        println("DB7. On-Line Fallback Processing (Terminals 1)")

        var testResult: TestResult
//===================================================================
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        val guidDB701A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB7.01A",
            testCard = EMV_13,
            operationType = AUTHORISATION,
            amount = BigDecimal(701.82),
            cardHolderVerificationType = SIGNED,
            description = "Authorisation",
            cardSlotType = ICC,
            guid=guidDB701A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDB702A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB7.02A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(702.82),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase PBT",
            cardSlotType = ICC,
            guid=guidDB702A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDB703A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB7.03A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(703.82),
            cardHolderVerificationType = SIGNED,
            description = "Purchase SBT",
            cardSlotType = ICC,
            guid=guidDB703A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDB704A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB7.04A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(704.82),
            cardHolderVerificationType = SIGNED,
            description = "Purchase Manual",
            cardSlotType = MANUAL,
            guid=guidDB704A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB7.06",
            testCard = EMV_13,
            operationType = REVERSAL,
            amount = BigDecimal(706.82),
            cardHolderVerificationType = SIGNED,
            description = "Unmatched Universal Reversal",
            cardSlotType = ICC,
            currency = USD,
            parentGuid = guidDB702A

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_AMOUNT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB7.02B",
            testCard = EMV_13,
            operationType = REVERSAL,
            amount = BigDecimal(702.82),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DB702A",
            cardSlotType = ICC,
            parentGuid = guidDB702A

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB7.03B",
            testCard = EMV_13,
            operationType = REVERSAL,
            amount = BigDecimal(70.3),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DB703A",
            cardSlotType = ICC,
            parentGuid = guidDB703A

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB7.04B",
            testCard = EMV_13,
            operationType = REFUND,
            amount = BigDecimal(704.82),
            cardHolderVerificationType = SIGNED,
            description = "Refuns DB703A",
            cardSlotType = ICC,
            parentGuid = guidDB704A

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB7.01B",
            testCard = EMV_13,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(701.82),
            cardHolderVerificationType = SIGNED,
            description = "Auth Confirmation DB701A",
            cardSlotType = ICC,
            parentGuid = guidDB701A

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("DB7.07 Reconciliation, rrn:" + testResult.rrn)
    }

    @Test
    fun testDB8() {
        println("DB8. On-Line Processing, Manual (Terminal 3)")

        var testResult: TestResult
//===================================================================
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        val guidDB801A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB8.01A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(801.82),
            cardHolderVerificationType = SIGNED,
            description = "Purchase Manual",
            cardSlotType = MANUAL,
            tid = Config.TESTS_TERMINAL_3,
            guid=guidDB801A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        val guidDB802A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB8.02A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(802.82),
            cardHolderVerificationType = SIGNED,
            description = "Purchase Manual",
            cardSlotType = MANUAL,
            tid = Config.TESTS_TERMINAL_3,
            guid=guidDB801A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB8.01B",
            testCard = EMV_13,
            operationType = REVERSAL,
            amount = BigDecimal(802.82),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DB801A",
            cardSlotType = MANUAL,
            tid=Config.TESTS_TERMINAL_3,
            parentGuid = guidDB801A

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB8.02B",
            testCard = EMV_13,
            operationType = REVERSAL,
            amount = BigDecimal(80.20),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DB802A",
            cardSlotType = MANUAL,
            tid=Config.TESTS_TERMINAL_3,
            parentGuid = guidDB802A

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("DB8.03 Reconciliation, rrn:" + testResult.rrn)
    }

    @Test
    fun testDB14() {
        println("DB14.  On-Line/OffLine Contactless Processing, SBT, UnionPay QuickPass qUICS  (Terminal 1,2)")

        var testResult: TestResult
//===================================================================
//        testResult = MagneticCardsTesterHelper.makeReconciliation()
//        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        val guidDB1401A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB14.01A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(10),
            cardHolderVerificationType = SIGNED,
            description = "Authorization",
            cardSlotType = RF,
            guid = guidDB1401A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        return
//--------------------------------------------------------------
        val guidDB1402A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB14.02A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(14.84),
            cardHolderVerificationType = SIGNED,
            description = "Purchase",
            cardSlotType = RF,
            guid = guidDB1402A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "DB14.03",
//            testCard = EMV_13,
//            operationType = PURCHASE,
//            amount = BigDecimal(14.85),
//            cardHolderVerificationType = SIGNED,
//            description = "Purchase",
//            cardSlotType = RF
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "DB14.02B",
//            testCard = EMV_13,
//            operationType = REVERSAL,
//            amount = BigDecimal(14.84),
//            cardHolderVerificationType = SIGNED,
//            description = "Universal Reversal DB14.02A",
//            cardSlotType = RF,
//            parentGuid = guidDB1402A
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "DB14.01A",
//            testCard = EMV_13,
//            operationType = AUTHORISATION_CONFIRMATION,
//            amount = BigDecimal(14.83),
//            cardHolderVerificationType = SIGNED,
//            description = "Authorisation Confirmation DB14.01A",
//            cardSlotType = RF,
//            parentGuid = guidDB1401A
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("DB14.05 Reconciliation, rrn:" + testResult.rrn)
    }

    @Test
    fun testDB15() {
        println("DB15.  On-Line/OffLine Contactless Processing, PBT, UnionPay QuickPass qUICS  (Terminal 1,2)")

        var testResult: TestResult
//===================================================================
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        val guidDB1501A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB15.01A",
            testCard = EMV_13,
            operationType = AUTHORISATION,
            amount = BigDecimal(15.83),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Authorization",
            cardSlotType = RF,
            tid = Config.TESTS_TERMINAL_1,
            guid = guidDB1501A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        val guidDB1502A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB15.02A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(15.84),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase",
            cardSlotType = RF,
            guid = guidDB1502A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB15.03",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(15.85),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB15.02B",
            testCard = EMV_13,
            operationType = REVERSAL,
            amount = BigDecimal(15.84),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Universal Reversal DB15.02A",
            cardSlotType = RF,
            parentGuid = guidDB1502A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB15.01A",
            testCard = EMV_13,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(15.83),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Authorisation Confirmation DB14.01A",
            cardSlotType = RF,
            parentGuid = guidDB1501A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("DB15.05 Reconciliation, rrn:" + testResult.rrn)
    }

    @Test
    fun testDB16() {
        println("DB16. On-Line/Off-Line Contact Processing, PBT, UnionPay QuickPass  (Terminal 1,2)")

        var testResult: TestResult
//===================================================================
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)
//--------------------------------------------------------------
        val guidDB1601A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB16.01A",
            testCard = EMV_13,
            operationType = AUTHORISATION,
            amount = BigDecimal(16.83),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Authorization",
            cardSlotType = ICC,
            guid = guidDB1601A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDB1602A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB16.02A",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(16.84),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase",
            cardSlotType = ICC,
            guid = guidDB1602A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB16.03",
            testCard = EMV_13,
            operationType = PURCHASE,
            amount = BigDecimal(16.85),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB16.02B",
            testCard = EMV_13,
            operationType = REVERSAL,
            amount = BigDecimal(16.84),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Universal Reversal DB16.02A",
            cardSlotType = ICC,
            parentGuid = guidDB1602A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB16.01A",
            testCard = EMV_13,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(16.83),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Authorisation Confirmation DB16.01A",
            cardSlotType = ICC,
            parentGuid = guidDB1601A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = MagneticCardsTesterHelper.makeReconciliation()
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
        println("DB15.05 Reconciliation, rrn:" + testResult.rrn)
    }

}