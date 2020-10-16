package other

import junit.framework.TestCase

class OpenwayCryptoUtilsTest : TestCase() {
    fun testCalcPinBlock() {
        val pinCode="6684"
        val pan="4052554009290268"
        val key=Utils.hexStringToByteArray("FFFFFFFFFFFFFFFF1111111111111111")
        val pinBlock=OpenwayCryptoUtils.calcPinBlock(key,pinCode,pan)
        println("Encrypted PIN Block="+Utils.bytesToHex(pinBlock))
        assertEquals(Utils.bytesToHex(pinBlock),"87711291353C216B")
    }

}