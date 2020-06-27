package com.example.task3.repository.model

import javax.persistence.*

@Entity
@Table(name = "branches")
data class BranchEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column(name = "title")
        val title: String,

        @Column(name = "lon")
        val lon: Double,

        @Column(name = "lat")
        val lat: Double,

        @Column(name = "address")
        val address: String
)