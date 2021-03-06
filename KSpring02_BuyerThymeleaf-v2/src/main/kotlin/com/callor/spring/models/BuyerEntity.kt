package com.callor.spring.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="tbl_buyer", schema = "naraDB" )
class BuyerEntity {

    @Id
    @Column(columnDefinition = "CHAR(5)", nullable = false)
    private val userid : String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val name : String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val tel : String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val address : String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val manager : String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val man_tel : String? = null

    @Column(columnDefinition = "VARCHAR(25)", nullable = false)
    private val buy_total : Int = 0

}