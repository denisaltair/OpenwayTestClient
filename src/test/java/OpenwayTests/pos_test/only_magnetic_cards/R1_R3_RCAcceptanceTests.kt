package OpenwayTests.pos_test.only_magnetic_cards

import entities.TestResult
import enums.*
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import org.junit.Test
import other.Utils
import java.math.BigDecimal
import enums.OpenwayResponseCode.Companion as OpenwayResponseCode1

class R1_R3_RCAcceptanceTests {
    //R1.  Decline Response Codes (Terminal 1 with MAC and without MAC)
    @Test
    fun testR1() {
        println("R1. Decline Response Codes")

        var testResult: TestResult
        //--------------------------------------------------------------
        EmvCardsTesterHelper.sendRequest(
            operationType = OperationType.RECONCILIATION,
            description = "Reconciliation"
        )
////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "R1.01",
//            testCard = TestCards.MAG_6,
//            operationType = OperationType.PURCHASE,
//            amount = BigDecimal(10.0),
//            description = "Refer to Card Issuer. Key in Auth Code=999999, then upload transaction as offline.",
//            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
//            cardHolderVerificationType = CardHolderVerificationType.SIGNED
//        )
//        println(testResult.resultMessage)
//        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("01"))

////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "R1.02",
//            testCard = TestCards.MAG_6,
//            operationType = OperationType.PURCHASE,
//            amount = BigDecimal(20.0),
//            description = "Refer to card issuer's special condition. Key in Auth Code=999999, then upload transaction as offline.",
//            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
//            cardHolderVerificationType = CardHolderVerificationType.SIGNED
//        )
//        println(testResult.resultMessage)
//        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("02"))
//
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.03",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(30.0),
            description = "Invalid merchant",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("03"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.05",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(50.0),
            description = "Do not honour",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("05"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.06",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(60.0),
            description = "Error",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("06"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.09",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(90.0),
            description = "Request in progress",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("09"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.12",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(120.0),
            description = "Invalid Transaction",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("12"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.13",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(130.0),
            description = "Invalid Amount",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("13"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.14",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(140.0),
            description = "Invalid CardNumber",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("14"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.15",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(150.0),
            description = "No such issue",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("15"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.17",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(170.0),
            description = "Customer Cancellation",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("17"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.18",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(180.0),
            description = "Customer dispute",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("18"))


//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.19",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(190.0),
            description = "Re-enter transaction",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("19"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.20",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(200.0),
            description = "Invalid response",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("20"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.21",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(210.0),
            description = "No action taken",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("21"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.22",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(220.0),
            description = "Suspected Mailfunction",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("22"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.23",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(230.0),
            description = OpenwayResponseCode1.valueOfFromCode("23").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("23"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.24",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(240.0),
            description = OpenwayResponseCode1.valueOfFromCode("24").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("24"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.25",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(250.0),
            description = OpenwayResponseCode1.valueOfFromCode("25").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("25"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.26",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(260.0),
            description = OpenwayResponseCode1.valueOfFromCode("26").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("26"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.27",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(270.0),
            description = OpenwayResponseCode1.valueOfFromCode("27").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("27"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.28",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(280.0),
            description = OpenwayResponseCode1.valueOfFromCode("28").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("28"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.29",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(290.0),
            description = OpenwayResponseCode1.valueOfFromCode("29").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("29"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.30",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(300.0),
            description = OpenwayResponseCode1.valueOfFromCode("30").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("30"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.31",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(310.0),
            description = OpenwayResponseCode1.valueOfFromCode("31").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("31"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.32",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(320.0),
            description = OpenwayResponseCode1.valueOfFromCode("32").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("32"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.39",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(390.0),
            description = OpenwayResponseCode1.valueOfFromCode("39").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("39"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.40",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(400.0),
            description = OpenwayResponseCode1.valueOfFromCode("40").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("40"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.42",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(420.0),
            description = OpenwayResponseCode1.valueOfFromCode("42").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("42"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.44",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(440.0),
            description = OpenwayResponseCode1.valueOfFromCode("44").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("44"))

////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "R1.50",
//            testCard = TestCards.MAG_6,
//            operationType = OperationType.AUTHORISATION,
//            amount = BigDecimal(500.0),
//            description = OpenwayResponseCode1.valueOfFromCode("50").toString(),
//            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
//            cardHolderVerificationType = CardHolderVerificationType.SIGNED
//        )
//        println(testResult.resultMessage)
//        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("50"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.51",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(510.0),
            description = OpenwayResponseCode1.valueOfFromCode("51").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("51"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.52",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(520.0),
            description = OpenwayResponseCode1.valueOfFromCode("52").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("52"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.53",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(530.0),
            description = OpenwayResponseCode1.valueOfFromCode("53").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("53"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.54",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(540.0),
            description = OpenwayResponseCode1.valueOfFromCode("54").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("54"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.55",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(550.0),
            description = OpenwayResponseCode1.valueOfFromCode("55").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("55"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.56",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(560.0),
            description = OpenwayResponseCode1.valueOfFromCode("56").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("56"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.57",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(570.0),
            description = OpenwayResponseCode1.valueOfFromCode("57").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("57"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.58",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(580.0),
            description = OpenwayResponseCode1.valueOfFromCode("58").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("58"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.59",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(590.0),
            description = OpenwayResponseCode1.valueOfFromCode("59").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("59"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.60",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(600.0),
            description = OpenwayResponseCode1.valueOfFromCode("60").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("60"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.61",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(610.0),
            description = OpenwayResponseCode1.valueOfFromCode("61").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("61"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.62",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(620.0),
            description = OpenwayResponseCode1.valueOfFromCode("62").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("62"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.63",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(630.0),
            description = OpenwayResponseCode1.valueOfFromCode("63").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("63"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.64",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(640.0),
            description = OpenwayResponseCode1.valueOfFromCode("64").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("64"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.65",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(650.0),
            description = OpenwayResponseCode1.valueOfFromCode("65").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("65"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.66",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(660.0),
            description = OpenwayResponseCode1.valueOfFromCode("66").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("66"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.68",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(680.0),
            description = OpenwayResponseCode1.valueOfFromCode("68").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("68"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.75",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(750.0),
            description = OpenwayResponseCode1.valueOfFromCode("75").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("75"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.76",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(760.0),
            description = OpenwayResponseCode1.valueOfFromCode("76").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("76"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.77",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(770.0),
            description = OpenwayResponseCode1.valueOfFromCode("77").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("77"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.78",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(780.0),
            description = OpenwayResponseCode1.valueOfFromCode("78").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("78"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.79",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(790.0),
            description = OpenwayResponseCode1.valueOfFromCode("79").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("79"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.80",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(800.0),
            description = OpenwayResponseCode1.valueOfFromCode("80").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("80"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.81",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(810.0),
            description = OpenwayResponseCode1.valueOfFromCode("81").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("81"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.82",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(820.0),
            description = OpenwayResponseCode1.valueOfFromCode("82").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("82"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.83",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(830.0),
            description = OpenwayResponseCode1.valueOfFromCode("83").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("83"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.84",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(840.0),
            description = OpenwayResponseCode1.valueOfFromCode("84").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("84"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.85",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(850.0),
            description = OpenwayResponseCode1.valueOfFromCode("85").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("85"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.86",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(860.0),
            description = OpenwayResponseCode1.valueOfFromCode("86").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("86"))

////--------------------------------------------------------------
//        testResult = EmvCardsTesterHelper.sendRequest(
//            testNumber = "R1.87",
//            testCard = TestCards.MAG_6,
//            operationType = OperationType.AUTHORISATION,
//            amount = BigDecimal(870.0),
//            description = OpenwayResponseCode1.valueOfFromCode("87").toString(),
//            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
//            cardHolderVerificationType = CardHolderVerificationType.SIGNED
//        )
//        println(testResult.resultMessage)
//        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("87"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.88",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(880.0),
            description = OpenwayResponseCode1.valueOfFromCode("88").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("88"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.89",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(890.0),
            description = OpenwayResponseCode1.valueOfFromCode("89").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("89"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.90",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(900.0),
            description = OpenwayResponseCode1.valueOfFromCode("900").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("90"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.91",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(910.0),
            description = OpenwayResponseCode1.valueOfFromCode("91").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("91"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.92",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(920.0),
            description = OpenwayResponseCode1.valueOfFromCode("92").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("92"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.93",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(930.0),
            description = OpenwayResponseCode1.valueOfFromCode("93").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("93"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.94",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(940.0),
            description = OpenwayResponseCode1.valueOfFromCode("94").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("94"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.95",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(950.0),
            description = OpenwayResponseCode1.valueOfFromCode("95").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("95"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R1.96",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(960.0),
            description = OpenwayResponseCode1.valueOfFromCode("96").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("96"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            operationType = OperationType.RECONCILIATION,
            testNumber = "R1.71",
            description = "Reconciliation"
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    //R2. Pick-up Response Codes (Terminal 2 with MAC)
    @Test
    fun testR2() {
        println("R2. Pick-up Response Codes (Terminal 2 with MAC)")

        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R2.01",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(40.0),
            description = "Pick-Up",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("04"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R2.02",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(70.0),
            description = OpenwayResponseCode1.valueOfFromCode("07").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("07"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R2.03",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(330.0),
            description = OpenwayResponseCode1.valueOfFromCode("33").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("33"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R2.04",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(340.0),
            description = OpenwayResponseCode1.valueOfFromCode("34").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("34"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R2.05",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(350.0),
            description = OpenwayResponseCode1.valueOfFromCode("35").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("35"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R2.06",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(360.0),
            description = OpenwayResponseCode1.valueOfFromCode("36").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("36"))
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R2.07",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(370.0),
            description = OpenwayResponseCode1.valueOfFromCode("37").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("37"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R2.05",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(380.0),
            description = OpenwayResponseCode1.valueOfFromCode("38").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("38"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R2.09",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(410.0),
            description = OpenwayResponseCode1.valueOfFromCode("41").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("41"))

//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R2.10",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(430.0),
            description = OpenwayResponseCode1.valueOfFromCode("43").toString(),
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("43"))

    }

    //R3. R3. Special Approve Response Codes (Terminal 1 with MAC and without MAC)
    @Test
    fun testR3() {
        println("R3. Special Approve Response Codes (Terminal 1 with MAC and without MAC)")

        var testResult: TestResult
//--------------------------------------------------------------
        testResult = EmvCardsTesterHelper.sendRequest(
            testNumber = "R3.01",
            testCard = TestCards.MAG_6,
            operationType = OperationType.AUTHORISATION,
            amount = BigDecimal(80.0),
            description = "Honour with identification",
            cardSlotType = CardSlotType.MAGNETIC_STRIPE,
            cardHolderVerificationType = CardHolderVerificationType.SIGNED,
            isWithMac = true
        )
        println(testResult.resultMessage)
        TestCase.assertEquals(testResult.openwayResponseCode, OpenwayResponseCode1.valueOfFromCode("08"))
    }
}