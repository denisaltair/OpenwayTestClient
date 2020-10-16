package tcpClient

import com.imohsenb.ISO8583.builders.ISOClientBuilder
import com.imohsenb.ISO8583.builders.ISOMessageBuilder
import com.imohsenb.ISO8583.entities.ISOMessage
import com.imohsenb.ISO8583.enums.FIELDS
import com.imohsenb.ISO8583.enums.MESSAGE_FUNCTION
import com.imohsenb.ISO8583.enums.MESSAGE_ORIGIN
import com.imohsenb.ISO8583.interfaces.ISOClient
import entities.MyDate
import enums.OpenwayResponseCode
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test
import org.slf4j.LoggerFactory
import java.lang.Exception

class TcpClientTest : TestCase() {
    var logger = LoggerFactory.getLogger("fffffff")

    @Test
    fun testConnect() {
        try {
            TcpClient.connect()
        } catch (e: Exception) {
            Assert.fail("Возникло исключение при попытке соединения с сервером")
        }
    }

    @Test
    fun testOpenwayCheckConnect() {
        val currDate = MyDate()
        val isoMessage: ISOMessage = ISOMessageBuilder.Packer(com.imohsenb.ISO8583.enums.VERSION.V1987)
            .networkManagement()
            .mti(MESSAGE_FUNCTION.Request, MESSAGE_ORIGIN.Acquirer)
            .processCode("930000")
            .setField(FIELDS.F7_TransmissionDataTime, currDate.toIso8583Date())
            .setField(FIELDS.F11_STAN, "000001")
            .setField(FIELDS.F41_CA_TerminalID, Config.TESTS_TERMINAL_1)
            .setField(FIELDS.F63_Reserved_Private, "007PC20001015SVNEXGOAPPv1.00")
            .build()
        isoMessage.getBody()
        val client: ISOClient = ISOClientBuilder.createSocket(Config.PREDHOST_IP, Config.PREDHOST_PORT).build()
        client.connect()
        val message = client.sendMessageSync(isoMessage)
        val isoResponseMessage = ISOMessageBuilder.Unpacker().setMessage(message).build()
        val bankResponseCode= OpenwayResponseCode.valueOfFromCode(String(isoResponseMessage.getField(FIELDS.F39_ResponseCode)))
        println("Check connect Bank Response Code:"+ bankResponseCode.toString() + " " + bankResponseCode.code )
        assertEquals(bankResponseCode, OpenwayResponseCode.ACCEPTED)

    }



}