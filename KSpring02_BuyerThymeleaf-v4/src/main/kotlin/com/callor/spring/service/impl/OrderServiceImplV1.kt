package com.callor.spring.service.impl

import com.callor.spring.model.Sales
import com.callor.spring.repository.SalesRepository
import com.callor.spring.service.OrderService
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import kotlin.random.Random

@Service("orderServiceV1")
class OrderServiceImplV1(val sDao: SalesRepository) : OrderService {

    override fun selectAll(): Array<Sales> {
        return sDao.findAll().toTypedArray()
    }

    override fun findById(seq: Long): Sales {
        return sDao.findById(seq).get()
    }

    override fun findByUserId(userid: String): Array<Sales> {
        return sDao.findByUserid(userid)
    }

    override fun findByPname(pname: String): Array<Sales> {
        return sDao.findByPname(pname)
    }

    override fun findByDateDestance(sDate: String, eDate: String): Array<Sales> {
        TODO("Not yet implemented")
    }

    override fun insert(sales: Sales): Sales {
        sales.date = LocalDate.now().toString()
        sales.time = LocalTime.now().toString()

        return sDao.save(sales)
    }

    override fun delete(seq: Long) {
        return sDao.deleteById(seq)
    }

    override fun update(sales: Sales): Sales {
        return sDao.save(sales)
    }


}