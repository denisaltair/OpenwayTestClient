package OpenwayTests

import OpenwayRequests
import entities.TestResult
import enums.OpenwayResponseCode
import entities.TransMessage
import enums.OperationType
import helpers.OpenwayTesterHelper
import junit.framework.TestCase

import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.entities.EntryMode
import org.junit.Test
import other.OpenwayCryptoUtils
import other.OpenwayUtils
import other.Utils
import java.math.BigDecimal
import java.util.*

class MagneticCardTestsM1_V1 : TestCase() {
    //M1. PIN-Based Transactions (Terminals 1, 2)

    val key = Utils.hexStringToByteArray("5413926DE0296E91C7F413387064FBD0")

    val pinBlockCard1 = OpenwayCryptoUtils.calcPinBlock(key, Config.CARD1_PIN, Config.CARD1_PAN)
    val pinBlockCard2 = OpenwayCryptoUtils.calcPinBlock(key, Config.CARD2_PIN, Config.CARD2_PAN)
    val badPinBlockCard = OpenwayCryptoUtils.calcPinBlock(key, "987654", Config.CARD2_PAN)

    @Test
    fun testM1() {
        var response: TransMessage
        var openwayResponseCode: OpenwayResponseCode

        var transMessageCard1 = TransMessage()
        transMessageCard1.pan = Config.CARD1_PAN
        transMessageCard1.expiredDate = OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessageCard1.entryMode = EntryMode.MAGNET_PBT
        transMessageCard1.track2 = Config.CARD1_TRACK2
        transMessageCard1.pinBlockData = pinBlockCard1
        transMessageCard1.tid = Config.TESTS_TERMINAL_1
        transMessageCard1.currency = Currency.RUB

        var transMessageCard2 = TransMessage()
        transMessageCard2.pan = Config.CARD2_PAN
        transMessageCard2.expiredDate = OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessageCard2.entryMode = EntryMode.MAGNET_PBT
        transMessageCard2.track2 = Config.CARD2_TRACK2
        transMessageCard2.pinBlockData = pinBlockCard2
        transMessageCard2.tid = Config.TESTS_TERMINAL_1
        transMessageCard2.currency = Currency.USD


//-----------------M1.01------------------------------------------
        transMessageCard1.guid = Utils.getGUID()
        transMessageCard1.amount = BigDecimal(101)
        transMessageCard1.transmissionDate = Date()


        response = OpenwayRequests.purchaseRequest(transMessageCard1)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M1.01 Success, rrn:" + response.rrn)

//-----------------M1.02------------------------------------------
        transMessageCard2.guid = Utils.getGUID()
        transMessageCard2.amount = BigDecimal(102)
        transMessageCard2.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard2)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M1.02 Success rrn:" + response.rrn)

//-----------------M1.03  Max Value------------------------------------------
        transMessageCard2.guid = Utils.getGUID()
        transMessageCard2.amount = BigDecimal(9999999999.99)
        transMessageCard2.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard2)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M1.03 Max Value Success rrn:" + response.rrn)

//-----------------M1.04  BAD PIN------------------------------------------
        transMessageCard2.guid = Utils.getGUID()
        transMessageCard2.amount = BigDecimal(104)
        transMessageCard2.pinBlockData = badPinBlockCard
        transMessageCard2.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard2)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.WRONG_PIN)
        println("M1.04 Wrong PIN Success rrn:" + response.rrn)

//-----------------M1.05  Good PIN------------------------------------------
        transMessageCard2.guid = Utils.getGUID()
        transMessageCard2.amount = BigDecimal(105)
        transMessageCard2.pinBlockData = pinBlockCard2
        transMessageCard2.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard2)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M1.05 Good PIN Success rrn:" + response.rrn)

//-----------------M1.06 ------------------------------------------
        transMessageCard2.guid = Utils.getGUID()
        transMessageCard2.amount = BigDecimal(106)
        transMessageCard2.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard2)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M1.06 Success rrn:" + response.rrn)

