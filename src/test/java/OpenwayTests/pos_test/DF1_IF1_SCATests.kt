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
import org.junit.Test
import other.Utils
import java.math.BigDecimal


class DF1_IF1_SCATests : TestCase() {
    @Test
    fun testDF1() {
        println("DF1. On-Line SCA processing")

        var testResult: TestResult
//===================================================================
      //  EmvCardsTesterHelper.sendRequest(operationType = RECONCILIATION)
//--------------------------------------------------------------
        var guidDF101A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DF1.01A-DF1.01B",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(101.84),
            cardHolderVerificationType = SIGNED,
            description = "Purchase rc=1A ",
            cardSlotType = RF,
            guid = guidDF101A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DF1.01C",
            testCard = EMV_9,
            operationType = REVERSAL,
            amount = BigDecimal(101.84),
            cardHolderVerificationType = SIGNED,
            description = "Purchase rc=1A ",
            cardSlotType = RF,
            parentGuid = guidDF101A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

        return

//--------------------------------------------------------------
        var guidDF101B = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "DF1.01B",
            testCard = EMV_9,
            operationType = PURCHASE,
            amount = BigDecimal(101.84),
            cardHolderVerificationType = SIGNED,
            description = "Purchase with PIN ",
            cardSlotType = RF,
            guid = guidDF101B
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)

//----------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = RECONCILIATION,
            testNumber = "D17.05",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, ACCEPTED)


    }

}