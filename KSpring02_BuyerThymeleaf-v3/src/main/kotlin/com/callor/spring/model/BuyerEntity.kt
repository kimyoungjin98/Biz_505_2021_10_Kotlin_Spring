package com.callor.spring.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

// table의 논리적인 개념
// Entity = Relation
// Entity 선언할 건데.. Entity를 기준으로 CREATE TABLE 하라!
//@Entity
//@Table(name = "tbl_buyer", schema = "naraDB")
class BuyerEntity {
    // 칼럼들을 멤버변수로 선언
    @Id
    @Column(columnDefinition = "CHAR(4)", nullable = false)
    private val userid: String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val name: String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = true)
    private val tel: String? = null

    @Column(nullable = true)
    private val address: String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = true)
    private val manager: String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = true)
    private val man_tel: String? = null

    @Column(columnDefinition = "CHAR(4)", nullable = true)
    private val buy_total: Int? = 0
}
