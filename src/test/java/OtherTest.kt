import junit.framework.TestCase
import kz.multibank.cardposclient.entities.OpenwayTest
import org.hibernate.cfg.Configuration
import org.junit.Test

class OtherTest : TestCase(){
    @Test
    fun testHibernateConnect() {
        val sessionFactory= Configuration().configure()
            .addAnnotatedClass(OpenwayTest::class.java)
            .buildSessionFactory()

        val session=sessionFactory.openSession()
        val transaction=session.beginTransaction()
        val openwayTest=OpenwayTest()
        openwayTest.name="Q1"
        session.save(openwayTest)
        transaction.commit()
        session.close()



    }




}