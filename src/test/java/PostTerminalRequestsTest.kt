import enums.CardHolderVerificationType
import enums.OpenwayResponseCode
import enums.OperationType
import enums.TestCards
import helpers.EmvCardsTesterHelper
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency
import java.math.BigDecimal

import kotlin.Exception

class PostTerminalRequestsTest : TestCase() {
    fun testConnect() {
        try {
            val outputClient = OutputClient(Config.POS_N5_IP, Config.POS_N5_OPENWAY_SERVER_PORT)
            assert(true)
        } catch (e: Exception) {
            assert(false)
        }

    }

    fun testPurchase() {
        val testResult = EmvCardsTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(100),
            cardHolderVerificationType = CardHolderVerificationType.SIGNED, tid = Config.TESTS_TERMINAL_1, currency = Currency.RUB,testCard = TestCards.EMV_3
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ACCEPTED)


    }






}