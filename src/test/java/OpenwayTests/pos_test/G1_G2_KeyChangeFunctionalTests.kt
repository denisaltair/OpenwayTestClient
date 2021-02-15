package OpenwayTests.pos_test

import entities.TestResult
import enums.*
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import org.junit.Test
import other.Utils
import java.math.BigDecimal

class G1_G2_KeyChangeFunctionalTests : TestCase() {
    //G1. Key Change (Terminal 1)
    @Test
    fun testG1() {
        println("G1. Keys Change (Terminal 1, TCP/IP protocol)*")
        var testResult: TestResult
        return
//--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "G1.01A",
//            testCard = TestCards.MAG_1,
//            operationType = OperationType.GET_AUTH_KEYS,
//            description = "Keys Change request",
//            isWithMac = true
//        )
//        println(testResult.resultMessage)
//        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
//        return
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "G1.01B",
            testCard = TestCards.MAG_1,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(101.15),
            description = "PBT",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.ONLINE_PIN,
            isWithMac = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        return

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "G1.02",
            testCard = TestCards.EMV_10,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(102.15),
            description = "PBT, new TPK should be generated",
            cardSlotType = CardSlotType.ICC,
            cardHolderVerificationType = CardHolderVerificationType.ONLINE_PIN,
            isWithMac = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "G1.03",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(103.15),
            description = "PBT",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.ONLINE_PIN,
            isWithMac = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

    }


    @Test
    fun testG2() {
        println("G2. Keys Change Timeout (Terminal 1, TCP/IP protocol)*")
        var testResult: TestResult
        EmvCardsTesterHelper.sendRequest(operationType = OperationType.RECONCILIATION)
        return
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "G2.01A",
            testCard = TestCards.MAG_1,
            operationType = OperationType.GET_AUTH_KEYS,
            description = "Keys Change request Timeout",
            isWithMac = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "G2.01B",
            testCard = TestCards.MAG_1,
            operationType = OperationType.GET_AUTH_KEYS,
            description = "Keys Change request",
            isWithMac = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "G2.02",
            testCard = TestCards.EMV_10,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(202.15),
            description = "PBT",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.ONLINE_PIN,
            isWithMac = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

    }



}