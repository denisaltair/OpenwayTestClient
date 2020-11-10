import entities.TransMessage
import helpers.TransMessageHelper
import kz.multibank.cardposclient.exceptions.BaseException
import kz.multibank.cardposclient.exceptions.GatewayException
import org.jpos.iso.ISOMsg
import other.OpenwayUtils
import other.Utils
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketTimeoutException



class OutputClient(val host: String = Config.PREDHOST_IP, val port: Int = Config.PREDHOST_PORT, val soTimeout: Int = 20000) {
    var socket: Socket
    var inputStream: DataInputStream
    var outputStream: OutputStream

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

    fun close() {
        socket.close()
    }

    fun send(transMessage: TransMessage):TransMessage {
        try {
            var requestIsoMsg = TransMessageHelper.encodeTransMessageToISOMessage(transMessage)
            var isoMsgBody = OpenwayUtils.packOpenwayMessage(requestIsoMsg)
            isoMsgBody = Utils.shortToByteArray(isoMsgBody.size.toShort()) + isoMsgBody
            outputStream.write(isoMsgBody)
            outputStream.flush()
            val size = inputStream.readShort()
            var response = ByteArray(0)
            for (i in 0 until size) {
                response += inputStream.readByte()
            }
            val responseIsoMsg = OpenwayUtils.unpackOpenwayMessage(response)
            val result=TransMessageHelper.decodeISOMessageToTransMessage(responseIsoMsg)
            return result
        } catch (e:BaseException) {
            throw e
        }
        catch (e:Exception) {
            throw GatewayException(GatewayException.ErrorCode.UNKNOWN_ERROR,"",e)
        }
    }


}