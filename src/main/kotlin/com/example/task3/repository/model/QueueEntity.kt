package com.example.task3.repository.model

import java.sql.Date
import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "queue_log")
data class QueueEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column(name = "branches_id")
        val branchesId: Int,

        @Column(name = "data")
        var data: Date?,

        @Column(name = "start_time_of_wait")
        val startTimeOfWait: Time,

        @Column(name = "end_time_of_wait")
        val endTimeOfWait: Time,

        @Column(name = "end_time_of_service")
        val endTimeOfService: Time
)
