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


class D25_I17_PurchaseWithCashBack : TestCase() {
    @Test
    fun testD25() {
        println("D25. Purchase with Cashback (Terminals 1)")

        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB25.01",
            testCard = EMV_3,
            operationType = PURCHASE_WITH_CASH_BACK,
            amount = BigDecimal(2501.54),
            cashBackAmount = BigDecimal(10),
            cardHolderVerificationType = SIGNED,
            description = "Purchase with cashback. Operation should be rejected by terminal. ",
            cardSlotType = ICC,
            currency = RUB

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, UNKNOWN_ERROR)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB25.02",
            testCard = EMV_3,
            operationType = PURCHASE_WITH_CASH_BACK,
            amount = BigDecimal(2502.54),
            cashBackAmount = BigDecimal(10),
            cardHolderVerificationType = SIGNED,
            description = "Purchase with cashback. Operation should be rejected by terminal. ",
            cardSlotType = ICC,
            currency = USD
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, UNKNOWN_ERROR)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB25.03",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(2503.54),
            cashBackAmount = BigDecimal(10),
            cardHolderVerificationType = SIGNED,
            description = "Purchase with cashback.",
            cardSlotType = ICC,
            currency = RUB
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DB25.04",
            testCard = EMV_10,
            operationType = PURCHASE,
            amount = BigDecimal(2504.54),
            cashBackAmount = BigDecimal(10),
            cardHolderVerificationType = SIGNED,
            description = "Purchase with cashback.",
            cardSlotType = ICC,
            currency = USD

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, APPROVED_FOR_PARTIAL_AMOUNT)

//----------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "D25.05",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

    }

}