import entities.TransMessage
import helpers.TransMessageHelper
import kz.multibank.cardposclient.exceptions.BaseException
import kz.multibank.cardposclient.exceptions.GatewayException
import org.json.JSONObject
import java.io.*
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketTimeoutException


class N5OutputClient(val host: String = Config.POS_N5_IP, val port: Int = Config.POS_N5_JSON_SERVER_PORT, val soTimeout: Int = 20000) {
    private var socket: Socket
    private var inputStream: DataInputStream
    private var outputStream: OutputStream

    init {
        try {
            socket = Socket()
            socket.soTimeout = soTimeout
            socket.connect(InetSocketAddress(host, port), soTimeout);
            inputStream = DataInputStream(socket.getInputStream())

            outputStream = BufferedOutputStream(socket.getOutputStream())
        } catch (e: SocketTimeoutException) {
            throw GatewayException(GatewayException.ErrorCode.SOCKET_OPEN_TIMEOUT, "OpenwayGateway Socket open timeout to $host:$port timeout: $soTimeout", e)
        } catch (e: Exception) {
            throw GatewayException(GatewayException.ErrorCode.UNKNOWN_ERROR, "OpenwayGateway Can't connect to $host:$port timeout: $soTimeout", e)
        }
    }



    fun send(transMessage: TransMessage):TransMessage {
        try {
            val requestBody=TransMessageHelper.transMessageToJsonRequest(transMessage).toString()
            val writer = PrintWriter(outputStream, true)
            writer.println(requestBody)

            val reader = BufferedReader(InputStreamReader(inputStream))

            while (!reader.ready()) {

            }
            val text = reader.readLine()
            val jsonResponse=JSONObject(text)
            return TransMessageHelper.jsonResponseToTransMessage(jsonResponse)
        } catch (e:BaseException) {
            throw e
        }
        catch (e:Exception) {
            throw GatewayException(GatewayException.ErrorCode.UNKNOWN_ERROR,"",e)
        }
    }


}