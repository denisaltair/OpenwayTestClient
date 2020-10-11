import com.imohsenb.ISO8583.builders.ISOClientBuilder
import com.imohsenb.ISO8583.builders.ISOMessageBuilder
import com.imohsenb.ISO8583.entities.ISOMessage
import com.imohsenb.ISO8583.enums.FIELDS
import com.imohsenb.ISO8583.enums.MESSAGE_FUNCTION
import com.imohsenb.ISO8583.enums.MESSAGE_ORIGIN
import com.imohsenb.ISO8583.exceptions.ISOClientException
import com.imohsenb.ISO8583.interfaces.ISOClient
import entities.MyDate
import tcpClient.TcpClient


    fun main() {
     //   TcpClient.connect()
        checkConnectMessage()
    }

fun checkConnectMessage()  {
        val currDate = MyDate()
        val isoMessage: ISOMessage = ISOMessageBuilder.Packer(com.imohsenb.ISO8583.enums.VERSION.V1987)
            .networkManagement()
            .mti(MESSAGE_FUNCTION.Request, MESSAGE_ORIGIN.Acquirer)
            .processCode("930000")
            .setField(FIELDS.F7_TransmissionDataTime, currDate.toIso8583Date())
            .setField(FIELDS.F11_STAN, "000007")
            .setField(FIELDS.F41_CA_TerminalID, "11399991")
            //.setField(FIELDS.F60_Reserved_National, "1")
            //.setField(FIELDS.F63_Reserved_Private, "Denis")
            .build()
        isoMessage.getBody()

        val client: ISOClient = ISOClientBuilder.createSocket("18.184.184.49", 11113).build()
        client.connect()
        val message = client.sendMessageSync(isoMessage)
        val isoResponseMessage = ISOMessageBuilder.Unpacker().setMessage(message).build()
        println(isoResponseMessage.toString())

}


