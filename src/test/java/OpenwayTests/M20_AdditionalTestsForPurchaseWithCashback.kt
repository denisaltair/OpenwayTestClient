package OpenwayTests

import entities.TestResult
import entities.TransMessage
import enums.OpenwayResponseCode
import enums.OperationType
import helpers.OpenwayTesterHelper
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency

import kz.multibank.cardposclient.entities.EntryMode
import org.junit.Test
import other.Utils
import java.math.BigDecimal
import java.util.*


class M20_AdditionalTestsForPurchaseWithCashback : TestCase() {

    @Test
    fun testM20() {
        println("M20. Purchase with Cashback (Terminals 1)")
        var testResult: TestResult
//===================================================================
//-----------------M20.01---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE_WITH_CASH_BACK, amount = BigDecimal(2001.63),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, cashBackAmount = BigDecimal(10),
            currency = Currency.USD
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M20.01 Purchase with cashback SBT, rrn:" + testResult.rrn)

//-----------------M20.02---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE_WITH_CASH_BACK, amount = BigDecimal(2002.63),
            pan = Config.CARD1_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, cashBackAmount = BigDecimal(10),
            currency = Currency.RUB
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M20.02 Purchase with cashback PBT, rrn:" + testResult.rrn)

//-----------------M20.03---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE_WITH_CASH_BACK, amount = BigDecimal(2003.63),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_PBT, tid = Config.TESTS_TERMINAL_1, cashBackAmount = BigDecimal(10),
            currency = Currency.USD
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M20.03 Purchase with cashback PBT, rrn:" + testResult.rrn)

//-----------------M20.04---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE_WITH_CASH_BACK, amount = BigDecimal(2004.63),
            pan = Config.CARD2_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1, cashBackAmount = BigDecimal(10),
            currency = Currency.RUB
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("M20.04 Purchase with cashback SBT, rrn:" + testResult.rrn)

//-----------------M20.05---------------------------------------------
        val transMessage = TransMessage()
        transMessage.guid = Utils.getGUID()
        transMessage.transmissionDate = Date()
        transMessage.tid = Config.TESTS_TERMINAL_1

        val response = OpenwayRequests.reconciliationRequest(transMessage)
        val openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("R20.05 Reconciliation, rrn:" + response.rrn)

    }



}