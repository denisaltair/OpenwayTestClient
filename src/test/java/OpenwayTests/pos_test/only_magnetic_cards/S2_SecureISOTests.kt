package OpenwayTests.pos_test.only_magnetic_cards

import entities.TestResult
import enums.*
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import org.junit.Test
import other.Utils

import java.math.BigDecimal

class S2_SecureISOTests: TestCase() {
    //S2. Secure ISO, 3-DES encryption (Terminal 1 with MAC and without MAC)*
    @Test
    fun testS2() {
        println("S2. Secure ISO, 3-DES encryption (with MAC")

        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.01",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(201.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.02",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(202.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.03",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(203.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.04",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(204.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.05",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(205.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.06",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(206.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.07",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(207.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.08",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(208.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.09",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(209.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.10",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(210.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        val guidS211A= Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.11A",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(211.08),
            description = "Purchase",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true,
            guid=guidS211A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.SERVER_NOT_RESPONDING)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.11B",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(211.08),
            description = "Purchase Repeat S2.11A",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true,
            isRepeat = true,
            guid=guidS211A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.SERVER_NOT_RESPONDING)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "S2.11C",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(211.08),
            description = "Purchase Repeat S2.11A",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithSecureIso = true,
            parentGuid =guidS211A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }
}