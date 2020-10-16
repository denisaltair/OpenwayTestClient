
import enums.OpenwayResponseCode
import entities.TransMessage
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency
import kz.multibank.cardposclient.entities.EntryMode
import org.junit.Test
import other.OpenwayUtils
import other.Utils
import java.math.BigDecimal
import java.util.*

class OpenwayRequestsTest : TestCase() {

    fun testCheckConnect() {
        val response=OpenwayRequests.checkConnect()
        val bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        println("Check connect Bank Response Code:"+ bankResponseCode.toString() + " " + bankResponseCode.code )
        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    fun testAuthorization() {
        val transMessage= TransMessage()
        transMessage.pan="1000011200000011"
        transMessage.amount= BigDecimal(101.1)
        transMessage.stan="000001"
        transMessage.transmissionDate= Date()
        transMessage.expiredDate=OpenwayUtils.isoExpirationDateToDate(Config.CARD_EXPDATE)
        transMessage.entryMode=EntryMode.MAGNET_SBT
        transMessage.track2=Config.CARD1_TRACK2
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.currency=Currency.RUB

        val response=OpenwayRequests.authorizationRequest(transMessage)
        val bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        println("TestAuthorization Bank Response Code:"+ bankResponseCode.toString() + " " + bankResponseCode.code )
        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    fun testAuthorizationRepeat() {
        val transMessage= TransMessage()
        transMessage.guid="1"
        transMessage.transmissionDate= Date()
        transMessage.stan="000011"

        val response=OpenwayRequests.authorizationRequest(transMessage, true)
        val bankResponseCode= response.openwayResponseCode?: OpenwayResponseCode.UNKNOWN_CODE
        println("TestAuthorization Bank Response Code:"+ bankResponseCode.toString() + " " + bankResponseCode.code )
        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
    }

    @Test
    fun testAutomaticReversalRequest() {
        val transMessage= TransMessage()
        transMessage.guid=Utils.getGUID()
        transMessage.parentGuid="734e4953-f0d7-4"
        transMessage.pan=Config.CARD1_PAN
        transMessage.transmissionDate= Date()
        val response=OpenwayRequests.automaticReversalRequest(transMessage)
        //println("TestAuthorization Bank Response Code:"+ bankResponseCode.toString() + " " + bankResponseCode.code )
        //assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)
    }
}