package other

object OpenwayCryptoUtils {
    fun calcPinBlock(key:ByteArray, pinCode:String, _pan:String):ByteArray {
        var firstPart="0"+ pinCode.length + pinCode
        firstPart=firstPart.padEnd(16,'F')
        val pan=_pan.substring(3, _pan.length-1).padStart(16,'0')

        val pinBlock=CryptoUtils.getXor(Utils.hexStringToByteArray(firstPart),Utils.hexStringToByteArray(pan))

        val encryptedPinBlock=CryptoUtils.encrypt3DES(pinBlock,key)

        return encryptedPinBlock
    }




}