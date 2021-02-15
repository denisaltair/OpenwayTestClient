package OpenwayTests.pos_test

import entities.TestResult
import enums.*
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import org.junit.Test
import other.Utils
import java.math.BigDecimal

class R1_R3_Test {
    //R1.  Decline Response Codes (Terminal 1 with MAC and without MAC)
    @Test
    fun testR1() {
        println("R1. Decline Response Codes")

        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.01",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(10.0),
            description = "Refer to Card Issuer. Key in Auth Code=999999, then upload transaction as offline.",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("01"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.02",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(20.0),
            description = "Refer to card issuer's special condition. Key in Auth Code=999999, then upload transaction as offline.",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("02"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.03",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(30.0),
            description = "Invalid merchant",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("03"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.05",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(50.0),
            description = "Do not honour",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("05"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.06",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(60.0),
            description = "Error",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("05"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.09",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(90.0),
            description = "Request in progress",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("09"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.12",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(120.0),
            description = "Invalid Transaction",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("12"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.13",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(130.0),
            description = "Invalid Amount",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("13"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.14",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(140.0),
            description = "Invalid CardNumber",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("14"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.15",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(150.0),
            description = "No such issue",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("15"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.17",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(170.0),
            description = "Customer Cancellation",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("17"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.18",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(180.0),
            description = "Customer dispute",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("18"))


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.19",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(190.0),
            description = "Re-enter transaction",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("19"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.20",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(200.0),
            description = "Invalid response",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("20"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.21",
            testCard = TestCards.MAG_6,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(200.0),
            description = "Invalid response",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOfFromCode("20"))

    }
}