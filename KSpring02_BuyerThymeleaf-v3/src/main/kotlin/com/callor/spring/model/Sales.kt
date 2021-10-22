package com.callor.spring.model
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

/**
 * id 칼럼을 자동 증가 옵션으로 자동 생성하기
 * SEQUENCE, IDENTITY, TABLE, AUTO
 * SEQUENCE : Oracle Sequence
 * IDENTITY : Auto_increment 가 지원되는 DB
 * TABLE : Hibernate 가 자체적으로 SEQUENCE TABLE 을 만들고
 *      증가 값을 관리하도록 하기
 * AUTO : 사용하는 DBMS 특성에 따라 SEQ 를 만들거나 AUTO_IN..을 사용하여 증가값을 관리
 *
 * AUTO 설정을 하면 DB특성에 따라 자체적으로 관리 하는데
 *      현재는 그렇지 않고 hibernate_sequence 테이블을 생성하여
 *      JPA 가 자체적으로 seq 를 생성하도록 만들어진다
 *      auto_increment가 없는 DB에서는 AUTO로 설정하는 것을 권장한다.
 */

@Entity
@Table(name = "tbl_sales", schema = "naraDB")
data class Sales(

    @Id
    @Column(columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var seq : Long? = null,
    val userid : String?= null,
    var date : String? = null,
    var time : String? = null,
    var pname : String? = null,
    var qty : Int? = null,
    var amt : Int? = null,
    var total : Int? = null,


    @Transient // table을 생성할 때 칼럼에 추가하지 말라! 라는 의미
    // 데이터에 특별하게 Date(날짜, 시간형) 값을 사용하고 싶을 때
    @Temporal(TemporalType.DATE)
    // 날짜만
    var date1 : Date? = null,
    // 시간만
    @Transient
    @Temporal(TemporalType.DATE)
    var time1 : Date? = null,

    // 날짜와 시간을 동시에
    @Temporal(TemporalType.TIMESTAMP)
    @Transient
    var date_time : Date? = null
)
