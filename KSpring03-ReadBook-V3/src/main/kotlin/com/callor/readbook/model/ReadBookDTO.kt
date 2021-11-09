package com.callor.readbook.model

import javax.persistence.*

@Entity
@Table(name = "tbl_readbooks")
data class ReadBookDTO(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "BIGINT")
        var seq: Long? = null,

        @Column(columnDefinition = "VARCHAR(13)", nullable = false)
        var isbn: String? = null,

        @Column(columnDefinition = "VARCHAR(125)", nullable = false)
        var title: String? = null,

        var sdate: String? = null,

        var stime: String? = null,

        var etime: String? = null,

        var subject: String? = null,

        var content: String? = null,
)
