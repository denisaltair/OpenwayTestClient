package kz.multibank.cardposclient.entities

import java.lang.IllegalArgumentException

enum class EntryMode (var openWayCode:String, var description:String){
    MAGNET_PBT ("901","PIN Magnet Card"),
    MAGNET_SBT ("902","Singed Magnet Card"),
    CHIP_PBT ("051","PIN Chip Card"),
    CHIP_SBT ("052","Signed Chip Card"),
    RFID_PBT ("071","PIN RFID Card"),
    RFID_SBT ("072","Signed RFID Card"),
    MANUAL_SBT ("012","Manual SBT");

    companion object {
        fun valueOfFromOpenwayCode(openWayCode: String): EntryMode {
            for (entryMode in EntryMode.values()) {
                if (entryMode.openWayCode == openWayCode) return entryMode
            }
            throw IllegalArgumentException()
        }
    }

}