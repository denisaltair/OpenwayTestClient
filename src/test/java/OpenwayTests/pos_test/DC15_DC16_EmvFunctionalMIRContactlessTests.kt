package OpenwayTests.pos_test

import entities.TestResult
import enums.CardHolderVerificationType.*
import enums.CardSlotType.*
import enums.OpenwayResponseCode.*
import enums.OperationType.*
import enums.TestCards.*
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import org.junit.Test
import other.Utils
import java.math.BigDecimal


class DC15_DC16_EmvFunctionalMIRContactlessTests : TestCase() {
    @Test
    fun testDC15() {
        println("DC15. On-Line/Off-Line Contactless Processing, SBT, MIR Contactless  (Terminal 1,2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        var guidDC1501 = Utils.getGUID()
            testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC15.01",
            testCard = EMV_11,
            operationType = AUTHORISATION,
            amount = BigDecimal(15.04),
            cardHolderVerificationType = SIGNED,
            description = "Authorisation (For Terminal 1 only)",
            cardSlotType = RF,
            guid = guidDC1501
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        var guidDC1502 = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC15.02",
            testCard = EMV_11,
            operationType = PURCHASE,
            amount = BigDecimal(15.05),
            cardHolderVerificationType = SIGNED,
            description = "Purchase",
            cardSlotType = RF,
            guid = guidDC1502
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC15.03",
            testCard = EMV_11,
            operationType = PURCHASE,
            amount = BigDecimal(15.06),
            cardHolderVerificationType = SIGNED,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC15.02",
            testCard = EMV_11,
            operationType = REVERSAL,
            amount = BigDecimal(15.05),
            cardHolderVerificationType = SIGNED,
            description = "Universal Reversal DC15.02A",
            cardSlotType = RF,
            parentGuid = guidDC1502
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC15.01",
            testCard = EMV_11,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(15.04),
            cardHolderVerificationType = SIGNED,
            description = "Authorisation Confirmation DC15.01A",
            cardSlotType = RF,
            parentGuid = guidDC1501
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DC15.05",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


    }

    @Test
    fun testDC16() {
        println("DC16. On-Line/Off-Line Contactless Processing, PBT, MIR Contactless  (Terminal 1,2)")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        var guidDC1601 = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC16.01",
            testCard = EMV_11,
            operationType = AUTHORISATION,
            amount = BigDecimal(16.04),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Authorisation (For Terminal 1 only)",
            cardSlotType = RF,
            guid = guidDC1601
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        var guidDC1602 = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC16.02",
            testCard = EMV_11,
            operationType = PURCHASE,
            amount = BigDecimal(16.05),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase",
            cardSlotType = RF,
            guid = guidDC1602
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC16.03",
            testCard = EMV_11,
            operationType = PURCHASE,
            amount = BigDecimal(16.06),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Purchase",
            cardSlotType = RF
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC16.02",
            testCard = EMV_11,
            operationType = REVERSAL,
            amount = BigDecimal(16.05),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Universal Reversal DC16.02A",
            cardSlotType = RF,
            parentGuid = guidDC1602
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DC16.01",
            testCard = EMV_11,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(16.04),
            cardHolderVerificationType = ONLINE_PIN,
            description = "Authorisation Confirmation DC16.01A",
            cardSlotType = RF,
            parentGuid = guidDC1601
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "DC16.05",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


    }


}