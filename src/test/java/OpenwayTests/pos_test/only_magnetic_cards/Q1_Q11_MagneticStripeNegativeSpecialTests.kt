package OpenwayTests.pos_test.only_magnetic_cards

import entities.TestResult
import enums.*
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import org.junit.Test
import other.Utils
import java.math.BigDecimal

class Q1_Q11_MagneticStripeNegativeSpecialTests : TestCase() {
    //Q1. Incorrect MAC (Terminal 1)
    @Test
    fun testQ1() {
        println("Q1. Incorrect MAC (Terminal 1)")

        var testResult: TestResult
//--------------------------------------------------------------
        val guidQ101A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q1.01A",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(101.10),
            description = "Authorisation, Incorrect MAC",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithMac = true,
            guid = guidQ101A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_MAC)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q1.01B",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(101.10),
            description = "Repeat of Q1.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithMac = true,
            parentGuid = guidQ101A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_MAC)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q1.01C",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(101.10),
            description = "Automatic Reversal of Q1.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithMac = true,
            parentGuid = guidQ101A,
            isRepeat = false
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        val guidQ102A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q1.02A",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(102.10),
            description = "Purchase, Incorrect MAC",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithMac = true,
            guid = guidQ102A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_MAC)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q1.02B",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(102.10),
            description = "Repeat of Q1.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithMac = true,
            parentGuid = guidQ102A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_MAC)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q1.02C",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(102.10),
            description = "Automatic Reversal of Q1.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            isWithMac = true,
            parentGuid = guidQ102A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    //Q2. Unmatched Card Number (Terminal 1)
    @Test
    fun testQ2() {
        var testResult: TestResult
//--------------------------------------------------------------
        val guidQ201A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q2.01A",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(201.10),
            description = "Authorisation, Unmatched Card Number",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ201A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_PAN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q2.01B",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(201.10),
            description = "Repeat of Q2.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ201A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_PAN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q2.01C",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(201.10),
            description = "Automatic Reversal of Q2.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ201A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        val guidQ202A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q2.02A",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(202.10),
            description = "Purchase, , Unmatched Card Number",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ202A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_PAN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q2.02B",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(202.10),
            description = "Repeat of Q2.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ202A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_PAN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q2.02C",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(202.10),
            description = "Automatic Reversal of Q2.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ202A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    //Q3. Unmatched Amount (Terminal 1)
    @Test
    fun testQ3() {
        var testResult: TestResult
//--------------------------------------------------------------
        val guidQ301A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q3.01A",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(301.10),
            description = "Authorisation, Unmatched Amount",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ301A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q3.01B",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(301.10),
            description = "Repeat of Q3.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ301A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q3.01C",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(301.10),
            description = "Automatic Reversal of Q3.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ301A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        val guidQ302A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q3.02A",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(302.10),
            description = "Purchase, Unmatched Amount",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ302A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q3.02B",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(302.10),
            description = "Repeat of Q3.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ302A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q3.02C",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(302.10),
            description = "Automatic Reversal of Q3.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ302A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    //Q4. Unusual Auth Code (Terminal 1)
    @Test
    fun testQ4() {
        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q4.01",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(401.10),
            description = "Authorisation, Unusual Auth Code",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q4.02",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(402.10),
            description = "Purchase, Unusual Auth Code",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    //Q5. Extra fields (Terminal 1)
    @Test
    fun testQ5() {
        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q5.01",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(501.10),
            description = "Authorisation, Extra field 47",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q5.02",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(502.10),
            description = "Purchase, Extra fields 28",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q5.03",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(503.10),
            description = "Purchase, Extra field 48",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    //Q6. Incorrect Terminal ID (Terminal 1)
    @Test
    fun testQ6() {
        var testResult: TestResult
//--------------------------------------------------------------
        val guidQ601A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q6.01A",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(601.10),
            description = "Authorisation, Incorrect Terminal ID ",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ601A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_TID)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q6.01B",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(601.10),
            description = "Repeat of Q6.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ601A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_TID)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q6.01C",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(601.10),
            description = "Automatic Reversal of Q6.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ601A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        val guidQ602A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q6.02A",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(602.10),
            description = "Purchase, Incorrect Terminal ID ",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ602A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_TID)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q6.02B",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(602.10),
            description = "Repeat of Q6.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ602A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_TID)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q6.02C",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(602.10),
            description = "Automatic Reversal of Q6.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ602A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    //Q7. Alpha Numeric RC (Terminal 1)
    @Test
    fun testQ7() {
        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q7.01",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(701.10),
            description = "Alpha Numeric RC",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.UNKNOWN_CODE)
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q7.02",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(702.10),
            description = "Purchase, Alpha Numeric RC",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.UNKNOWN_CODE)
    }

    //Q8. Unmatched MTID (Terminal 1)
    @Test
    fun testQ8() {
        var testResult: TestResult
//--------------------------------------------------------------
        val guidQ801A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q8.01A",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(801.10),
            description = "Authorisation, Unmatched MTID ",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ801A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_RESPONSE_OPERATION)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q8.01B",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(801.10),
            description = "Repeat of Q8.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ801A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_RESPONSE_OPERATION)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q8.01C",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(801.10),
            description = "Automatic Reversal of Q8.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ801A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        val guidQ802A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q8.02A",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(802.10),
            description = "Purchase, Incorrect Terminal ID ",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ802A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_MESSAGE_FORMAT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q8.02B",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(802.10),
            description = "Repeat of Q8.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ802A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_RESPONSE_OPERATION)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q8.02C",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(802.10),
            description = "Automatic Reversal of Q8.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ802A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    //Q9. Unmatched Processing Code (Terminal 1)
    @Test
    fun testQ9() {
        var testResult: TestResult
//--------------------------------------------------------------
        val guidQ901A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q9.01A",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(901.10),
            description = "Authorisation, Unmatched Processing Code ",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ901A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_MESSAGE_FORMAT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q9.01B",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(901.10),
            description = "Repeat of Q9.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ901A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_MESSAGE_FORMAT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q9.01C",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(901.10),
            description = "Automatic Reversal of Q9.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ901A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        val guidQ902A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q9.02A",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(902.10),
            description = "Purchase, Unmatched Processing Code ",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ902A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_MESSAGE_FORMAT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q9.02B",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(902.10),
            description = "Repeat of Q9.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ902A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_MESSAGE_FORMAT)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q9.02C",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(902.10),
            description = "Automatic Reversal of Q9.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ902A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    //Q10. Unmatched STAN (Terminal 1)
    @Test
    fun testQ10() {
        var testResult: TestResult
//--------------------------------------------------------------
        val guidQ1001A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q10.01A",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(1001.10),
            description = "Authorisation, Unmatched STAN ",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ1001A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_STAN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q10.01B",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(1001.10),
            description = "Repeat of Q10.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ1001A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_STAN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q10.01C",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(1001.10),
            description = "Automatic Reversal of Q10.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ1001A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        val guidQ1002A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q10.02A",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(1002.10),
            description = "Purchase, Unmatched STAN ",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ1002A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_STAN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q10.02B",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(1002.10),
            description = "Repeat of Q10.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ1002A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_STAN)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q10.02C",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(1002.10),
            description = "Automatic Reversal of Q10.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ1002A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    //Q11. Unmatched Currency  (Terminal 1)
    @Test
    fun testQ11() {
        var testResult: TestResult
//--------------------------------------------------------------
        val guidQ1101A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q11.01A",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(1101.10),
            description = "Authorisation, Unmatched Currency  ",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ1101A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_CURRENCY_CODE)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q11.01B",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(1101.10),
            description = "Repeat of Q11.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ1101A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_CURRENCY_CODE)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q11.01C",
            testCard = TestCards.MAG_1,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(1101.10),
            description = "Automatic Reversal of Q11.01A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ1101A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)

//--------------------------------------------------------------
        val guidQ1102A = Utils.getGUID()
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q11.02A",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(1102.10),
            description = "Purchase, Unmatched Currency ",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            guid = guidQ1102A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_CURRENCY_CODE)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q11.02B",
            testCard = TestCards.MAG_2,
            operationType = OperationType.PURCHASE,
            amount = BigDecimal(1102.10),
            description = "Repeat of Q11.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ1102A,
            isRepeat = true
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_CURRENCY_CODE)

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "Q11.02C",
            testCard = TestCards.MAG_2,
            operationType = OperationType.AUTO_REVERSAL,
            amount = BigDecimal(1102.10),
            description = "Automatic Reversal of Q11.02A",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            parentGuid = guidQ1102A
        )
        println(testResult.resultMessage)
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }


}