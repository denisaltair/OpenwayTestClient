package OpenwayTests

import entities.TestResult
import entities.TransMessage
import enums.OpenwayResponseCode
import enums.OperationType
import helpers.OpenwayTesterHelper
import junit.framework.TestCase
import kz.multibank.cardposclient.entities.Currency

import kz.multibank.cardposclient.entities.EntryMode
import org.junit.Test
import other.OpenwayUtils
import other.Utils
import java.math.BigDecimal
import java.util.*


class R1_R3_RCAcceptanceTests : TestCase() {

    @Test
    fun testR1() {
        println("R1. Decline Response Codes (Terminal 1 with MAC and without MAC)")
        var guid = ""
        var testResult: TestResult
//===================================================================
//-----------------R1.01---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(10),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.REFER_TO_CARD_ISSUER)
        println("R1.01 Refer to Card Issuer. Key in Auth Code=999999, then upload transaction as offline., rrn:" + testResult.rrn)

//-----------------R1.02---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.PURCHASE, amount = BigDecimal(20),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.REFER_TO_CARD_ISSUER_SPECIAL_CONDITION)
        println("R1.02 Refer to card issuer's special condition. Key in Auth Code=999999, then upload transaction as offline., rrn:" + testResult.rrn)


//-----------------R1.03---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(30),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.INVALID_MERCHANT_SOURCE)
        println("R1.03 Invalid merchant, rrn:" + testResult.rrn)

//-----------------R1.04---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(50),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.DO_NOT_HONOUR)
        println("R1.04 Do not honor, rrn:" + testResult.rrn)

//-----------------R1.05---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(60),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ERROR)
        println("R1.05 Error, rrn:" + testResult.rrn)

//-----------------R1.06---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(90),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.REQUEST_IN_PROGRESS)
        println("R1.06 Request in progress, rrn:" + testResult.rrn)

//-----------------R1.07---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(120),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.INVALID_TRANSACTION)
        println("R1.07 Invalid transaction, rrn:" + testResult.rrn)

//-----------------R1.08---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(130),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_AMOUNT)
        println("R1.08 Invalid amount, rrn:" + testResult.rrn)

//-----------------R1.09---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(140),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_SUCH_CARD)
        println("R1.09 Invalid card number, rrn:" + testResult.rrn)

//-----------------R1.10---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(150),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_SUCH_ISSUER)
        println("R1.10 No such issuer, rrn:" + testResult.rrn)

//-----------------R1.11---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(170),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.CUSTOMER_CANCELLATION)
        println("R1.11 Customer cancellation, rrn:" + testResult.rrn)

//-----------------R1.12---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(180),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.CUSTOMER_DISPUTE)
        println("R1.12 Customer dispute, rrn:" + testResult.rrn)

//-----------------R1.13---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(190),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.REENTER_TRANSACTION)
        println("R1.13 Re-enter transaction, rrn:" + testResult.rrn)

//-----------------R1.14---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(200),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.INVALID_RESPONSE)
        println("R1.14 Invalid response, rrn:" + testResult.rrn)

//-----------------R1.15---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(210),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_ACTION_TAKEN)
        println("R1.15 No action taken, rrn:" + testResult.rrn)

//-----------------R1.16---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(220),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.SUSPECTED_MALFUNCTION)
        println("R1.16 Suspected malfunction, rrn:" + testResult.rrn)

//-----------------R1.17---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(230),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.UNACCEPTABLE_TRANSACTION_FEE)
        println("R1.17 Unacceptable transaction fee, rrn:" + testResult.rrn)

//-----------------R1.18---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(240),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.FILE_UPDATE_NOT_SUPPORTED_BY_RECEIVER)
        println("R1.18 File update not supported by receiver, rrn:" + testResult.rrn)

//-----------------R1.19---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(250),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_SUCH_RECORD)
        println("R1.19 No such record, rrn:" + testResult.rrn)

//-----------------R1.20---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(260),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.DUPLICATE_RECORD_UPDATE_OLD_RECORD_REPLACED)
        println("R1.20 Duplicate record update, old record replaced, rrn:" + testResult.rrn)

//-----------------R1.21---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(270),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.FILE_UPDATE_FIELD_EDIT_ERROR)
        println("R1.21 File update field edit error, rrn:" + testResult.rrn)

//-----------------R1.22---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(280),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.FILE_LOCKED_OUT_WHILE_UPDATE)
        println("R1.22 File locked out while update, rrn:" + testResult.rrn)


//-----------------R1.23---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(290),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.FILE_UPDATE_ERROR_CONTACT_ACQUIRER)
        println("R1.23 File update error, contact acquirer, rrn:" + testResult.rrn)

//-----------------R1.24---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(300),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.FORMAT_ERROR)
        println("R1.24 Format error, rrn:" + testResult.rrn)

//-----------------R1.25---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(310),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ISSUER_SIGNED_OFF)
        println("R1.25 Issuer signed-off, rrn:" + testResult.rrn)

//-----------------R1.26---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(320),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.COMPLETED_PARTIALLY)
        println("R1.26 Completed partially, rrn:" + testResult.rrn)

//-----------------R1.27---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(390),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_CREDIT_ACCOUNT)
        println("R1.27 No credit account, rrn:" + testResult.rrn)

//-----------------R1.28---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(400),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.REQUESTED_FUNCTION_NOT_SUPPORTED)
        println("R1.28 Requested function not supported, rrn:" + testResult.rrn)

//-----------------R1.29---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(420),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_UNIVERSAL_ACCOUNT)
        println("R1.29 No universal account, rrn:" + testResult.rrn)

//-----------------R1.30---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(440),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_INVESTMENT_ACCOUNT)
        println("R1.30 No investment account, rrn:" + testResult.rrn)

//-----------------R1.31---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(500),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.DO_NOT_RENEW)
        println("R1.31 Do not renew, rrn:" + testResult.rrn)

//-----------------R1.32---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(510),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NOT_SUFFICIENT_FUNDS)
        println("R1.32 Not sufficient funds, rrn:" + testResult.rrn)

//-----------------R1.33---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(520),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_CHECKING_ACCOUNT)
        println("R1.33 No checking account, rrn:" + testResult.rrn)

//-----------------R1.34---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(530),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_SAVINGS_ACCOUNT)
        println("R1.34 No savings account, rrn:" + testResult.rrn)

//-----------------R1.35---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(540),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.EXPIRED_CARD)
        println("R1.35 Expired card, rrn:" + testResult.rrn)

//-----------------R1.36---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(550),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_PIN)
        println("R1.36 Incorrect PIN, rrn:" + testResult.rrn)

//-----------------R1.37---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(560),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_CARD_RECORD)
        println("R1.37 No card record, rrn:" + testResult.rrn)

//-----------------R1.38---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(570),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.TRANSACTION_NOT_PERMITTED_TO_CARDHOLDER)
        println("R1.38 Transaction not permitted to cardholder, rrn:" + testResult.rrn)

//-----------------R1.39---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(580),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.TRANSACTION_NOT_PERMITTED_TO_TERMINAL)
        println("R1.39 Transaction not permitted to terminal, rrn:" + testResult.rrn)

//-----------------R1.40---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(590),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.SUSPECTED_FRAUD)
        println("R1.40 Suspected fraud, rrn:" + testResult.rrn)

//-----------------R1.41---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(600),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.CARD_ACCEPTOR_CONTACT_ACQUIRER)
        println("R1.41 Card acceptor contact acquirer, rrn:" + testResult.rrn)

//-----------------R1.42---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(610),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.EXCEEDS_WITHDRAWAL_AMOUNT_LIMIT)
        println("R1.42 Exceeds withdrawal amount limit, rrn:" + testResult.rrn)

//-----------------R1.43---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(620),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.RESTRICTED_CARD)
        println("R1.43 Restricted card, rrn:" + testResult.rrn)

//-----------------R1.44---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(630),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.SECURITY_VIOLATION)
        println("R1.44 Security violation, rrn:" + testResult.rrn)

//-----------------R1.45---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(640),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_ORIGINAL_AMOUNT)
        println("R1.45 Wrong original amount, rrn:" + testResult.rrn)

//-----------------R1.46---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(650),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.EXCEEDS_WITHDRAWAL_FREQUENCY_LIMIT)
        println("R1.46 Exceeds withdrawal frequency limit, rrn:" + testResult.rrn)

//-----------------R1.47---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(660),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.CALL_ACQUIRERS_SECURITY_DEPARTMENT)
        println("R1.47 Call acquirers security department, rrn:" + testResult.rrn)

//-----------------R1.48---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(680),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.RESPONSE_RECEIVED_TOO_LATE)
        println("R1.48 Response received too late, rrn:" + testResult.rrn)

//-----------------R1.49---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(750),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ALLOWABLE_NUMBER_OF_PIN_TRIES_EXCEEDED)
        println("R1.49 Allowable number of PIN tries exceeded, rrn:" + testResult.rrn)

//-----------------R1.50---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(760),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_PIN_NUMBER_OF_PIN_TRIES_EXCEEDED)
        println("R1.50 Wrong PIN, number of PIN tries exceeded, rrn:" + testResult.rrn)

//-----------------R1.51---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(770),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.WRONG_REFERENCE_NO)
        println("R1.51 Wrong Reference No., rrn:" + testResult.rrn)

//-----------------R1.52---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(780),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.RECORD_NOT_FOUND)
        println("R1.52 Record Not Found, rrn:" + testResult.rrn)

//-----------------R1.53---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(790),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ALREADY_REVERSED)
        println("R1.53 Already reversed, rrn:" + testResult.rrn)

//-----------------R1.54---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(800),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NETWORK_ERROR)
        println("R1.54 Network error, rrn:" + testResult.rrn)

//-----------------R1.55---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(810),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.FOREIGN_NETWORK_ERROR_PIN_CRYPTOGRAPHIC_ERROR)
        println("R1.55 Foreign network error, rrn:" + testResult.rrn)

//-----------------R1.56---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(820),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.TIMEOUT_AT_ISSUER_SYSTEM_BAD_CVV_VISA)
        println("R1.56 Time-out at issuer system, rrn:" + testResult.rrn)

//-----------------R1.57---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(830),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.TRANSACTION_FAILED)
        println("R1.57 Transaction failed, rrn:" + testResult.rrn)

//-----------------R1.58---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(840),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PREAUTHORIZATION_TIMED_OUT)
        println("R1.58 Pre-Authorisation timed out, rrn:" + testResult.rrn)

//-----------------R1.59---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(850),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.NO_REASON_TO_DECLINE)
        println("R1.59 Account verification failed, rrn:" + testResult.rrn)

//-----------------R1.60---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(860),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.UNABLE_TO_VALIDATE_PIN)
        println("R1.60 Unable to validate PIN, rrn:" + testResult.rrn)

//-----------------R1.61---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(870),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PURCHASE_APPROVAL_ONLY)
        println("R1.61 Reserved, rrn:" + testResult.rrn)

//-----------------R1.62---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(880),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.CRYPTOGRAPHIC_FAILURE)
        println("R1.62 Cryptographic failure, rrn:" + testResult.rrn)

//-----------------R1.63---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(890),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.AUTHENTICATION_FAILURE)
        println("R1.63 Authentication failure, rrn:" + testResult.rrn)

//-----------------R1.64---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(900),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.CUTOFF_IS_IN_PROGRESS)
        println("R1.64 Cut-off is in progress, rrn:" + testResult.rrn)

//-----------------R1.65---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(910),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.ISSUER_OR_SWITCH_IS_INOPERATIVE)
        println("R1.65 Issuer or switch is inoperative, rrn:" + testResult.rrn)

//-----------------R1.66---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(920),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.UNABLE_TO_ROUTE_AT_ACQUIRER_MODULE)
        println("R1.66 Unable to route at acquirer module, rrn:" + testResult.rrn)

//-----------------R1.67---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(930),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.CANNOT_BE_COMPLETED_VIOLATION_OF_LAW)
        println("R1.67 Cannot be completed, violation of law, rrn:" + testResult.rrn)

//-----------------R1.68---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(940),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.DUPLICATE_TRANSMISSION)
        println("R1.68 Duplicate Transmission, rrn:" + testResult.rrn)

//-----------------R1.69---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(950),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.RECONCILE_ERROR_AUTH_NOT_FOUND)
        println("R1.69 Reconcile error, rrn:" + testResult.rrn)

//-----------------R1.70---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(960),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.SYSTEM_DESTROYED)
        println("R1.70 System Malfunction, rrn:" + testResult.rrn)

//-----------------R1.71---------------------------------------------
        val transMessage = TransMessage()
        transMessage.guid = Utils.getGUID()
        transMessage.transmissionDate = Date()
        transMessage.tid = Config.TESTS_TERMINAL_1

        val response = OpenwayRequests.reconciliationRequest(transMessage)
        val openwayResponseCode = response.openwayResponseCode ?: OpenwayResponseCode.UNKNOWN_CODE
        assertEquals(openwayResponseCode, OpenwayResponseCode.ACCEPTED)
        println("R1.71 Reconciliation, rrn:" + response.rrn)

    }


