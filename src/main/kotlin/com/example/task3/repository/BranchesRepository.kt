package com.example.task3.repository

import com.example.task3.repository.model.BranchEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BranchesRepository: JpaRepository<BranchEntity, Int> {
}