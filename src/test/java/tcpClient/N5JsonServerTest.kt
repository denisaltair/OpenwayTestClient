package tcpClient

import Config
import N5OutputClient
import entities.TransMessage
import enums.CardSlotType
import enums.OperationType
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import other.Utils
import java.io.*
import java.math.BigDecimal
import java.net.Socket

class N5JsonServerTest : TestCase() {
    @Test
    fun testPurchaseICC() {
        try {
            val socket=Socket(Config.POS_N5_IP, Config.POS_N5_JSON_SERVER_PORT)
            val output: OutputStream = socket.getOutputStream()
            val writer = PrintWriter(output, true)

            val input: InputStream = socket.getInputStream()
            val reader = BufferedReader(InputStreamReader(input))
            val jsonRequest=JSONObject()
            jsonRequest.put("guid", Utils.getGUID())
            jsonRequest.put("amount","10.01")
            jsonRequest.put("operationType","PURCHASE")
            jsonRequest.put("description","test purchase")
            jsonRequest.put("cardSlotType", CardSlotType.ICC)

            writer.println(jsonRequest.toString())
            val text = reader.readLine()
            println(text)
            val jsonResponse=JSONObject(text)
            println(jsonResponse.toString())

        } catch (e: Exception) {

            Assert.fail("Возникло исключение при попытке соединения с сервером \n" + e.toString())
        }
    }

    @Test
    fun testPurchaseRFVisa() {
        try {
            val socket=Socket(Config.POS_N5_IP, Config.POS_N5_JSON_SERVER_PORT)
            val output: OutputStream = socket.getOutputStream()
            val writer = PrintWriter(output, true)

            val input: InputStream = socket.getInputStream()
            val reader = BufferedReader(InputStreamReader(input))
            val jsonRequest=JSONObject()
            jsonRequest.put("guid", Utils.getGUID())
            jsonRequest.put("amount","10.01")
            jsonRequest.put("operationType","PURCHASE")
            jsonRequest.put("tid",Config.TESTS_TERMINAL_1)
            jsonRequest.put("description","test purchase")
            jsonRequest.put("pan",Config.EMV_CARD9_PAN)
            jsonRequest.put("cardSlotType", CardSlotType.RF)

            writer.println(jsonRequest.toString())
            val text = reader.readLine()
            println(text)
            val jsonResponse=JSONObject(text)
            println(jsonResponse.toString())

        } catch (e: Exception) {

            Assert.fail("Возникло исключение при попытке соединения с сервером \n" + e.toString())
        }
    }







    fun testAuthorization() {
        val transMessage=TransMessage()
        transMessage.operationType=OperationType.AUTHORISATION
        transMessage.guid=Utils.getGUID()
        transMessage.amount= BigDecimal(301.04)
        transMessage.currency=Currency.RUB
        transMessage.tid=Config.TESTS_TERMINAL_1
        transMessage.description="D3.01A Card: EMV_3 Authorization SBT"

        val n5OutputClient=N5OutputClient()
        n5OutputClient.send(transMessage)

    }


}