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


class DA1_DA8_EMVFunctionalForAMEXCardsTest : TestCase() {
    @Test
    fun testDA1() {
        println("DA1.01. On-Line Processing, On-Line PIN (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//-----------------DA1.01---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA1.01",
            operationType = AUTHORISATION,
            testCard = EMV_4,
            amount = BigDecimal(101.04),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Authorisation"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA1.04---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA1.04",
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_4,
            description = "Purchase Max Value PBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA1.07---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA1.07",
            operationType = PURCHASE,
            amount = BigDecimal(107.81),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_4,
            description = "Purchase Real Bad Pin 987654 PBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_PIN)

//-----------------DA1.08---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA1.08",
            operationType = PURCHASE,
            amount = BigDecimal(108.81),
            cardHolderVerificationType = ONLINE_PIN,
            testCard = EMV_4,
            description = "Purchase, Good PIN 2126 PBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DA1.12",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }

    @Test
    fun testDA2() {
        println("On-Line Processing, Offline PIN (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//-----------------DA2.01---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA2.01",
            operationType = AUTHORISATION,
            amount = BigDecimal(201.81),
            cardHolderVerificationType = OFFLINE_PIN,
            cardSlotType = ICC,
            testCard = EMV_4,
            description = "Authorisation"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA2.02---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA2.02",
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = OFFLINE_PIN,
            testCard = EMV_4,

            description = "Purchase Offline PIN"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

////-----------------D2.06---------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "DA2.06",
//            operationType = PURCHASE,
//            amount = BigDecimal(206.81),
//            cardHolderVerificationType = OFFLINE_PIN,
//            testCard = EMV_4,
//            description = "Purchase Real Bad Pin 987654"
//        )
//
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, WRONG_PIN)

//-----------------D2.07---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA2.07",
            operationType = PURCHASE,
            amount = BigDecimal(207.81),
            cardHolderVerificationType = OFFLINE_PIN,
            testCard = EMV_4,
            description = "Purchase Real Bad Pin 987654"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DA2.10",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }


    @Test
    fun testDA3() {
        println("On-Line Processing, SBT (Terminals 1, 2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//-----------------DA3.01A---------------------------------------------
        val guidDA301A= Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA3.01A",
            operationType = AUTHORISATION,
            amount = BigDecimal(301.81),
            cardHolderVerificationType = SIGNED,
            description = "Authorization SBT",
            guid = guidDA301A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA3.02A---------------------------------------------
        val guidDA302A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA3.02A",
            operationType = PURCHASE,
            amount = BigDecimal(302.81),
            cardHolderVerificationType = SIGNED,
            description = "Purchase SBT",
            guid= guidDA302A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA3.03A---------------------------------------------
        val guidDA303A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA3.03A",
            operationType = PURCHASE,
            amount = BigDecimal(303.81),
            cardHolderVerificationType = SIGNED,
            guid=guidDA303A,
            testCard = EMV_4,
            description = "Purchase SBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA3.04---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA3.04",
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            cardHolderVerificationType = SIGNED,
            testCard = EMV_4,
            description = "Purchase Max Value SBT"
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA3.06---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA3.06",
            operationType = REVERSAL,
            amount = BigDecimal(306.81),
            cardHolderVerificationType = SIGNED,
            description = "Unmatched Universal Reversal MAC Mandatory",
            parentGuid= Config.WRONG_GUID_RUB,
            testCard = EMV_4,
            isWithMac = true
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)

//-----------------DA3.02B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA3.02B",
            operationType = REVERSAL,
            amount = BigDecimal(302.81),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DA3.02A SBT",
            parentGuid= guidDA302A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA3.03B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA3.03B",
            operationType = REVERSAL,
            amount = BigDecimal(30.3),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DA3.03B SBT",
            parentGuid= guidDA303A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

////-----------------DA3.01B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA3.01B",
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(301.81),
            cardHolderVerificationType = SIGNED,
            description = "Authorisation Confirmation DA3.01A SBT",
            parentGuid= guidDA301A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DA3.07",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

    }

    @Test
    fun testDA4() {
        println("DA4 On-Line Processing, SBT")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        val guidDA401A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA4.01A",
            operationType = PURCHASE,
            amount = BigDecimal(401.81),
            cardHolderVerificationType = SIGNED,
            description = "Purchase SBT",
            guid = guidDA401A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidDA402A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA4.02A",
            operationType = PURCHASE,
            amount = BigDecimal(402.81),
            cardHolderVerificationType = SIGNED,
            description = "Purchase SBT",
            guid = guidDA402A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA4.01B",
            operationType = REVERSAL,
            amount = BigDecimal(401.81),
            description = "Universal Reversal DA4.01A",
            parentGuid = guidDA401A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA4.02B",
            operationType = REVERSAL,
            amount = BigDecimal(40.20),
            description = "Universal Reversal DA4.02A",
            parentGuid = guidDA402A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DA4.03",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }

    @Test
    fun testDA7() {
        println("On-Line Fall Back Processing, (Terminals 1,4)")

        var testResult: TestResult
//===================================================================

//---------------------------------------------------------------------
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//-----------------D7.01A---------------------------------------------
        val guidDA701A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA7.01A",
            operationType = AUTHORISATION,
            amount = BigDecimal(701.81),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Authorization SBT",
            guid=guidDA701A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//-----------------DA7.02A---------------------------------------------
        val guidDA702A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA7.02A",
            operationType = PURCHASE,
            amount = BigDecimal(702.81),
            cardHolderVerificationType = ONLINE_PIN,
            description = "FALLBACK! Purchase PBT",
            guid=guidDA702A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//-----------------D7.03A---------------------------------------------
        val guidDA703A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.03A",
            operationType = PURCHASE,
            amount = BigDecimal(703.81),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Purchase SBT",
            guid = guidDA703A,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA7.04A---------------------------------------------
        val guidDA704A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.04A",
            operationType = PURCHASE, amount = BigDecimal(704.81),
            cardHolderVerificationType = SIGNED,
            cardSlotType = MANUAL,
            description = "Purchase Manual",
            guid = guidDA704A,
            cvv2 = EMV_4.cvv2,
            testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//-----------------DA7.06---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA7.06",
            operationType = REVERSAL,
            amount = BigDecimal(706.81),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Unmatched Universal Reversal",
            parentGuid =Config.WRONG_GUID_USD,
            testCard = EMV_4,
            currency = USD
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)


//-----------------D7.02B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA7.02B",
            operationType = REVERSAL,
            amount = BigDecimal(702.81),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Universal Reversal DA7.02A",
            parentGuid =guidDA702A, testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA7.03B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA7.03B",
            operationType = REVERSAL, amount = BigDecimal(70.30),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK! Universal Reversal DA7.03B", parentGuid =guidDA703A, testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//-----------------DA7.04B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D7.04B",
            operationType = REFUND, amount = BigDecimal(704.81),
            cardHolderVerificationType = SIGNED, cardSlotType = ICC,
            description = "FALLBACK! Refund DA704A", parentGuid =guidDA704A, testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//-----------------DA7.01B---------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA7.01B",
            operationType = AUTHORISATION_CONFIRMATION, amount = BigDecimal(701.81),
            cardHolderVerificationType = SIGNED,
            description = "Auth Confirmation DA7.01A", parentGuid =guidDA701A, testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DA7.07",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }


    @Test
    fun testDA8() {
        println("DA8 On-Line Processing Manual")

        var testResult: TestResult
//---------------------------------------------------------------------
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//-----------------DA8.01A---------------------------------------------
        val guidDA801A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA8.01A",
            operationType = PURCHASE,
            amount = BigDecimal(801.81),
            cardSlotType = MANUAL,
            description = "Purchase", guid=guidDA801A, testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        val guidDA802A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D8.02A",
            operationType = PURCHASE,
            amount = BigDecimal(802.81),
            cardSlotType = MANUAL,
            description = "Purchase", guid=guidDA802A, testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA8.01B",
            operationType = REVERSAL,
            amount = BigDecimal(801.81),
            cardSlotType = MANUAL,
            description = "Universal Reversal DA8.01A", parentGuid = guidDA801A, testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DA8.02B",
            operationType = REVERSAL,
            amount = BigDecimal(80.20),
            cardSlotType = MANUAL,
            description = "Universal Reversal DA8.02A", parentGuid = guidDA802A, testCard = EMV_4
        )

        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DA8.03",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }




}