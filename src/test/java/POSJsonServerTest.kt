import junit.framework.TestCase
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import other.Utils
import java.io.*
import java.net.Socket

class TcpClientTest : TestCase() {
    @Test
    fun testJson() {
        try {
            val socket=Socket(Config.POS_N5_IP,Config.POS_N5_JSON_SERVER_PORT)
            val output: OutputStream = socket.getOutputStream()
            val writer = PrintWriter(output, true)

            val input: InputStream = socket.getInputStream()
            val reader = BufferedReader(InputStreamReader(input))
            val jsonRequest=JSONObject()
            jsonRequest.put("guid", Utils.getGUID())
            jsonRequest.put("amount","10.01")
            jsonRequest.put("operationType","PURCHASE")
            jsonRequest.put("description","test purchase")

            writer.println(jsonRequest.toString())
            val text = reader.readLine()
            println(text)
            val jsonResponse=JSONObject(text)
            println(jsonResponse.toString())



        } catch (e: Exception) {

            Assert.fail("Возникло исключение при попытке соединения с сервером \n" + e.toString())
        }
    }
}