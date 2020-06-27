package com.example.task3.repository

import com.example.task3.repository.model.QueueEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface QueueRepository: JpaRepository<QueueEntity, Int> {

    @Query("""
        select * from queue_log where branches_id = :branch
                          and extract(isodow from data) = :dayOfWeek
                          and (extract(hour from start_time_of_wait) = :hourOfDay
                          or extract(hour from end_time_of_wait) = :hourOfDay)
    """, nativeQuery = true)
    fun queueHistory(@Param("branch") branch: Int,
                     @Param("dayOfWeek") dayOfWeek: Int,
                     @Param("hourOfDay") hourOfDay: Int): List<QueueEntity>
}