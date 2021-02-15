package OpenwayTests.pos_test.only_magnetic_cards

import entities.TestResult
import enums.*
import enums.CardHolderVerificationType.*
import enums.CardSlotType.*
import enums.OpenwayResponseCode.*
import enums.OperationType.*
import enums.TestCards.*
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency
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
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(101.00),
            description = "Purchase",
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.02",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(102.00),
            description = "Purchase",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.03",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            description = "Purchase",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.04",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(104.00),
            description = "Real Bad PIN (PIN = 987654)",
            cardSlotType = MAGNETIC_STRIPE,
            pin="987654",
            cardHolderVerificationType = ONLINE_PIN
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_PIN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.05",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(105.00),
            description = "Good PIN",
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.06",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(106.00),
            description = "Purchase",
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.07",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(107.00),
            description = "Purchase",
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.08",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(Config.MIN_AMOUNT_VALUE),
            description = "Purchase",
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M1.09",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(109.00),
            description = "Purchase",
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }

    //M2. Signature-Based Transactions (Terminals 1, 2)
    @Test
    fun testM2() {
        println("M2. Signature-Based Transactions (Terminals 1, 2)")

        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M2.01",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(201.00),
            description = "Purchase",
            cardHolderVerificationType = SIGNED,
            cardSlotType = MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M2.02",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            description = "Purchase",
            cardHolderVerificationType = SIGNED,
            cardSlotType = MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M2.03",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(203.03),
            description = "Purchase",
            cardHolderVerificationType = SIGNED,
            cardSlotType = MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


    }

    //M3. Manual Entry Transactions (Terminals 1, 2, 4)
    @Test
    fun testM3() {
        println("M3. Manual Entry Transactions (Terminals 1, 2, 4)")

        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M3.01",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(301.00),
            description = "Purchase",
            cardSlotType = MANUAL
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M3.02",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(302.00),
            description = "Purchase",
            cardSlotType = MANUAL
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M3.03",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(303.00),
            description = "Purchase",
            cardSlotType = MANUAL
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        //--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M3.04",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(304.00),
            description = "Purchase",
            cardSlotType = MANUAL
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        //--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M3.05",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(Config.MIN_AMOUNT_VALUE),
            description = "Purchase",
            cardSlotType = MANUAL
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        //--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M3.06",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(Config.MAX_AMOUNT_VALUE),
            description = "Purchase",
            cardSlotType = MANUAL
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }

    //M8. Retail Refund (Terminals 1, 3, 4)
    @Test
    fun testM8() {
        println("M8. Retail Refund (Terminals 1, 3, 4)")

        var testResult: TestResult
//--------------------------------------------------------------
        val guidM801A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.01A",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(801.00),
            description = "PBT (For terminal 1,3 only)",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidM801A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.01B",
            testCard = MAG_2,
            operationType = REFUND,
            amount = BigDecimal(801.00),
            description = "Refund M8.01A",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            parentGuid = guidM801A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
//--------------------------------------------------------------
        val guidM802A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.02A",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(803.00),
            description = "SBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            guid = guidM802A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.02B",
            testCard = MAG_2,
            operationType = REFUND,
            amount = BigDecimal(80.2),
            description = "Refund M8.02A",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            parentGuid = guidM802A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM803A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.03A",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(803.00),
            description = "Manual",
            cardSlotType = MANUAL,
            guid = guidM803A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.02B",
            testCard = MAG_1,
            operationType = REFUND,
            amount = BigDecimal(80.3),
            description = "Refund M8.02A",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            parentGuid = guidM803A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM804A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.04A",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(804.00),
            description = "PBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidM804A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.04B",
            testCard = MAG_1,
            operationType = REFUND,
            amount = BigDecimal(8040.00),
            description = "Refund M8.04A. Wrong amount",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            parentGuid = guidM804A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_AMOUNT)

