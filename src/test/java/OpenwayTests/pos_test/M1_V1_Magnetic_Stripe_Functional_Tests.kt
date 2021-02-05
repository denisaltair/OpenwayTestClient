package OpenwayTests.pos_test

import entities.TestResult
import enums.*
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import org.junit.Test
import other.Utils
import java.math.BigDecimal

class M1_V1_Magnetic_Stripe_Functional_Tests : TestCase() {
    //M1. PIN-Based Transactions (Terminals 1, 2)
    @Test
    fun testM1() {
        println("M1.01 PIN-Based Transactions")

        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.01",
            testCard = TestCards.MAG_1,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(101.00),
            description = "",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.02",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(102.00),
            description = "",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.03",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            description = "",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.04",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(104.00),
            description = "",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

    }


}