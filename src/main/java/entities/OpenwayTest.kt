package  kz.multibank.cardposclient.entities

import javax.persistence.*



@Entity
@Table(name="openway_test")
class OpenwayTest {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    var id:Int=0
    @Column (name = "name")
    var name=""
}