//--------------------------------------------------------------
        val guidM805A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.05A",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(805.00),
            description = "SBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            guid = guidM805A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.05B",
            testCard = MAG_1,
            operationType = REFUND,
            amount = BigDecimal(805.00),
            description = "Refund M8.05A, RC=96",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            parentGuid = guidM805A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, SYSTEM_DESTROYED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.03C",
            testCard = MAG_1,
            operationType = REFUND,
            amount = BigDecimal(80.3),
            description = "Refund M8.03A",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM803A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M8.03D",
            testCard = MAG_1,
            operationType = REFUND,
            amount = BigDecimal(8030),
            description = "Refund M8.03A, Wrong Amount",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM803A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_AMOUNT)
    }

    //M9. Retail Authorisation and Authorisation Confirmation (Terminals 1, 3)
    @Test
    fun testM9() {
        println("M9. Retail Authorisation and Authorisation Confirmation (Terminals 1, 3)")

        var testResult: TestResult
//--------------------------------------------------------------
        val guidM901A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.01A",
            testCard = MAG_2,
            operationType = AUTHORISATION,
            amount = BigDecimal(901.00),
            description = "Authorisation PBT ",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidM901A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.01B",
            testCard = MAG_2,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(901.00),
            description = "Manual Confirmation M9.01A",
            cardSlotType = MANUAL,
            parentGuid = guidM901A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM902A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.02A",
            testCard = MAG_2,
            operationType = AUTHORISATION,
            amount = BigDecimal(902.00),
            description = "Authorisation SBT ",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            guid = guidM902A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.02B",
            testCard = MAG_2,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(902.00),
            description = "Manual Confirmation M9.02A",
            cardSlotType = MANUAL,
            parentGuid = guidM902A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


//--------------------------------------------------------------
        val guidM903A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.03A",
            testCard = MAG_1,
            operationType = AUTHORISATION,
            amount = BigDecimal(903.00),
            description = "Authorisation Manual ",
            cardSlotType = MANUAL,
            guid = guidM903A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM903B = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.03B",
            testCard = MAG_1,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(903.00),
            description = "Manual Confirmation M9.03A",
            cardSlotType = MANUAL,
            guid = guidM903B,
            parentGuid = guidM903A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM904A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.04A",
            testCard = MAG_1,
            operationType = AUTHORISATION,
            amount = BigDecimal(904.00),
            description = "Authorisation PBT ",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidM904A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.04B",
            testCard = MAG_1,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(9040.00),
            description = "Manual Confirmation M9.04A. Wrong Amount",
            cardSlotType = MANUAL,
            parentGuid = guidM904A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_AMOUNT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.04C",
            testCard = MAG_1,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(904.00),
            description = "Manual Confirmation M9.04A.",
            cardSlotType = MANUAL,
            parentGuid = guidM904A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM905A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.05A",
            testCard = MAG_1,
            operationType = AUTHORISATION,
            amount = BigDecimal(905.00),
            description = "Authorisation SBT ",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            guid = guidM905A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.05B",
            testCard = MAG_1,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(905.00),
            description = "Manual Confirmation M9.05A",
            cardSlotType = MANUAL,
            parentGuid = guidM905A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.03C",
            testCard = MAG_1,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(903.00),
            description = "Manual Confirmation M9.03A. Repeat",
            cardSlotType = MANUAL,
            isRepeat = true,
            parentGuid = guidM903A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.03D",
            testCard = MAG_1,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(9030.00),
            description = "Manual Confirmation M9.03A. Repeat. Wrong Amount.",
            cardSlotType = MANUAL,
            isRepeat = true,
            parentGuid = guidM903A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)

////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "M9.03E",
//            testCard = MAG_1,
//            operationType = REFUND,
//            amount = BigDecimal(902.00),
//            description = "Refund 9.03B",
//            cardSlotType = MAGNETIC_STRIPE,
//            parentGuid = guidM903B
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM906A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.06A",
            testCard = MAG_1,
            operationType = AUTHORISATION,
            amount = BigDecimal(906.00),
            description = "Authorisation PBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidM906A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.06B",
            testCard = MAG_1,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(905.00),
            currency = Currency.USD,
            description = "Manual Confirmation M9.06A. Wrong Currency",
            cardSlotType = MANUAL,
            parentGuid = guidM906A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.06B",
            testCard = MAG_1,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(905.00),
            currency = Currency.USD,
            description = "Manual Confirmation M9.06A. Wrong Currency",
            cardSlotType = MANUAL,
            parentGuid = guidM906A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)