//-----------------M1.07 ------------------------------------------
        transMessageCard1.guid = Utils.getGUID()
        transMessageCard1.amount = BigDecimal(106)
        transMessageCard1.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard1)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M1.07 rrn:" + response.rrn)

//-----------------M1.08 ------------------------------------------
        transMessageCard2.guid = Utils.getGUID()
        transMessageCard2.amount = BigDecimal(0.01)
        transMessageCard2.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard2)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M1.08 Success rrn:" + response.rrn)

//-----------------M1.09 ------------------------------------------
        transMessageCard1.guid = Utils.getGUID()
        transMessageCard1.amount = BigDecimal(109)
        transMessageCard1.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard1)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M1.09 rrn:" + response.rrn)

    }

    //M2. Signature-Based Transactions (Terminals 1, 2)
    @Test
    fun testM2() {

        var response: TransMessage
        var openwayResponseCode: OpenwayResponseCode

        var transMessageCard1 = TransMessage()
        transMessageCard1.pan = Config.CARD1_PAN
        transMessageCard1.expiredDate = OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessageCard1.entryMode = EntryMode.MAGNET_SBT
        transMessageCard1.track2 = Config.CARD1_TRACK2
        transMessageCard1.tid = Config.TESTS_TERMINAL_1
        transMessageCard1.currency = Currency.RUB

        var transMessageCard2 = TransMessage()
        transMessageCard2.pan = Config.CARD2_PAN
        transMessageCard2.expiredDate = OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessageCard2.entryMode = EntryMode.MAGNET_SBT
        transMessageCard2.track2 = Config.CARD2_TRACK2
        transMessageCard2.tid = Config.TESTS_TERMINAL_1
        transMessageCard2.currency = Currency.USD


//-----------------M2.01------------------------------------------
        transMessageCard2.guid = Utils.getGUID()
        transMessageCard2.amount = BigDecimal(201)
        transMessageCard2.transmissionDate = Date()


        response = OpenwayRequests.purchaseRequest(transMessageCard2)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M2.01 Success, rrn:" + response.rrn)

//-----------------M2.02------------------------------------------
        transMessageCard2.guid = Utils.getGUID()
        transMessageCard2.amount = BigDecimal(Config.MAX_AMOUNT_VALUE)
        transMessageCard2.transmissionDate = Date()


        response = OpenwayRequests.purchaseRequest(transMessageCard2)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M2.02 Success, rrn:" + response.rrn)

//-----------------M2.03------------------------------------------
        transMessageCard1.guid = Utils.getGUID()
        transMessageCard1.amount = BigDecimal(203)
        transMessageCard1.transmissionDate = Date()


        response = OpenwayRequests.purchaseRequest(transMessageCard1)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M2.03 Success, rrn:" + response.rrn)
    }

    //M3.Manual Entry Transactions (Terminals 1, 2, 4)
    @Test
    fun testM3() {
        println("M3.Manual Entry Transactions (Terminals 1, 2, 4)")
        var response: TransMessage
        var openwayResponseCode: OpenwayResponseCode

        var transMessageCard1 = TransMessage()
        transMessageCard1.pan = Config.CARD1_PAN
        transMessageCard1.expiredDate = OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessageCard1.entryMode = EntryMode.MANUAL_SBT
        transMessageCard1.tid = Config.TESTS_TERMINAL_1
        transMessageCard1.currency = Currency.RUB
        transMessageCard1.reservedPrivate = "0081610" + Config.CARD1_CVV2

        var transMessageCard2 = TransMessage()
        transMessageCard2.pan = Config.CARD2_PAN
        transMessageCard2.expiredDate = OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessageCard2.entryMode = EntryMode.MANUAL_SBT
        transMessageCard2.tid = Config.TESTS_TERMINAL_1
        transMessageCard2.currency = Currency.USD
        transMessageCard2.reservedPrivate = "0081610" + Config.CARD2_CVV2


//-----------------M3.01------------------------------------------
        transMessageCard2.guid = Utils.getGUID()
        transMessageCard2.amount = BigDecimal(301)
        transMessageCard2.transmissionDate = Date()


        response = OpenwayRequests.purchaseRequest(transMessageCard2)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M3.01 Success, rrn:" + response.rrn)

//-----------------M3.02------------------------------------------
        transMessageCard1.guid = Utils.getGUID()
        transMessageCard1.amount = BigDecimal(302)
        transMessageCard1.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard1)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M3.02 Success, rrn:" + response.rrn)

