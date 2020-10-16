package other

import exceptions.CryptoException
import java.math.BigInteger
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CryptoUtils {
    fun encrypt3DES(value:ByteArray, key: ByteArray): ByteArray {
        return try {
            val keyBytes = Arrays.copyOf(key, 24)
            var j = 0
            var k = 16
            while (j < 8) {
                keyBytes[k++] = keyBytes[j++]
            }
            val keySpec: SecretKey = SecretKeySpec(keyBytes, "DESede")
            val decipher: Cipher
            decipher = Cipher.getInstance("DESede/ECB/NoPadding")
            decipher.init(Cipher.ENCRYPT_MODE, keySpec)
            decipher.doFinal(value)
        } catch (e: Exception) {
            throw CryptoException(CryptoException.ErrorCode.ENCRYPT_3DES, "", e)
        }
    }


    fun encryptDES(clear: ByteArray, raw: ByteArray): ByteArray {
        val skeySpec = SecretKeySpec(raw, "DES")
        val cipher = Cipher.getInstance("DES")
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec)
        return cipher.doFinal(clear).copyOfRange(0,8)
    }

    fun decryptDES(clear: ByteArray, raw: ByteArray): ByteArray {
        val skeySpec = SecretKeySpec(raw, "DES")
        val cipher = Cipher.getInstance("DES/ECB/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, skeySpec)
        return cipher.doFinal(clear).copyOfRange(0,8)
    }

    fun getXor(val1: ByteArray, val2: ByteArray): ByteArray {
        val i1 = BigInteger(val1)
        val i2 = BigInteger(val2)
        val res = i1.xor(i2)
        // val countSymbols= max(val1.size, val2.size)


        return res.toByteArray()
    }

    fun decrypt3DES(dataHex: String, keyHex: String): String {
        val digestOfPassword = Utils.hexStringToByteArray(keyHex)
        val keyBytes = Arrays.copyOf(digestOfPassword, 24)
        var j = 0
        var k = 16
        while (j < 8) {
            keyBytes[k++] = keyBytes[j++]
        }
        val keySpec: SecretKey = SecretKeySpec(keyBytes, "DESede")
        val decipher: Cipher
        return try {
            decipher = Cipher.getInstance("DESede/ECB/NoPadding")
            decipher.init(Cipher.DECRYPT_MODE, keySpec)
            val plainText = decipher.doFinal(Utils.hexStringToByteArray(dataHex))
            Utils.bytesToHex(plainText)
        } catch (e: Exception) {
            throw CryptoException(CryptoException.ErrorCode.ENCRYPT_3DES, "", e)
        }

    }

    fun getMac(_message: ByteArray, key:ByteArray):ByteArray {
        val k = key.copyOfRange(0, 8)
        val k1 = key.copyOfRange(8, 16)

        var message=_message
        //дополнить весь блок нулями до длины кратной 8
        message = if (message.size % 8 != 0) {
            val resLength = message.size / 8 * 8 + 8
            message + ByteArray(resLength - message.size)
        } else {
            message
        }

        val block1 = message.copyOfRange(0, 0 + 8)
        var encResult = encryptDES(block1, k)

        for (i in 8..message.size - 8 step 8) {
            val blockNext = message.copyOfRange(i, i + 8)

            val xorResult = getXor(blockNext, encResult)
            encResult = encryptDES(xorResult, k)
        }
        encResult= decryptDES(encResult, k1)
        return encryptDES(encResult, k).copyOfRange(0,4)




    }

    fun getKcv(key: String): String {
        val digestOfPassword = Utils.hexStringToByteArray(key)
        val keyBytes = Arrays.copyOf(digestOfPassword, 24)
        var j = 0
        var k = 16
        while (j < 8) {
            keyBytes[k++] = keyBytes[j++]
        }
        val keySpec: SecretKey = SecretKeySpec(keyBytes, "DESede")
        val iv = IvParameterSpec(ByteArray(8))
        val decipher: Cipher
        return try {
            decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding")
            decipher.init(Cipher.ENCRYPT_MODE, keySpec, iv)
            val plainText = decipher.doFinal(Utils.hexStringToByteArray("0000000000000000"))
            Utils.bytesToHex(plainText).substring(0, 6)
        } catch (e: Exception) {
            throw CryptoException(CryptoException.ErrorCode.KCV_EXCEPTION, "", e)
        }
    }

}