//--------------------------------------------------------------
        val guidM907A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.07A",
            testCard = MAG_1,
            operationType = AUTHORISATION,
            amount = BigDecimal(907.00),
            description = "Authorisation SBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            guid = guidM907A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        //--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.07B",
            testCard = MAG_1,
            operationType = REVERSAL,
            amount = BigDecimal(907.0),
            description = "Universal Reversal M9.07A",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM907A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        //--------------------------------------------------------------
        val guidM908A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.08A",
            testCard = MAG_2,
            operationType = AUTHORISATION,
            amount = BigDecimal(908.00),
            description = "Authorisation PBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidM908A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        //--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.08B",
            testCard = MAG_2,
            operationType = REVERSAL,
            amount = BigDecimal(90.8),
            description = "Universal Reversal M9.08A",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM908A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


        //--------------------------------------------------------------
        val guidM911A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.11A",
            testCard = MAG_2,
            operationType = AUTHORISATION,
            amount = BigDecimal(912.00),
            description = "Authorisation PBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidM911A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        //--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.11B",
            testCard = MAG_1,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(912.0),
            currency = Currency.USD,
            description = "Confirmation M9.11A. Wrong Card Number",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM911A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)

        //--------------------------------------------------------------
        val guidM912A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.12A",
            testCard = MAG_1,
            operationType = AUTHORISATION,
            amount = BigDecimal(913.00),
            description = "Authorisation PBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidM912A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        //--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.12B",
            testCard = MAG_1,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(915.0),
            description = "Confirmation M9.12A. More Amount",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM912A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        //--------------------------------------------------------------
        val guidM913A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.13A",
            testCard = MAG_2,
            operationType = AUTHORISATION,
            amount = BigDecimal(914.00),
            description = "Authorisation PBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidM913A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        //--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M9.13B",
            testCard = MAG_2,
            operationType = AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(911.0),
            description = "Confirmation M9.13A. Less Amount",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM913A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


    }

    //M15. Universal Reversal
    @Test
    fun testM15() {
        println("M15. Universal Reversal")

        var testResult: TestResult
//--------------------------------------------------------------
        val guidM1501A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.01A",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(1501.00),
            description = "Purchase PBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidM1501A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.01B",
            testCard = MAG_1,
            operationType = REVERSAL,
            amount = BigDecimal(1501.00),
            description = "Universal Reversal M15.01A",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM1501A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM1502A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.01A",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(1502.00),
            description = "Purchase SBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            guid = guidM1502A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.02B",
            testCard = MAG_2,
            operationType = REVERSAL,
            amount = BigDecimal(1502.00),
            description = "Universal Reversal M15.01A",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM1502A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM1503A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.03A",
            testCard = MAG_1,
            operationType = AUTHORISATION,
            amount = BigDecimal(1503.00),
            description = "Authorisation Manual",
            cardSlotType = MANUAL,
            guid = guidM1503A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.03B",
            testCard = MAG_1,
            operationType = REVERSAL,
            amount = BigDecimal(1503.00),
            description = "Universal Reversal M15.03A",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM1503A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM1504A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.04A",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(1504.00),
            description = "Purchase Manual",
            cardSlotType = MANUAL,
            guid = guidM1504A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.04B",
            testCard = MAG_1,
            operationType = REVERSAL,
            amount = BigDecimal(150.40),
            description = "Universal Reversal M15.04A",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM1504A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidM1505A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.05A",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(1505.00),
            description = "Purchase SBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            guid = guidM1505A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.05B",
            testCard = MAG_2,
            operationType = REVERSAL,
            amount = BigDecimal(15050.00),
            description = "Universal Reversal M15.05A. Wrong Amount",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM1505A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_AMOUNT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.03C",
            testCard = MAG_1,
            operationType = REVERSAL,
            amount = BigDecimal(1503.00),
            description = "Universal Reversal M15.03A. Repeat",
            cardSlotType = MAGNETIC_STRIPE,
            isRepeat = true,
            parentGuid = guidM1503A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, RECONCILE_ERROR_AUTH_NOT_FOUND)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "M15.03D",
            testCard = MAG_1,
            operationType = REVERSAL,
            amount = BigDecimal(15030.00),
            description = "Universal Reversal M15.03A. Repeat. Wrong Amount.",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidM1503A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, WRONG_AMOUNT)
    }

    //V1. Purchase Return
    @Test
    fun testV1() {
        println("V1. Purchase Return")

        var testResult: TestResult
//--------------------------------------------------------------
        val guidV101A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "V1.01A",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(101.07),
            description = "Purchase PBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = ONLINE_PIN,
            guid = guidV101A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "V1.01B",
            testCard = MAG_2,
            operationType = PURCHASE_RETURN,
            amount = BigDecimal(101.07),
            description = "Purchase Return V1.01A",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidV101A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidV102A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "V1.02A",
            testCard = MAG_2,
            operationType = PURCHASE,
            amount = BigDecimal(102.07),
            description = "Purchase SBT",
            cardSlotType = MAGNETIC_STRIPE,
            cardHolderVerificationType = SIGNED,
            guid = guidV102A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "V1.02B",
            testCard = MAG_2,
            operationType = PURCHASE_RETURN,
            amount = BigDecimal(102.07),
            description = "Purchase Return V1.02A",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidV102A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        val guidV103A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "V1.03A",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(103.07),
            description = "Purchase Manual",
            cardSlotType = MANUAL,
            guid = guidV103A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "V1.03B",
            testCard = MAG_1,
            operationType = PURCHASE_RETURN,
            amount = BigDecimal(103.07),
            description = "Purchase Return V1.03B",
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidV103A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }
}