//-----------------M3.03------------------------------------------
        transMessageCard1.guid = Utils.getGUID()
        transMessageCard1.amount = BigDecimal(303)
        transMessageCard1.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard1)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M3.03 Success, rrn:" + response.rrn)

//-----------------M3.04------------------------------------------
        transMessageCard1.guid = Utils.getGUID()
        transMessageCard1.amount = BigDecimal(304)
        transMessageCard1.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard1)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M3.04 Success, rrn:" + response.rrn)

//-----------------M3.05------------------------------------------
        transMessageCard2.guid = Utils.getGUID()
        transMessageCard2.amount = BigDecimal(Config.MIN_AMOUNT_VALUE)
        transMessageCard2.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard2)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M3.05 Success, rrn:" + response.rrn)

//-----------------M3.06------------------------------------------
        transMessageCard1.guid = Utils.getGUID()
        transMessageCard1.amount = BigDecimal(Config.MAX_AMOUNT_VALUE)
        transMessageCard1.transmissionDate = Date()

        response = OpenwayRequests.purchaseRequest(transMessageCard1)
        openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M3.06 Success, rrn:" + response.rrn)

    }

    //M8. Retail Refund (Terminals 1, 3, 4)
    @Test
    fun testM8() {
        println("M8. Retail Refund (Terminals 1, 3, 4)")
        var guid = ""
        var testResult: TestResult

//-----------------M8.01A------------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(801),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M8.01A Success PBT (For terminal 1,3 only), rrn:" + testResult.rrn)

//-----------------M8.01B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REFUND, amount = BigDecimal(801),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M8.01B Refund M8.01A, rrn:" + testResult.rrn)

//-----------------M8.02A------------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(802),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M8.02A Success SBT (For terminal 1,3 only), rrn:" + testResult.rrn)

//-----------------M8.02B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REFUND, amount = BigDecimal(802),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M8.02B Refund M8.02A, rrn:" + testResult.rrn)


//===============================================================
//----------------M8.03A
        val m803AGuid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(803),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MANUAL_SBT, tid = Config.TESTS_TERMINAL_1, guid = m803AGuid)

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M8.03A Manual, rrn:" + testResult.rrn)

//----------------M8.03B-----------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REFUND, amount = BigDecimal(80.3),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = m803AGuid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M8.03B Refund M8.03A, rrn:" + testResult.rrn)


//-----------------M8.04A------------------------------------------
        guid=Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(804),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid)

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M8.04A PBT (For terminal 1,3 only) Success, rrn:" + testResult.rrn)

//----------------M8.04B-----------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REFUND, amount = BigDecimal(8040),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("M8.04B Refund M8.04B Wrong Amount, rrn:" + testResult.rrn)

//-----------------M8.05A------------------------------------------
        guid=Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(805),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, guid = guid)

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M8.05A SBT (For terminal 1,3 only) Success, rrn:" + testResult.rrn)

//----------------M8.05B-----------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REFUND, amount = BigDecimal(805),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.SYSTEM_DESTROYED)
        println("M8.05B Refund M8.05A, RC=96, rrn:" + testResult.rrn)

//==================================================================
//----------------M8.03C-----------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REFUND, amount = BigDecimal(80.3),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = m803AGuid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M8.03C Refund M8.03C, rrn:" + testResult.rrn)

