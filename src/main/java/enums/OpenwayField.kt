package enums

enum class OpenwayField(val number:Int) {
    UNKNOWN_FIELD(-1),
    F1_BITMAP(1),
    F2_PAN(2),
    F3_PROCESS_CODE(3),
    F4_AMOUNT(4),
    F5_AMOUNT_SETTLEMENT(5),
    F6_AMOUNT_CARDHOLDER(6),
    F7_TRANSMISSION_DATETIME(7),
    F8_AMOUNT_CARDHOLDER_BILLING_FEE(8),
    F9_CONVERSION_RATE_SETTLEMENT(9),
    F10_CONVERSION_RATE_CARDHOLDER(10),
    F11_STAN(11),
    F12_LOCAL_TIME(12),
    F13_LOCAL_DATE(13),
    F14_EXPIRATION_DATE(14),
    F15_SETTLEMENT_DATE(15),
    F16_CURRENCY_CONVERSION_DATE(16),
    F17_CAPTURE_DATE(17),
    F18_MERCHANT_TYPE(18),
    F19_ACQUIRING_INSTITUTION(19),
    F20_PAN_EXTENDED(20),
    F21_FORWARDING_INSTITUTION(21),
    F22_ENTRY_MODE(22),
    F23_PAN_SEQUENCE(23),
    F24_NII_FUNCTION_CODE(24),
    F25_POS_CONDITION_CODE(25),
    F26_POS_CAPTURE_CODE(26),
    F27_AUTH_ID_RESPONSE_LENGTH(27),
    F28_AMOUNT_TRANSACTION_FEE(28),
    F29_AMOUNT_SETTLEMENT_FEE(29),
    F30_AMOUNT_TRANSACTION_PROCESSING_FEE(30),
    F31_AMOUNT_SETTLEMENT_PROCESSING_FEE(31),
    F32_ACQUIRING_INSTITUTION_CODE(32),
    F33_FORWARDING_INSTITUTION_CODE(33),
    F34_PAN_EXTENDED(34),
    F35_TRACK2(35),
    F36_TRACK3(36),
    F37_RRN(37),
    F38_AUTH_ID_RESPONSE(38),
    F39_RESPONSE_CODE(39),
    F40_SERVICE_RESTRICTION_CODE(40),
    F41_TID(41),
    F42_CA_ID(42),
    F43_CARD_ACCEPTOR_INFO(43),
    F44_ADD_RESPONSE_DATA(44),
    F45_TRACK1(45),
    F46_ADD_DATA_ISO(46),
    F47_ADD_DATA_NATIONAL(47),
    F48_ADD_DATA_PRIVATE(48),
    F49_CURRENCY_CODE(49),
    F50_CURRENCY_CODE_SETTLEMENT(50),
    F51_CURRENCY_CODE_CARDHOLDER(51),
    F52_PIN(52),
    F53_SECURITY_CONTROL_INFO(53),
    F54_ADD_AMOUNT(54),
    F55_ICC(55),
    F56_RESERVED_ISO(56),
    F57_RESERVED_NATIONAL(57),
    F58_RESERVED_NATIONAL(58),
    F59_RESERVED_NATIONAL(59),
    F60_RESERVED_NATIONAL(60),
    F61_RESERVED_PRIVATE(61),
    F62_RESERVED_PRIVATE(62),
    F63_RESERVED_PRIVATE(63),
    F64_MAC(64),
    F65_GUID(65),
    F66_PARENT_GUID(66),
    F67_IS_WITH_MAC(67),
    F68_IS_WITH_SECURE_ISO(68),
    F69_BANK_REQUEST(69),
    F70_BANK_RESPONSE(70);

    companion object {
        fun valueOfFromNumber(number: Int): OpenwayField {
            for (openwayField in OpenwayField.values()) {
                if (openwayField.number == number) return openwayField
            }
            return UNKNOWN_FIELD
        }
    }
}