    fun testR2() {
        println("R2. Decline Response Codes (Terminal 1 with MAC and without MAC)")
        var guid = ""
        var testResult: TestResult
//===================================================================
//-----------------R2.01---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(40),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PICK_UP)
        println("R2.01 Pick-up, rrn:" + testResult.rrn)


//-----------------R2.02---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(70),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PICKUP_CARD_SPECIAL_CONDITION)
        println("R2.02 Pick-up, Special Condition rrn:" + testResult.rrn)

//-----------------R2.33---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(330),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PICKUP_EXPIRED_CARD)
        println("R2.33 Expired card, pick-up,  rrn:" + testResult.rrn)

//-----------------R2.34---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(340),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.SUSPECT_FRAUD)
        println("R2.34 Suspected fraud, pick-up rrn:" + testResult.rrn)

//-----------------R2.35---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(350),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PICKUP_CARD_ACCEPTOR_CONTACT_ACQUIRER)
        println("R2.35 Card acceptor contact acquirer, pick-up rrn:" + testResult.rrn)

//-----------------R2.36---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(360),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PICKUP_CARD_RESTRICTED)
        println("R2.36 Restricted card, pick-up rrn:" + testResult.rrn)

//-----------------R2.37---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(370),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PICKUP_CALL_ACQUIRER_SECURITY)
        println("R2.37 Call acquirer security, pick-up rrn:" + testResult.rrn)

//-----------------R2.38---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(380),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PICK_UP_ALLOWABLE_PIN_TRIES_EXCEEDED)
        println("R2.38 Allowable PIN tries exceeded, pick-up rrn:" + testResult.rrn)

//-----------------R2.41---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(410),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PICKUP_LOST_CARD)
        println("R2.41 Lost card, pick-up rrn:" + testResult.rrn)

//-----------------R2.43---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(430),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.PICKUP_STOLEN_CARD)
        println("R2.43 Stolen card, pick-up rrn:" + testResult.rrn)

    }

    fun testR3() {
        println("R3. Special Approve Response Codes (Terminal 1 with MAC and without MAC)")
        var guid = ""
        var testResult: TestResult
//===================================================================
//-----------------R2.01---------------------------------------------
        testResult = OpenwayTesterHelper.sendRequest(
            operationType = OperationType.AUTHORISATION, amount = BigDecimal(80),
            pan = Config.CARD6_PAN, entryMode = EntryMode.MAGNET_SBT, tid = Config.TESTS_TERMINAL_1
        )

        assertEquals(testResult.openwayResponseCode, OpenwayResponseCode.HONOUR_WITH_IDENTIFICATION)
        println("R3.01 Honour with identification, rrn:" + testResult.rrn)
    }
}