//----------------M8.03D-----------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REFUND, amount = BigDecimal(8030),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, parentGuid = m803AGuid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M8.03DB Refund M8.03A Wrong Amount, rrn:" + testResult.rrn)

    }

    //M9. Retail Authorisation and Authorisation Confirmation (Terminals 1, 3)
    @Test
    fun testM9() {
        println("M9 Retail Authorisation and Authorisation Confirmation (Terminals 1, 3)")

        var guid = ""
        var testResult: TestResult

        val block01_02 = true
        val block04_05 = true
        val block03 = true
        val block06_08 = true


        if (block01_02) {
//===================================================================
//-----------------M9.01A------------------------------------------

            guid = Utils.getGUID()
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION, amount = BigDecimal(901),
                pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.01A Authorisation (PBT), rrn:" + testResult.rrn)


//-----------------M9.01B------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION_CONFIRMATION,
                amount = BigDecimal(901),
                pan = Config.CARD2_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.01B Manual Confirmation M9.01A, rrn:" + testResult.rrn)


//===================================================================
//-----------------M9.02A------------------------------------------
            guid = Utils.getGUID()
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION, amount = BigDecimal(902),
                pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, guid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.02A Authorisation (SBT), rrn:" + testResult.rrn)


//-----------------M9.02B------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION_CONFIRMATION,
                amount = BigDecimal(90.2),
                pan = Config.CARD2_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.02B Manual Confirmation M9.02A, rrn:" + testResult.rrn)

        }


        val guidM903A = Utils.getGUID()
        val guidM903B = Utils.getGUID()
        if (block03) {
//===================================================================
//-----------------M9.03A-----------------------------------------

            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION,
                amount = BigDecimal(903),
                pan = Config.CARD1_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                guid = guidM903A
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.03A Authorisation (Manual), rrn:" + testResult.rrn)

//-----------------M9.03B------------------------------------------

            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION_CONFIRMATION,
                amount = BigDecimal(903),
                pan = Config.CARD1_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                guid = guidM903B,
                parentGuid = guidM903A
            )


            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.03B Manual Confirmation M9.03A, rrn:" + testResult.rrn)
        }

        if (block04_05) {

//===================================================================
//-----------------M9.04A-----------------------------------------
            guid = Utils.getGUID()
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION, amount = BigDecimal(904),
                pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.04A Authorisation (PBT), rrn:" + testResult.rrn)

//-----------------M9.04B------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION_CONFIRMATION,
                amount = BigDecimal(9040),
                pan = Config.CARD1_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
            println("M9.04B Manual Confirmation M9.04B, Wrong Amount rrn:" + testResult.rrn)

//-----------------M9.04C------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION_CONFIRMATION,
                amount = BigDecimal(904),
                pan = Config.CARD1_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.04C Manual Confirmation M9.04C:" + testResult.rrn)


//===================================================================
//-----------------M9.05A-----------------------------------------
            guid = Utils.getGUID()
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION, amount = BigDecimal(905),
                pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, guid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.05A Authorisation (SBT), rrn:" + testResult.rrn)

//-----------------M9.05B------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION_CONFIRMATION,
                amount = BigDecimal(905),
                pan = Config.CARD1_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.05B Manual Confirmation M9.05A rrn:" + testResult.rrn)
        }
        if (block03) {
//==========================================================================
//-----------------M9.03C------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION_CONFIRMATION,
                amount = BigDecimal(903),
                pan = Config.CARD1_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guidM903A
            )
            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.FIX_ERROR)
            println("M9.03C Manual Confirmation M9.03A Repeat:" + testResult.rrn)

//-----------------M9.03D------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION_CONFIRMATION,
                amount = BigDecimal(9030),
                pan = Config.CARD1_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guidM903A
            )
            //assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
            //println("M9.03D Manual Confirmation M9.03A Repeat. Wrong Amount:" + testResult.rrn)

