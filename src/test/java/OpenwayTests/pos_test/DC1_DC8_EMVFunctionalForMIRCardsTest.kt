package OpenwayTests.pos_test

import entities.TestResult
import enums.CardHolderVerificationType.*
import enums.CardSlotType.*
import enums.OpenwayResponseCode.*
import enums.OperationType.*
import enums.TestCards.*
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency.*
import org.junit.Test
import other.Utils
import java.math.BigDecimal


class DC1_DC8_EMVFunctionalForMIRCardsTest : TestCase() {
    @Test
    fun testDC1() {
        println("DC1.01. On-Line Processing, On-Line PIN (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC1.01",
            operationType = AUTHORISATION,
            testCard = EMV_11,
            amount = BigDecimal(101.83),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Authorisation"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC1.04",
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_11,
            description = "Purchase Max Value PBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC1.07",
            operationType = PURCHASE,
            amount = BigDecimal(107.83),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_11,
            description = "Purchase Real Bad Pin 987654 PBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_PIN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC1.08",
            operationType = PURCHASE,
            amount = BigDecimal(108.83),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_11,
            description = "Purchase, Good PIN 2126 PBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DC1.12",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }

    @Test
    fun testDC2() {
        println("On-Line Processing, Offline PIN (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC2.01",
            operationType = AUTHORISATION,
            amount = BigDecimal(201.83),
            cardHolderVerificationType = OFFLINE_PIN,
            cardSlotType = ICC,
            testCard = EMV_11,
            description = "Authorisation"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC2.02",
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = OFFLINE_PIN,
            testCard = EMV_11,

            description = "Purchase Offline PIN"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC2.07",
            operationType = PURCHASE,
            amount = BigDecimal(207.83),
            cardHolderVerificationType = OFFLINE_PIN,
            testCard = EMV_11,
            description = "Purchase Real Bad Pin 987654"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DC2.10",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }


    @Test
    fun testDC3() {
        println("On-Line Processing, SBT (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        val guidDC301A= Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC3.01A",
            operationType = AUTHORISATION,
            amount = BigDecimal(301.83),
            cardHolderVerificationType = SIGNED,
            description = "Authorization SBT",
            guid = guidDC301A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDC302A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC3.02A",
            operationType = PURCHASE,
            amount = BigDecimal(302.83),
            cardHolderVerificationType = SIGNED,
            description = "Purchase SBT",
            guid= guidDC302A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDC303A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC3.03A",
            operationType = PURCHASE,
            amount = BigDecimal(303.83),
            cardHolderVerificationType = SIGNED,
            guid=guidDC303A,
            testCard = EMV_11,
            description = "Purchase SBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC3.04",
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = SIGNED,
            testCard = EMV_11,
            description = "Purchase Max Value SBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC3.06",
            operationType = REVERSAL,
            amount = BigDecimal(306.83),
            cardHolderVerificationType = SIGNED,
            description = "Unmatched Universal Reversal MAC Mandatory",
            parentGuid= Config.WRONG_GUID_RUB,
            testCard = EMV_11,
            isWithMac = true
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC3.02B",
            operationType = REVERSAL,
            amount = BigDecimal(302.83),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DC3.02A SBT",
            parentGuid= guidDC302A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC3.03B",
            operationType = REVERSAL,
            amount = BigDecimal(30.3),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DC3.03B SBT",
            parentGuid= guidDC303A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC3.01B",
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(301.83),
            cardHolderVerificationType = SIGNED,
            description = "Authorisation Confirmation DC3.01A SBT",
            parentGuid= guidDC301A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DC3.07",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

    }

    @Test
    fun testDC4() {
        println("DC4 On-Line Processing, SBT")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        val guidDC401A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC4.01A",
            operationType = PURCHASE,
            amount = BigDecimal(401.83),
            cardHolderVerificationType = SIGNED,
            description = "Purchase SBT",
            guid = guidDC401A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDC402A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC4.02A",
            operationType = PURCHASE,
            amount = BigDecimal(402.83),
            cardHolderVerificationType = SIGNED,
            description = "Purchase SBT",
            guid = guidDC402A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC4.01B",
            operationType = REVERSAL,
            amount = BigDecimal(401.83),
            description = "Universal Reversal DC4.01A",
            parentGuid = guidDC401A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC4.02B",
            operationType = REVERSAL,
            amount = BigDecimal(40.20),
            description = "Universal Reversal DC4.02A",
            parentGuid = guidDC402A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DC4.03",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }

    @Test
    fun testDC7() {
        println("On-Line Fall Back Processing, (Terminals 1,4)")

        var testResult: TestResult
//===================================================================

//---------------------------------------------------------------------
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        val guidDC701A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC7.01A",
            operationType = AUTHORISATION,
            amount = BigDecimal(701.83),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Authorization SBT",
            guid=guidDC701A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        val guidDC702A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC7.02A",
            operationType = PURCHASE,
            amount = BigDecimal(702.83),
            cardHolderVerificationType = ONLINE_PIN,
            description = "FALLBACK! Purchase PBT",
            guid=guidDC702A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//--------------------------------------------------------------
        val guidDC703A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC7.03A",
            operationType = PURCHASE,
            amount = BigDecimal(703.83),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Purchase SBT",
            guid = guidDC703A,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDC704A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC7.04A",
            operationType = PURCHASE, amount = BigDecimal(704.83),
            cardHolderVerificationType = SIGNED,
            cardSlotType = MANUAL,
            description = "Purchase Manual",
            guid = guidDC704A,
            cvv2 = EMV_11.cvv2,
            testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC7.06",
            operationType = REVERSAL,
            amount = BigDecimal(706.83),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Unmatched Universal Reversal",
            parentGuid =Config.WRONG_GUID_USD,
            testCard = EMV_11,
            currency = USD
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC7.02B",
            operationType = REVERSAL,
            amount = BigDecimal(702.83),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Universal Reversal DC7.02A",
            parentGuid =guidDC702A, testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DC7.03B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC7.03B",
            operationType = REVERSAL, amount = BigDecimal(70.30),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Universal Reversal DC7.03B", parentGuid =guidDC703A, testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC7.04B",
            operationType = REFUND, amount = BigDecimal(704.83),
            cardHolderVerificationType = SIGNED, cardSlotType = ICC,
            description = "FALLBACK! Refund DC704A", parentGuid =guidDC704A, testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//-----------------DA7.01B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC7.01B",
            operationType = AUTHORISATION_CONFIRMATION, amount = BigDecimal(701.83),
            cardHolderVerificationType = SIGNED,
            description = "Auth Confirmation DC7.01A", parentGuid =guidDC701A, testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DC7.07",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }


    @Test
    fun testDC8() {
        println("DC8 On-Line Processing Manual")

        var testResult: TestResult
//---------------------------------------------------------------------
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        val guidDC801A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC8.01A",
            operationType = PURCHASE,
            amount = BigDecimal(801.83),
            cardSlotType = MANUAL,
            description = "Purchase", guid=guidDC801A, testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        val guidDC802A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC8.02A",
            operationType = PURCHASE,
            amount = BigDecimal(802.83),
            cardSlotType = MANUAL,
            description = "Purchase", guid=guidDC802A, testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC8.01B",
            operationType = REVERSAL,
            amount = BigDecimal(801.83),
            cardSlotType = MANUAL,
            description = "Universal Reversal DC8.01A", parentGuid = guidDC801A, testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC8.02B",
            operationType = REVERSAL,
            amount = BigDecimal(80.20),
            cardSlotType = MANUAL,
            description = "Universal Reversal DC8.02A", parentGuid = guidDC802A, testCard = EMV_11
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DC8.03",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }




}