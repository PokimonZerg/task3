package com.example.task3.services

import com.example.task3.controllers.model.BranchView
import com.example.task3.repository.BranchesRepository
import com.example.task3.repository.QueueRepository
import com.example.task3.repository.model.BranchEntity
import org.apache.commons.math3.stat.descriptive.rank.Median
import org.apache.lucene.util.SloppyMath
import org.springframework.stereotype.Service

@Service
class BranchService(val branchesRepository: BranchesRepository, val queueRepository: QueueRepository) {

    fun predict(id: Int, dayOfWeek: Int, hourOfDay: Int): BranchView {
        val branch = branchesRepository.findById(id)
        if (!branch.isPresent)
            throw BranchException("branch not found")
        val queues = queueRepository.queueHistory(id, dayOfWeek, hourOfDay)
        if (queues.isEmpty())
            throw BranchException("branch not found")

        val waitTimeInSeconds = queues.map {
            (it.endTimeOfWait.time - it.startTimeOfWait.time) / 1000.0
        }.toDoubleArray()

        val median = Median().evaluate(waitTimeInSeconds)
        return convert(branch.get()).apply {
            this.dayOfWeek = dayOfWeek
            this.hourOfDay = hourOfDay
            this.predicting = median.toInt()
        }
    }

    fun getBranch(id: Int): BranchView {
        val branch = branchesRepository.findById(id)
        if (branch.isPresent) {
            return convert(branch.get())
        }
        else {
            throw BranchException("branch not found")
        }
    }

    fun getNearBranch(lat: Double, lon: Double): BranchView {
        // Если офисов много, то считать надо прям в базе данных конечно
        val branch = branchesRepository.findAll().minBy {
            SloppyMath.haversinMeters(it.lat, it.lon, lat, lon)
        }

        val view = convert(branch ?: throw BranchException("branch not found"))
        view.distance = SloppyMath.haversinMeters(branch.lat, branch.lon, lat, lon).toInt()
        return view
    }

    fun convert(from: BranchEntity): BranchView {
        return BranchView(
                id = from.id,
                title = from.title,
                lat = from.lat,
                lon = from.lon,
                address = from.address
        )
    }
}