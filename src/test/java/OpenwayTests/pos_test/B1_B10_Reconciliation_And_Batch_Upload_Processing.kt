package OpenwayTests.pos_test

import entities.TestResult
import enums.CardHolderVerificationType.*
import enums.CardSlotType.*
import enums.OpenwayResponseCode.*
import enums.OperationType
import enums.OperationType.*
import enums.TestCards.*
import helpers.EmvCardsTesterHelper
import helpers.MagneticCardsTesterHelper
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.entities.Currency.*
import org.junit.Test
import other.Utils
import java.math.BigDecimal


class B1_B10_Reconciliation_And_Batch_Upload_Processing : TestCase() {
    @Test
    fun testB1_B6() {
        println("B1 – B6. Online Normal Processing (Terminals 1, 2)")

        var testResult: TestResult
//        //----------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            operationType = RECONCILIATION,
//            description = "Reconciliation"
//        )
//        println(testResult.resultMessage)

////--------------------------------------------------------------
//        val guidB101 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.01",
//            testCard = MAG_1,
//            operationType = PURCHASE,
//            amount = BigDecimal(101.06),
//            cardHolderVerificationType = ONLINE_PIN,
//            description = "Purchase PBT",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB101
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB102 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.02",
//            testCard = MAG_2,
//            operationType = PURCHASE,
//            amount = BigDecimal(102.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Magnetic Stripe Purchase/Cash SBT",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB102
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.03",
//            testCard = MAG_1,
//            operationType = REVERSAL,
//            amount = BigDecimal(101.06),
//            description = "Magnetic Stripe Universal Reversal B1.01",
//            cardSlotType = MAGNETIC_STRIPE,
//            parentGuid = guidB101
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB104 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.04",
//            testCard = MAG_1,
//            operationType = PURCHASE,
//            amount = BigDecimal(104.06),
//            cashBackAmount = BigDecimal(10),
//            cardHolderVerificationType = SIGNED,
//            description = "Magnetic Stripe Purchase/Cash Manual",
//            cardSlotType = MANUAL,
//            guid = guidB104
//
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.05",
//            testCard = MAG_1,
//            operationType = REVERSAL,
//            amount = BigDecimal(10.4),
//            description = "Magnetic Stripe Universal Reversal B1.04",
//            cardSlotType = MAGNETIC_STRIPE,
//            parentGuid = guidB104
//
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB106 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.06",
//            testCard = MAG_2,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(106.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Magnetic Stripe Auth Manual",
//            cardSlotType = MANUAL,
//            guid = guidB106
//
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.07",
//            testCard = MAG_2,
//            operationType = REVERSAL,
//            amount = BigDecimal(106.06),
//            description = "Magnetic Stripe Universal Reversal B1.06",
//            cardSlotType = MANUAL,
//            parentGuid = guidB106
//
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB108 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.08",
//            testCard = MAG_1,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(108.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Magnetic Stripe Authorisation SBT",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB108
//
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB109 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.09",
//            testCard = MAG_2,
//            operationType = PURCHASE,
//            amount = BigDecimal(109.06),
//            cardHolderVerificationType = SIGNED,
//            description = "PURCHASE",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB109
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
////--------------------------------------------------------------
//
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.10",
//            testCard = MAG_2,
//            operationType = REFUND,
//            amount = BigDecimal(109.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Magnetic Stripe Refund B1.09",
//            cardSlotType = MAGNETIC_STRIPE,
//            parentGuid = guidB109
//
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
////--------------------------------------------------------------
//        val guidB111 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.11",
//            testCard = EMV_3,
//            operationType = PURCHASE,
//            amount = BigDecimal(111.06),
//            cardHolderVerificationType = SIGNED,
//            description = "EMV PURCHASE",
//            cardSlotType = ICC,
//            guid = guidB111
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB112 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.12",
//            testCard = EMV_9,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(112.06),
//            cardHolderVerificationType = SIGNED,
//            description = "EMV Authorisation",
//            cardSlotType = ICC,
//            guid = guidB112
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB113 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.13",
//            testCard = EMV_3,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(113.06),
//            cardHolderVerificationType = SIGNED,
//            description = "EMV Authorisation",
//            cardSlotType = ICC,
//            guid = guidB113
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidB114 = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B1.14",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(114.06),
            cardHolderVerificationType = SIGNED,
            description = "EMV Purchase",
            cardSlotType = ICC,
            guid = guidB114
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

////--------------------------------------------------------------
//        val guidB115 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.15",
//            testCard = EMV_3,
//            operationType = PURCHASE,
//            amount = BigDecimal(115.06),
//            cardHolderVerificationType = SIGNED,
//            description = "EMV Purchase",
//            cardSlotType = ICC,
//            guid = guidB115
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB117 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.17",
//            testCard = EMV_3,
//            operationType = PURCHASE,
//            amount = BigDecimal(117.06),
//            cardHolderVerificationType = SIGNED,
//            description = "EMV Purchase",
//            cardSlotType = ICC,
//            guid = guidB117
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB118 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.18",
//            testCard = MAG_2,
//            operationType = PURCHASE,
//            amount = BigDecimal(118.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Purchase",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB118
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB119 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.19",
//            testCard = MAG_2,
//            operationType = PURCHASE,
//            amount = BigDecimal(119.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Purchase",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB119
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB120 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B1.20",
//            testCard = MAG_2,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(120.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Authorisation",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB120
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////----------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            operationType = RECONCILIATION,
//            testNumber = "B1.22",
//            description = "Reconciliation"
//        )
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//        println(testResult.resultMessage)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B2.01",
//            testCard = EMV_3,
//            operationType = AUTHORISATION_CONFIRMATION,
//            amount = BigDecimal(113.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Authorisation Confirmation B1.13",
//            cardSlotType = ICC,
//            parentGuid = guidB113
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B2.02",
//            testCard = MAG_2,
//            operationType = REVERSAL,
//            amount = BigDecimal(102.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Universal Reversal B1.02",
//            cardSlotType = MAGNETIC_STRIPE,
//            parentGuid = guidB102
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B2.03",
//            testCard = EMV_3,
//            operationType = REVERSAL,
//            amount = BigDecimal(11.10),
//            cardHolderVerificationType = SIGNED,
//            description = "Universal Reversal B1.11",
//            cardSlotType = ICC,
//            parentGuid = guidB111
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B2.04",
            testCard = EMV_9,
            operationType = REFUND,
            amount = BigDecimal(114.06),
            cardHolderVerificationType = SIGNED,
            description = "Refund B1.14",
            cardSlotType = ICC,
            parentGuid = guidB114
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB205 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B2.05",
//            testCard = MAG_2,
//            operationType = PURCHASE,
//            amount = BigDecimal(205.06),
//            cardHolderVerificationType = SIGNED,
//            description = "PURCHASE",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB205
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////----------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            operationType = RECONCILIATION,
//            testNumber = "B2.11",
//            description = "Reconciliation"
//        )
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//        println(testResult.resultMessage)
//
////--------------------------------------------------------------
//        val guidB301 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B3.01",
//            testCard = EMV_3,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(301.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Authorisation",
//            cardSlotType = ICC,
//            guid = guidB301
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB302 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B3.02",
//            testCard = EMV_9,
//            operationType = PURCHASE,
//            amount = BigDecimal(302.06),
//            cardHolderVerificationType = SIGNED,
//            description = "PURCHASE",
//            cardSlotType = ICC,
//            guid = guidB302
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B3.03",
//            testCard = EMV_9,
//            operationType = REVERSAL,
//            amount = BigDecimal(112.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Universal Reversal B1.12",
//            cardSlotType = ICC,
//            parentGuid = guidB112
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B3.04",
//            testCard = MAG_7,
//            operationType = PURCHASE,
//            amount = BigDecimal(304.06),
//            cardHolderVerificationType = SIGNED,
//            currency = USD,
//            description = "PURCHASE",
//            cardSlotType = MAGNETIC_STRIPE
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, TIMEOUT_AT_ISSUER_SYSTEM_BAD_CVV_VISA)
//
////----------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            operationType = RECONCILIATION,
//            testNumber = "B3.06",
//            description = "Reconciliation"
//        )
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//        println(testResult.resultMessage)
//
//
////=====================B4-B6 Reconciliation Errors=======================
//        println("====================B4-B6 Reconciliation Errors==================")
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testCard = EMV_3,
//            testNumber = "B4.03",
//            operationType = REVERSAL,
//            amount = BigDecimal(115.06),
//            cardHolderVerificationType = ONLINE_PIN,
//            description = "Universal Reversal",
//            cardSlotType = ICC,
//            parentGuid = guidB115
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB404 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testCard = MAG_1,
//            testNumber = "B4.04",
//            operationType = PURCHASE,
//            amount = BigDecimal(404.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Purchase Manual",
//            cardSlotType = MANUAL,
//            guid = guidB404
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, NO_SUCH_CARD)
//
////--------------------------------------------------------------
//        val guidB406 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.06",
//            testCard = MAG_2,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(406.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Magnetic Stripe Authorisation Manual",
//            cardSlotType = MANUAL,
//            guid = guidB406
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.07",
//            testCard = MAG_1,
//            operationType = REVERSAL,
//            amount = BigDecimal(108.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Universal Reversal 1.08",
//            cardSlotType = MANUAL,
//            parentGuid = guidB108
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB408 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.08",
//            testCard = EMV_9,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(408.06),
//            cardHolderVerificationType = ONLINE_PIN,
//            description = "EMV Authorisation PBT ",
//            cardSlotType = ICC,
//            guid = guidB408
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB409 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.09",
//            testCard = MAG_2,
//            operationType = PURCHASE,
//            amount = BigDecimal(409.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Magnetic Stripe Purchase",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB409
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.10",
//            testCard = EMV_3,
//            operationType = REFUND,
//            amount = BigDecimal(117.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Refund B1.17 (For Terminal 1 only)",
//            cardSlotType = ICC,
//            parentGuid = guidB117
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB412 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.12",
//            testCard = MAG_2,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(412.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Authorisation",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB412
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB413 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.13",
//            testCard = EMV_9,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(413.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Authorisation",
//            cardSlotType = ICC,
//            guid = guidB413
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB414 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.14",
//            testCard = MAG_2,
//            operationType = PURCHASE,
//            amount = BigDecimal(414.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Purchase",
//            currency = RUB,
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB414
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB415 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.15",
//            testCard = EMV_9,
//            operationType = PURCHASE,
//            amount = BigDecimal(415.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Purchase",
//            cardSlotType = ICC,
//            guid = guidB415
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB419 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.19",
//            testCard = EMV_3,
//            operationType = PURCHASE,
//            amount = BigDecimal(419.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Purchase",
//            cardSlotType = ICC,
//            guid = guidB419
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.20",
//            testCard = EMV_3,
//            operationType = REVERSAL,
//            amount = BigDecimal(419.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Universal Reversal B4.19",
//            cardSlotType = ICC,
//            parentGuid = guidB419
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB421 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.21",
//            testCard = MAG_2,
//            operationType = PURCHASE,
//            amount = BigDecimal(421.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Purchase",
//            currency = RUB,
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB421
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B4.22",
//            testCard = MAG_2,
//            operationType = REVERSAL,
//            amount = BigDecimal(421.06),
//            cardHolderVerificationType = SIGNED,
//            currency = RUB,
//            description = "Universal Reversal B4.21",
//            cardSlotType = MAGNETIC_STRIPE,
//            parentGuid = guidB421
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////----------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            operationType = RECONCILIATION,
//            testNumber = "B4.23",
//            description = "Reconciliation"
//        )
//        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)
//        println(testResult.resultMessage)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B5.01",
//            testCard = MAG_2,
//            operationType = AUTHORISATION_CONFIRMATION,
//            amount = BigDecimal(120.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Authorisation Confirmation B1.20",
//            cardSlotType = MAGNETIC_STRIPE,
//            parentGuid = guidB120
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B5.02",
//            testCard = EMV_9,
//            operationType = REVERSAL,
//            amount = BigDecimal(415.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Universal Reversal B4.15",
//            cardSlotType = ICC,
//            parentGuid = guidB415
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B5.04",
//            testCard = MAG_2,
//            operationType = REFUND,
//            amount = BigDecimal(414.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Refund B4.14",
//            currency = RUB,
//            cardSlotType = MAGNETIC_STRIPE,
//            parentGuid = guidB414
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB505 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B5.05",
//            testCard = MAG_2,
//            operationType = PURCHASE,
//            amount = BigDecimal(505.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Purchase",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB505
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, NO_SUCH_CARD)
//
////----------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            operationType = RECONCILIATION,
//            testNumber = "B5.09",
//            description = "Reconciliation"
//        )
//        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)
//        println(testResult.resultMessage)
//
////--------------------------------------------------------------
//        val guidB601 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B6.01",
//            testCard = MAG_2,
//            operationType = AUTHORISATION,
//            amount = BigDecimal(601.06),
//            cardHolderVerificationType = SIGNED,
//            description = "Authorisation",
//            currency = RUB,
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB601
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////--------------------------------------------------------------
//        val guidB604 = Utils.getGUID()
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B6.04",
//            testCard = MAG_6,
//            operationType = PURCHASE,
//            amount = BigDecimal(605.06),
//            cardHolderVerificationType = ONLINE_PIN,
//            currency = RUB,
//            description = "Magnetic Stripe Purchase PBT",
//            cardSlotType = MAGNETIC_STRIPE,
//            guid = guidB604
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, CARD_ACCEPTOR_CONTACT_ACQUIRER)
//
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "B6.05",
//            operationType = CHECK_CONNECT
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//
////----------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            operationType = RECONCILIATION,
//            testNumber = "B6.07",
//            description = "Reconciliation"
//        )
//        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)
//        println(testResult.resultMessage)
    }


    @Test
    fun testB7() {
        println("B7. Database Error (Terminals 7)*")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        val guidB701 = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B7.01",
            testCard = EMV_3,
            operationType = PURCHASE,
            amount = BigDecimal(701.06),
            cardHolderVerificationType = SIGNED,
            description = "EMV Purchase SBT",
            cardSlotType = ICC,
            guid = guidB701
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidB702 = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B7.02",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(702.06),
            cardHolderVerificationType = SIGNED,
            description = "Purchase SBT",
            cardSlotType = MAGNETIC_STRIPE,
            guid = guidB702
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


        //----------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "B7.04A",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, DUPLICATE_TRANSMISSION)


        //----------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "B7.04B",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, DUPLICATE_TRANSMISSION)

        //----------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "B7.04D",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }

    @Test
    fun testB9() {
        println("B9. Normal Processing")

        var testResult: TestResult
//===================================================================
        EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B9.01",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(901.06),
            cardHolderVerificationType = ONLINE_PIN,
            description = "FALLBACK!  PBT",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B9.02",
            testCard = EMV_3,
            operationType = PURCHASE,
            amount = BigDecimal(902.06),
            cardHolderVerificationType = SIGNED,
            description = "FALLBACK!  SBT",
            cardSlotType = ICC
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidB903A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B9.03A",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(903.06),
            cardHolderVerificationType = ONLINE_PIN,
            currency = RUB,
            description = "Magnetic Stripe Purchase PBT",
            cardSlotType = MAGNETIC_STRIPE,
            guid = guidB903A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B9.03B",
            testCard = MAG_2,
            operationType = REVERSAL,
            amount = BigDecimal(903.06),
            description = "Universal Reversal B9.03A",
            currency = RUB,
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidB903A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidB904A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B9.04A",
            testCard = EMV_3,
            operationType = PURCHASE,
            amount = BigDecimal(904.06),
            cardHolderVerificationType = SIGNED,
            description = "EMV Purchase SBT",
            cardSlotType = ICC,
            guid = guidB904A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B9.04B",
            testCard = EMV_3,
            operationType = REVERSAL,
            amount = BigDecimal(90.46),
            description = "Universal Reversal B9.04A",
            cardSlotType = ICC,
            parentGuid = guidB904A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidB905A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B9.05A",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(905.06),
            cardHolderVerificationType = SIGNED,
            description = "EMV Purchase Manual",
            cardSlotType = MANUAL,
            guid = guidB905A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "B9.05B",
            testCard = EMV_9,
            operationType = REVERSAL,
            amount = BigDecimal(905.06),
            description = "Universal Reversal B9.05A",
            cardSlotType = ICC,
            parentGuid = guidB905A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "B9.06",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

    }
}