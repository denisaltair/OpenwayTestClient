import junit.framework.TestCase

import kotlin.Exception

class PostTerminalRequestsTest : TestCase() {
    fun testConnect() {
        try {
            val outputClient = OutputClient(Config.POS_N5_IP, Config.POS_N5_OPENWAY_SERVER_PORT)
            assert(true)
        } catch (e: Exception) {
            assert(false)
        }

    }

    fun testCheckConnect() {
        try {
            OpenwayRequests.outputClient.close()
            OpenwayRequests.outputClient= OutputClient(Config.POS_N5_IP, Config.POS_N5_OPENWAY_SERVER_PORT)
            val result=OpenwayRequests.checkConnect(Config.TESTS_TERMINAL_1,"000001")



        } catch (e:Exception) {
            assert(false)


        }



    }





}