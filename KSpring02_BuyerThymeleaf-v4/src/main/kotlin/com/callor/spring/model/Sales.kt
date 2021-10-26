package com.callor.spring.model

import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

/**
 * ID 칼럼을 자동 증가 옵션으로 자동 생성하기
 * SEQUENCE, IDENTITY, TABLE, AUTO
 * SEQUENCE: Oracle Sequence
 * IDENTITY: Auto_increment가 지원되는 DB
 * TABLE: Hibernate가 자체적으로 SEQUENCE TABLE을 만들고 증가값을 관리하도록 하기
 * AUTO: 사용하는 DBMS 특성에 따라 SEQ를 만들거나 Auto_increment를 생성하여 증가값을 관리
 *
 * AUTO 설정을 하면 DB 특성에 따라 자체적으로 관리를 하는 데
 *      현재는 그렇지 않고 hibernate_sequence 테이블을 생성하여
 *      JPA가 자체적으로 seql를 생성하도록 만들어진다
 * auto_increment가 없는 DB에서는 AUTO로 설정하는 것을 권함
 */
@Entity
@Table(name = "tbl_sales", schema = "naraDB")
data class Sales(

    @Id
    @Column(columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var seq: Long? = null,
    var userid:String? = null,
    var date: String? = null,
    var time: String? = null,
    var pname: String? = null,
    var qty: Int? = null,
    var amt: Int? = null,
    var total: Int? = null,

    // 데이터에 특별하게 Date(날짜? = null, 시간형) 값을 사용하고 싶을 때
    @Transient // table 생성 시 칼럼에 추가하지 말라!
    @Temporal(TemporalType.DATE)
    var date1: Date? = null,

    @Transient
    @Temporal(TemporalType.TIME)
    var time1: Date? = null,

    @Transient
    @Temporal(TemporalType.TIMESTAMP)
    var date_time: Date? = null,
)
