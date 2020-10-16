package other

import entities.MyDate
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*
import kotlin.experimental.and
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

object Utils {
    private val hexArray = "0123456789ABCDEF".toCharArray()
    fun bytesToHex(bytes: ByteArray): String {
        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v: Int = bytes[j].toInt() and 0xFF
            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }

    fun hexStringToByteArray(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4)
                    + Character.digit(s[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

    fun shortToByteArray(value:Short):ByteArray=
        ByteBuffer.allocate(2).putShort(value).array()

    fun leftPad(str: String?, size: Int, padChar: String?): String {
        val padded = StringBuilder(str ?: "")
        while (padded.length < size) {
            padded.insert(0, padChar)
        }
        return padded.toString()
    }

    fun leftPad(str: String?, size: Int, padChar: Char): String {
        val padded = StringBuilder(str ?: "")
        while (padded.length < size) {
            padded.insert(0, padChar)
        }
        return padded.toString()
    }

    fun getGUID()=UUID.randomUUID().toString().substring(0..14)


}