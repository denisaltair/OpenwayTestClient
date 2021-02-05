package entities


import enums.OpenwayField
import enums.OpenwayField.*
import org.jpos.iso.ISOMsg
import other.OpenwayUtils
import other.Utils

class MyISOMsg : ISOMsg() {
    override fun setMTI(mti: String?) {
        super.setMTI(mti)
        calcDirection()
    }

    override fun unpack(b: ByteArray?): Int {
        val result = super.unpack(b)
        calcDirection()
        return result
    }

    fun calcDirection() {
        direction = if (mti[2] == '1' || mti[2] == '3') INCOMING else OUTGOING
    }

    override fun toString(): String {

        var result = "Body: " + Utils.bytesToHex(OpenwayUtils.packOpenwayMessage(this))+"\n"+
                "MTI: " + this.getMTI() + "\n"
        for (i in 1..this.maxField) {
            if (!this.hasField(i)) continue
            val str = this.getString(i)
            result += str + (if (str.length < 40) "".padStart(45 - str.length, ' ') else "  ") + OpenwayField.valueOfFromNumber(i) + "\n"
        }
        return result
    }

    fun getGUID():String? {
        return this.getString(F65_GUID)
    }

    fun setGUID(value:String) {
        this.set(F65_GUID, value)
    }

    fun getParentGUID():String? {
        return this.getString(F66_PARENT_GUID)
    }

    fun setParentGUID(value:String) {
        this.set(F66_PARENT_GUID, value)
    }

    fun set(openwayField: OpenwayField, value: String?) {
        super.set(openwayField.number, value)
    }


    fun set(openwayField: OpenwayField, value: ByteArray?) {
        super.set(openwayField.number, value)
    }




    fun getString(openwayField: OpenwayField):String? {
        return super.getString(openwayField.number)
    }

    fun getBytes(openwayField: OpenwayField):ByteArray? {
        return super.getBytes(openwayField.number)
    }


    fun hasField(openwayField: OpenwayField):Boolean {
        return super.hasField(openwayField.number)
    }




}