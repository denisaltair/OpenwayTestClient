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

//----------------------------------------------------
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION,description = "Reconciliation")

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D25.01",
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
            testNumber = "D25.02",
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
            testNumber = "D25.03",
            testCard = EMV_10,
            operationType = PURCHASE_WITH_CASH_BACK,
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
            testNumber = "D25.04",
            testCard = EMV_10,
            operationType = PURCHASE_WITH_CASH_BACK,
            amount = BigDecimal(2504.54),
            cashBackAmount = BigDecimal(10),
            cardHolderVerificationType = SIGNED,
            description = "Purchase with cashback.",
            cardSlotType = ICC,
            currency = USD

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "D25.05",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

    }

    @Test
    fun testD26() {
        println("D26. Purchase with Cashback (Terminals 1)")

        var testResult: TestResult

//----------------------------------------------------
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION, description = "Reconciliation")

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D26.01",
            testCard = EMV_10,
            operationType = PURCHASE_WITH_CASH_BACK,
            amount = BigDecimal(2601.54),
            cashBackAmount = BigDecimal(1.54),
            cardHolderVerificationType = SIGNED,
            description = "",
            cardSlotType = ICC,
            currency = RUB

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D26.02",
            testCard = EMV_10,
            operationType = PURCHASE_WITH_CASH_BACK,
            amount = BigDecimal(2602.54),
            cashBackAmount = BigDecimal(2.54),
            cardHolderVerificationType = SIGNED,
            description = "",
            cardSlotType = ICC,
            currency = RUB

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, PURCHASE_APPROVAL_ONLY)

//--------------------------------------------------------------
        val guidD2603A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D26.03A",
            testCard = EMV_10,
            operationType = PURCHASE_WITH_CASH_BACK,
            amount = BigDecimal(2603.54),
            cashBackAmount = BigDecimal(3.54),
            cardHolderVerificationType = SIGNED,
            description = "",
            cardSlotType = ICC,
            currency = RUB,
            guid = guidD2603A

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, PURCHASE_APPROVAL_ONLY)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "D26.03B",
            testCard = EMV_10,
            operationType = REVERSAL,
            amount = BigDecimal(2600),
            cashBackAmount = BigDecimal(0),
            cardHolderVerificationType = SIGNED,
            description = "",
            cardSlotType = ICC,
            currency = RUB,
            parentGuid= guidD2603A

        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "D26.04",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


    }



    }