//-----------------M9.03E------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.REFUND,
                amount = BigDecimal(902),
                pan = Config.CARD1_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guidM903B
            )
            //assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
            //println("M9.03E Refund 9.03B:" + testResult.rrn)

        }
        if (block06_08) {

//===================================================================
//-----------------M9.06A-----------------------------------------
            guid = Utils.getGUID()
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION, amount = BigDecimal(906),
                pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.06A Authorisation (PBT), rrn:" + testResult.rrn)

//-----------------M9.06B------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION_CONFIRMATION,
                amount = BigDecimal(906),
                pan = Config.CARD1_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guid,
                currency = Currency.USD
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.FIX_ERROR)
            println("M9.06B Manual Confirmation M9.06A. Wrong Currency rrn:" + testResult.rrn)


//===================================================================
//-----------------M9.07A-----------------------------------------
            guid = Utils.getGUID()
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION, amount = BigDecimal(907),
                pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, guid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.07A Authorisation (SBT), rrn:" + testResult.rrn)

//-----------------M9.07B------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.REVERSAL,
                amount = BigDecimal(907),
                pan = Config.CARD1_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.07B Manual Confirmation M9.07A. rrn:" + testResult.rrn)


//===================================================================
//-----------------M9.08A-----------------------------------------
            guid = Utils.getGUID()
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.AUTHORISATION, amount = BigDecimal(908),
                pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.08A Authorisation (PBT), rrn:" + testResult.rrn)

//-----------------M9.08B------------------------------------------
            testResult = OpenwayTesterHelper.sendRequest(
                operationType = OperationType.REVERSAL,
                amount = BigDecimal(90.8),
                pan = Config.CARD2_PAN,
                entryMode = EntryMode.MANUAL_SBT,
                tid = Config.TESTS_TERMINAL_1,
                parentGuid = guid
            )

            assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
            println("M9.08B Manual Confirmation M9.08A: rrn:" + testResult.rrn)

        }
//===================================================================
//-----------------M9.11A-----------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(912),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M9.11A Authorisation (PBT), rrn:" + testResult.rrn)

//-----------------M9.11B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(912),
            pan = Config.CARD1_PAN,
            entryMode = EntryMode.MAGNET_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.FIX_ERROR)
        println("M9.11B Confirmation M9.11A. Wrong Card Number rrn:" + testResult.rrn)

//===================================================================
//-----------------M9.12A-----------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(913),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M9.12A Authorisation (PBT), rrn:" + testResult.rrn)

//-----------------M9.12B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(915),
            pan = Config.CARD1_PAN,
            entryMode = EntryMode.MAGNET_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M9.12B Confirmation M9.12A. More Amount:" + testResult.rrn)

//===================================================================
//-----------------M9.13A-----------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(914),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M9.13A Authorisation (PBT), rrn:" + testResult.rrn)

