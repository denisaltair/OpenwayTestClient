package OpenwayTests.pos_test.only_magnetic_cards

import entities.TestResult
import enums.*
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency
import org.junit.Test
import other.Utils

import java.math.BigDecimal

class M20_T17_AdditionalTestsForPurchaseWithCashback: TestCase() {
    //M20. Purchase with Cashback (Terminals 1)
    @Test
    fun testM20() {
        println("M20. Purchase with Cashback (Terminals 1)")

        var testResult: TestResult

     //--------------------------------------------------------------
        EmvCardsTesterHelper.sendRequest(
            operationType = OperationType.RECONCILIATION,
            description = "Reconciliation"
        )
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M20.01",
            testCard = TestCards.MAG_1,
            operationType = OperationType.PURCHASE_WITH_CASH_BACK,
            amount = BigDecimal(2001.63),
            cashBackAmount = BigDecimal(10),
            currency = Currency.USD,
            description = "Purchase with cashback SBT",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M20.02",
            testCard = TestCards.MAG_1,
            operationType = OperationType.PURCHASE_WITH_CASH_BACK,
            amount = BigDecimal(2002.63),
            cashBackAmount = BigDecimal(10),
            currency = Currency.RUB,
            description = "Purchase with cashback PBT",
            cardHolderVerificationType = CardHolderVerificationType.ONLINE_PIN,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M20.03",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE_WITH_CASH_BACK,
            amount = BigDecimal(2003.63),
            cashBackAmount = BigDecimal(10),
            currency = Currency.USD,
            description = "Purchase with cashback PBT",
            cardHolderVerificationType = CardHolderVerificationType.ONLINE_PIN,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M20.04",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE_WITH_CASH_BACK,
            amount = BigDecimal(2004.63),
            currency = Currency.RUB,
            cashBackAmount = BigDecimal(10),
            description = "Purchase with cashback SBT",
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = OperationType.RECONCILIATION,
            testNumber = "M20.05",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }





}