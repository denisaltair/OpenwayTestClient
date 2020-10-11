import junit.framework.TestCase
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import java.io.*
import java.net.Socket

class TcpClientTest : TestCase() {
    @Test
    fun testJson() {
        try {
            val socket=Socket("192.168.1.105",1025)
            val output: OutputStream = socket.getOutputStream()
            val writer = PrintWriter(output, true)

            val input: InputStream = socket.getInputStream()
            val reader = BufferedReader(InputStreamReader(input))
            val jsonRequest=JSONObject()
            jsonRequest.put("currencyCode","0398")
            jsonRequest.put("amount","10.01")
            jsonRequest.put("mti","200")
            jsonRequest.put("processCode","000000")
            jsonRequest.put("tid","PM711117")
            jsonRequest.put("cardNumber","123242342342")
            jsonRequest.put("testDescription","fsfdsfsd")
            jsonRequest.put("operationId","123")
            jsonRequest.put("pinCode","1234")
            jsonRequest.put("entryMode","072")



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