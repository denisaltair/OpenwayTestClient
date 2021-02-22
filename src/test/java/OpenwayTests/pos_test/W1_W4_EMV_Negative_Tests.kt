package OpenwayTests.pos_test

import entities.TestResult
import enums.CardHolderVerificationType.*
import enums.CardSlotType.*
import enums.OpenwayResponseCode
import enums.OpenwayResponseCode.*
import enums.OperationType
import enums.OperationType.*
import enums.TestCards.*
import helpers.EmvCardsTesterHelper
import helpers.MagneticCardsTesterHelper
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency.*
import org.junit.Test
import other.Utils
import java.math.BigDecimal


class W1_W4_EMV_Negative_Tests : TestCase() {
    @Test
    fun testW1() {
        println("W1. Partial Grade Response (Terminal 1)")

        var testResult: TestResult
//--------------------------------------------------------------
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "W1.01",
            testCard = EMV_3,
            operationType = AUTHORISATION,
            amount = BigDecimal(101.11),
            currency = RUB,
            description = "Authorisation, No F55 in Response",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "W1.02",
            testCard = EMV_3,
            operationType = PURCHASE,
            amount = BigDecimal(102.11),
            currency = USD,
            description = "Purchase, No F55 in Response",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "W1.03",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }


}