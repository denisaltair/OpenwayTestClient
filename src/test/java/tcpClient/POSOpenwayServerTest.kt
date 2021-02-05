package tcpClient

import Config
import OutputClient
import entities.TransMessage
import enums.OpenwayResponseCode
import helpers.MagneticCardsTesterHelper
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.entities.EntryMode
import other.OpenwayUtils
import java.math.BigDecimal
import java.util.*

class POSOpenwayServerTest : TestCase() {

    fun testAuthorization() {
        OpenwayRequests.outputClient= OutputClient(Config.POS_N5_IP, Config.POS_N5_OPENWAY_SERVER_PORT)

        val transMessage= TransMessage()
        transMessage.amount= BigDecimal(301.4)
        transMessage.stan="000001"
        transMessage.transmissionDate= Date()
        transMessage.cardExpiredDate= OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPIRED_DATE)
        transMessage.entryMode= EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency= Currency.RUB


        val response=OpenwayRequests.authorizationRequest(transMessage)
        val bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        println("TestAuthorization Bank Response Code:"+ bankResponseCode.toString() + " " + bankResponseCode.code )
        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    fun testReconciliation() {
        val testResult = MagneticCardsTesterHelper.makeReconciliation()
        println("Reconciliation, rrn:" + testResult.rrn)

    }



    fun testGetReport() {
        val testResult = MagneticCardsTesterHelper.getReport()
        println("Reconciliation, rrn:" + testResult.rrn)

    }






}