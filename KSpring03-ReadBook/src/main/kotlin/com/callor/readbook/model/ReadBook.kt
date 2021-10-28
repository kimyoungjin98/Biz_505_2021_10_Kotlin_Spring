package com.callor.readbook.model

import javax.persistence.*

@Entity
@Table(name = "tbl_readbook", schema = "naraDB")
data class ReadBook(
    @Id
    @Column(columnDefinition = "BIGINT", unique = true, name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var seq: Long? = 0,

    @Column(columnDefinition = "VARCHAR(13)", nullable = false)
    var isbn: String? = null,

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    var rdate: String? = null,

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    var stime: String? = null,

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    var etime: String? = null,

    @Column(nullable = false)
    var rating: Int? = null,

    @Column(nullable = false)
    var comment: String? = null,
)