//-----------------M9.13B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION_CONFIRMATION,
            amount = BigDecimal(911),
            pan = Config.CARD2_PAN,
            entryMode = EntryMode.MAGNET_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M9.13B Confirmation M9.13A. Less Amount:" + testResult.rrn)
    }

    //M15. Universal Reversal (Terminals 1, 2, 4)
    @Test
    fun testM15() {
        println("M15. Universal Reversal (Terminals 1, 2, 4)")
        var guid = ""
        var testResult: TestResult

//===================================================================
//-----------------M15.01A------------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(1501),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M15.01A PBT, rrn:" + testResult.rrn)


//-----------------M15.01B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REVERSAL,
            amount = BigDecimal(1501),
            pan = Config.CARD1_PAN,
            entryMode = EntryMode.MANUAL_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M15.01B Universal Reversal M15.01A, rrn:" + testResult.rrn)

//===================================================================
//-----------------M15.02A------------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(1502),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M15.01A SBT, rrn:" + testResult.rrn)


//-----------------M15.02B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REVERSAL,
            amount = BigDecimal(1502),
            pan = Config.CARD2_PAN,
            entryMode = EntryMode.MANUAL_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M15.02B Universal Reversal M15.02A, rrn:" + testResult.rrn)

//===================================================================
//-----------------M15.03A------------------------------------------
        val guidM1503A = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(1503),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MANUAL_SBT, tid = Config.TESTS_TERMINAL_1, guid = guidM1503A
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M15.03A Authorisation Manual (Not for Terminal 4), rrn:" + testResult.rrn)


//-----------------M15.03B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REVERSAL,
            amount = BigDecimal(1503),
            pan = Config.CARD1_PAN,
            entryMode = EntryMode.MANUAL_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guidM1503A
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M15.03B Universal Reversal M15.03A, rrn:" + testResult.rrn)

//===================================================================
//-----------------M15.04A------------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(1504),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MANUAL_SBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M15.04A Manual, rrn:" + testResult.rrn)


//-----------------M15.04B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REVERSAL,
            amount = BigDecimal(150.4),
            pan = Config.CARD1_PAN,
            entryMode = EntryMode.MANUAL_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M15.03B Universal Reversal M15.03A, rrn:" + testResult.rrn)


//===================================================================
//-----------------M15.05A------------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(1505),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M15.05A SBT, rrn:" + testResult.rrn)


//-----------------M15.05B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REVERSAL,
            amount = BigDecimal(15050),
            pan = Config.CARD2_PAN,
            entryMode = EntryMode.MANUAL_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("M15.05B Universal Reversal 15.05A. Wrong Amount, rrn:" + testResult.rrn)

//-----------------M15.03C------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REVERSAL,
            amount = BigDecimal(1503),
            pan = Config.CARD1_PAN,
            entryMode = EntryMode.MANUAL_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guidM1503A
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.FIX_ERROR)
        println("M15.03C Universal Reversal M15.03A Repeat, rrn:" + testResult.rrn)

//-----------------M15.03D------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.REVERSAL,
            amount = BigDecimal(15030),
            pan = Config.CARD1_PAN,
            entryMode = EntryMode.MANUAL_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guidM1503A
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("M15.05B Universal Reversal 15.05A. Wrong Amount, rrn:" + testResult.rrn)
    }

    //V1. Purchase Return (Terminals 1, 2)
    @Test
    fun testV1() {
        println("V1. Purchase Return (Terminals 1, 2)")
        var guid = ""
        var testResult: TestResult

//===================================================================
//-----------------V1.01A------------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(101.07),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("V1.01A PBT, rrn:" + testResult.rrn)


//-----------------V1.01B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE_RETURN,
            amount = BigDecimal(101.07),
            pan = Config.CARD2_PAN,
            entryMode = EntryMode.MAGNET_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("V1.01B Purchase Return V1.01A, rrn:" + testResult.rrn)

//-----------------V1.02A------------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(102.07),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("V1.02A SBT, rrn:" + testResult.rrn)


//-----------------V1.02B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE_RETURN,
            amount = BigDecimal(102.07),
            pan = Config.CARD2_PAN,
            entryMode = EntryMode.MAGNET_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("V1.02B Purchase Return V1.02A, rrn:" + testResult.rrn)


//-----------------V1.03A------------------------------------------
        guid = Utils.getGUID()
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(103.07),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MANUAL_SBT, tid = Config.TESTS_TERMINAL_1, guid = guid
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("V1.03A Manual, rrn:" + testResult.rrn)


//-----------------V1.03B------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE_RETURN,
            amount = BigDecimal(103.07),
            pan = Config.CARD1_PAN,
            entryMode = EntryMode.MAGNET_SBT,
            tid = Config.TESTS_TERMINAL_1,
            parentGuid = guid
        )
        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("V1.03B Purchase Return V1.03A, rrn:" + testResult.rrn)



    }
}