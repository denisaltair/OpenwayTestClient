package OpenwayTests.pos_test.only_magnetic_cards

import com.sleepycat.je.OperationResult
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
import kz.multibank.cardposclient.entities.Currency.*
import org.junit.Test
import other.Utils
import java.math.BigDecimal
import enums.OperationType.valueOf as valueOf1

class T1_T14_Magnetic_Stripe_Timeout_Tests : TestCase() {
    //T1. Authorisation Timeout (Terminal 1)
    @Test
    fun testT1() {
        println("T1. Authorisation Timeout (Terminal 1)")
        var testResult: TestResult
//--------------------------------------------------------------
        val guidT101A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "T1.01A",
            testCard = MAG_1,
            operationType = AUTHORISATION,
            amount = BigDecimal(101.03),
            description = "Authorisation",
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE,
            guid=guidT101A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, SERVER_NOT_RESPONDING)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "T1.01A",
            testCard = MAG_1,
            operationType = AUTHORISATION,
            amount = BigDecimal(101.03),
            description = "Repeat of T1.01A",
            isRepeat = true,
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE,
            guid = guidT101A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, SERVER_NOT_RESPONDING)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "T1.01C",
            testCard = MAG_1,
            operationType = AUTO_REVERSAL,
            amount = BigDecimal(101.03),
            description = "Automatic Reversal of T1.01A",
            isRepeat = true,
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidT101A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


    }

    //T2. Purchase Timeout (Terminal 1)*
    @Test
    fun testT2() {
        println("T2. Purchase Timeout (Terminal 1)*")
        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "T2.01",
            testCard = MAG_7,
            operationType = PURCHASE,
            amount = BigDecimal(201.03),
            description = "Network Timeout",
            cardSlotType = MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.valueOf("82"))

//--------------------------------------------------------------
        val guidT202A=Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "T2.02A",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(202.03),
            description = "Purchase",
            cardSlotType = MAGNETIC_STRIPE,
            guid = guidT202A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, SERVER_NOT_RESPONDING)
//-----------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "T2.02B",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(202.03),
            description = "Repeat of T2.02A",
            isRepeat = true,
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE,
            guid = guidT202A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, SERVER_NOT_RESPONDING)

//-----------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "T2.02C",
            testCard = MAG_1,
            operationType = PURCHASE,
            amount = BigDecimal(202.03),
            description = "Repeat of T2.02A",
            isRepeat = true,
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE,
            guid = guidT202A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, SERVER_NOT_RESPONDING)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "T2.02D",
            testCard = MAG_1,
            operationType = AUTO_REVERSAL,
            amount = BigDecimal(202.03),
            description = "Automatic Reversal of T2.02A",
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidT202A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, SERVER_NOT_RESPONDING)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "T2.02G",
            testCard = MAG_1,
            operationType = AUTO_REVERSAL,
            amount = BigDecimal(202.03),
            description = "Automatic Reversal of T2.02A",
            isRepeat = true,
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidT202A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, SERVER_NOT_RESPONDING)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "T2.02H",
            testCard = MAG_1,
            operationType = AUTO_REVERSAL,
            amount = BigDecimal(202.03),
            description = "Automatic Reversal of T2.02A",
            isRepeat = true,
            cardHolderVerificationType = ONLINE_PIN,
            cardSlotType = MAGNETIC_STRIPE,
            parentGuid = guidT202A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)
    }
}